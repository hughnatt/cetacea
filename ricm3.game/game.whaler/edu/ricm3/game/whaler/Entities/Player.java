package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Game_exception.Automata_Exception;
import edu.ricm3.game.whaler.Game_exception.Game_exception;
import edu.ricm3.game.whaler.Interpretor.IAutomata;

public final class Player extends Mobile_Entity {

	BufferedImage m_playerNorth;
	BufferedImage m_playerSouth;
	BufferedImage m_playerEast;
	BufferedImage m_playerWest;

	BufferedImage m_playerNorthUnder;
	BufferedImage m_playerSouthUnder;
	BufferedImage m_playerEastUnder;
	BufferedImage m_playerWestUnder;

	int oil_jauge;

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

	public Player(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir,
			IAutomata auto) throws Game_exception {
		super(pos, true, sprite, underSprite, model, dir, Options.PLAYER_LIFE);

		m_automata = auto;

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
	}

	@Override
	public void destroy() throws Game_exception {
		m_model.map().tile(m_pos).remove(this);

		// TODO : END OF THE GAME

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
		long elapsed = now - m_lastStep;
		if (elapsed > 50L) {
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
	public void pop() {
		m_model.swap();
	}

	@Override
	public void wizz() throws Game_exception {
		Entity result = m_model.map().tile(this.pos_front()).contain(EntityType.OIL);
		if (result != null) {
			Oil will_burn = (Oil) result;
			will_burn.is_burning = true;
		}
	}

	public void pick() {
		this.oil_jauge += Options.OIL_PICKED;
		// TODO: faire disparaitre le pétrole
	}

	@Override
	public void hit() throws Game_exception {
		switch (m_direction) {
		case SOUTH:
			new Projectile(new Location(this.getx(), this.gety() + 1), m_model.get_projectile_sprite(),
					m_model.get_projectile_sprite(), m_model, Direction.SOUTH, 6, 3);
			break;
		case NORTH:
			new Projectile(new Location(this.getx(), this.gety() - 1), m_model.get_projectile_sprite(),
					m_model.get_projectile_sprite(), m_model, Direction.NORTH, 6, 3);
			break;
		case EAST:
			new Projectile(new Location(this.getx() + 1, this.gety()), m_model.get_projectile_sprite(),
					m_model.get_projectile_sprite(), m_model, Direction.EAST, 6, 3);
			break;
		case WEST:
			new Projectile(new Location(this.getx() - 1, this.gety()), m_model.get_projectile_sprite(),
					m_model.get_projectile_sprite(), m_model, Direction.WEST, 6, 3);
			break;
		default:
			break;
		}
	}

}
