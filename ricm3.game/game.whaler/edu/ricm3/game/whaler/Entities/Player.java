package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Game_exception.Automata_Exception;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public final class Player extends MobileEntity {

	BufferedImage m_playerNorth;
	BufferedImage m_playerSouth;
	BufferedImage m_playerEast;
	BufferedImage m_playerWest;

	BufferedImage m_playerNorthUnder;
	BufferedImage m_playerSouthUnder;
	BufferedImage m_playerEastUnder;
	BufferedImage m_playerWestUnder;

	public float m_oil_jauge;
	private long m_lastHit;
	private long m_lastPop;

	/**
	 * @param pos
	 * @param sprite
	 * @param underSprite
	 * @param model
	 * @param dir
	 *            initial direction
	 * @param auto
	 *            player's automata
	 * @throws Game_exception
	 */

	public Player(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir)
			throws Game_exception {
		super(pos, true, sprite, underSprite, model, dir, Options.PLAYER_LIFE);

		m_automata = m_model.getAutomata(this);
		m_oil_jauge = Options.PLAYER_OIL_GAUGE;

		loadSprites();
		switch (dir) {
		case EAST:
			m_underSprite = m_playerEastUnder;
			m_sprite = m_playerEast;
			break;
		case WEST:
			m_underSprite = m_playerWestUnder;
			m_sprite = m_playerWest;
			break;
		case NORTH:
			m_underSprite = m_playerNorthUnder;
			m_sprite = m_playerNorth;

			break;
		default: // direction by default, SOUTH
			m_underSprite = m_playerSouthUnder;
			m_sprite = m_playerSouth;
			break;
		}
		
		m_lastHit = 1000000000L;
		m_lastPop = 1000000000L;
	}

	@Override
	public void destroy() throws Game_exception {
	}

	public void loadSprites() {
		m_playerSouth = m_sprite.getSubimage(0, 0, 32, 32);
		m_playerWest = m_sprite.getSubimage(0, 32, 32, 32);
		m_playerEast = m_sprite.getSubimage(0, 64, 32, 32);
		m_playerNorth = m_sprite.getSubimage(0, 96, 32, 32);

		m_playerSouthUnder = m_underSprite.getSubimage(0, 0, 32, 32);
		m_playerWestUnder = m_underSprite.getSubimage(0, 32, 32, 32);
		m_playerEastUnder = m_underSprite.getSubimage(0, 64, 32, 32);
		m_playerNorthUnder = m_underSprite.getSubimage(0, 96, 32, 32);
	}

	@Override
	public void step(long now) throws Game_exception, Automata_Exception {
		m_lastHit++;
		m_lastPop++;
		if(m_lastPop>1200L && m_model.UNDER_WATER && !(m_model.map().tile(this.m_pos).isSolid())) {
			m_model.swap();
		}
		long elapsed = now - m_lastStep;
		if (elapsed > 80L) {
			m_lastStep = now;

			try {
				m_automata.step(m_model, this);
			} catch (Exception e) {
				e.printStackTrace();
			}

			switch (m_direction) {
			case EAST:
				m_underSprite = m_playerEastUnder;
				m_sprite = m_playerEast;
				break;
			case WEST:
				m_underSprite = m_playerWestUnder;
				m_sprite = m_playerWest;
				break;
			case NORTH:
				m_underSprite = m_playerNorthUnder;
				m_sprite = m_playerNorth;

				break;

			default: // direction by default, SOUTH
				m_underSprite = m_playerSouthUnder;
				m_sprite = m_playerSouth;
				break;
			}
			if (m_life <= 0) {

				destroy();
			}
			m_oil_jauge -= 0.02;
		}
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {
		g.drawImage(m_underSprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

	@Override
	public void pop() throws Game_exception {
		if (m_model.UNDER_WATER) {
			if (!(m_model.map().tile(this.m_pos).isSolid())) {
				m_model.swap();
			}

		} else {
			m_model.swap();
		}
		m_lastPop=0;
	}

	@Override
	public void wizz() throws Game_exception {
		if (!m_model.UNDER_WATER) {

			Entity result = m_model.map().tile(this.pos_front()).contain(EntityType.OIL);
			if (result != null) {
				Oil will_burn = (Oil) result;
				will_burn.m_is_burning = true;
			}
		}
	}

	public void pick() throws Game_exception {
		Entity result = m_model.map().tile(this.m_pos).contain(EntityType.OIL);
		Oil will_burn = (Oil) result;
		if ((result != null) && !(m_model.UNDER_WATER) && !(will_burn.m_is_burning)) {
			Oil to_pick = (Oil) result;
			if (this.m_oil_jauge + Options.OIL_PICKED > Options.PLAYER_OIL_GAUGE) {
				this.m_oil_jauge = Options.PLAYER_OIL_GAUGE;
			} else {
				this.m_oil_jauge += Options.OIL_PICKED;
			}
			to_pick.destroy();
		}
	}

	@Override
	public void hit() throws Game_exception {

		if (m_lastHit > 50L && !m_model.UNDER_WATER) {
			switch (m_direction) {
			case SOUTH:
				new Projectile(new Location(this.getx(), this.gety() + 1), m_model.get_projectile_sprite(),
						m_model.get_projectile_sprite(), m_model, Direction.SOUTH);
				break;
			case NORTH:
				new Projectile(new Location(this.getx(), this.gety() - 1), m_model.get_projectile_sprite(),
						m_model.get_projectile_sprite(), m_model, Direction.NORTH);
				break;
			case EAST:
				new Projectile(new Location(this.getx() + 1, this.gety()), m_model.get_projectile_sprite(),
						m_model.get_projectile_sprite(), m_model, Direction.EAST);
				break;
			case WEST:
				new Projectile(new Location(this.getx() - 1, this.gety()), m_model.get_projectile_sprite(),
						m_model.get_projectile_sprite(), m_model, Direction.WEST);
				break;
			default:
				break;
			}
			m_lastHit = 0;
		}
		
	}

	public boolean isSolid() {
		return !m_model.UNDER_WATER;
	}

	public boolean isSolidUnder() {
		return m_model.UNDER_WATER;
	}

	@Override
	public EntityType getType() {
		return EntityType.PLAYER;
	}

}
