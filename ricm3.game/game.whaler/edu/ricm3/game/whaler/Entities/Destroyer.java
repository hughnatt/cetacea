package edu.ricm3.game.whaler.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Entities.Entity.EntityType;
import edu.ricm3.game.whaler.Game_exception.Automata_Exception;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public class Destroyer extends MobileEntity {

	BufferedImage m_destroyerNorth;
	BufferedImage m_destroyerSouth;
	BufferedImage m_destroyerEast;
	BufferedImage m_destroyerWest;


	private BufferedImage[] m_explode;
	private int m_explode_idx;

	private boolean m_exploding;
	long m_lastAnim;

	int m_damage;
	private long m_speed;

	/**
	 * @param pos
	 *            Initial Position of the Destroyer
	 * @param sprite
	 *            Destroyer Sprite
	 * @param underSprite
	 * @param model
	 *            Internal Model
	 * @param dir
	 *            Initial Direction of the Destroyer
	 * @throws Game_exception
	 */

	public Destroyer(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir)
			throws Game_exception {
		super(pos, true, sprite, underSprite, model, dir, Options.DESTROYER_LIFE);
		m_speed = Options.DESTROYER_SPD_STANDARD;

		m_exploding = false;
		m_explode_idx = 0;
		m_automata = m_model.getAutomata(this);

		loadSprites();

		switch (dir) {
		case EAST:
			m_sprite = m_destroyerEast;
			break;
		case WEST:
			m_sprite = m_destroyerWest;
			break;
		case NORTH:
			m_sprite = m_destroyerNorth;
			break;

		default: // direction by default, SOUTH

			m_sprite = m_destroyerSouth;
			break;
		}

	}

	
	
	@Override
	public void destroy() throws Game_exception {
		m_model.map().tile(m_pos).remove(this);
		m_model.m_garbage.add(this);
		m_model.m_score.step(1);
	}

	private void loadSprites() {
		m_destroyerSouth = m_sprite.getSubimage(0, 0, 32, 32);
		m_destroyerWest = m_sprite.getSubimage(0, 32, 32, 32);
		;
		m_destroyerEast = m_sprite.getSubimage(0, 64, 32, 32);
		;
		m_destroyerNorth = m_sprite.getSubimage(0, 96, 32, 32);
		;

		m_sprite = m_model.get_boom_sprite();

		m_explode = new BufferedImage[8];
		for (int i = 0; i < 8; i++) {
			m_explode[i] = m_sprite.getSubimage(32 * i, 0, 32, 32);
		}
	}

	@Override

	public void step(long now) throws Game_exception {
		stepAnim(now);

		long elapsed = now - m_lastStep;

		if (!m_exploding && elapsed > m_speed) {
			m_lastStep = now;

			try {
				m_automata.step(m_model, this);
			} catch (Automata_Exception e) {
				e.printStackTrace();
			} catch (Game_exception e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (m_life <= 0) {
				m_exploding = true;
			}
		}
	}

	public void stepAnim(long now) throws Game_exception {
		long elapsed = now - m_lastAnim;
		if (elapsed > 100L) {
			m_lastAnim = now;
			if (m_exploding) {
				m_explode_idx++;

				if (m_explode_idx == 8) {
					this.destroy();
				}
			}

		}
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		if (m_exploding) {

			g.drawImage(m_explode[m_explode_idx], (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
		} else {
			g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
			g.setColor(Color.RED);
			g.fillRect((m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32,
					(int) (((double) m_life / (double) (Options.DESTROYER_LIFE)) * 32), 2);
		}

	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {
		// nothing
	}

	@Override
	public void pop() {
		// TODO
	}

	@Override
	public void wizz() {
		this.m_speed = Options.DESTROYER_SPD_IMPROVED;
	}

	@Override
	public void hit() {


	}

	@Override
	public void pick() throws Game_exception {
		//
		
	}



	@Override
	public EntityType getType() {
		return EntityType.DESTROYER;
	}
}
