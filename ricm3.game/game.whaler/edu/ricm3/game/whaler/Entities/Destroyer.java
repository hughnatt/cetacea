package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Game_exception.Map_exception;

public class Destroyer extends Mobile_Entity {

	BufferedImage m_destroyerNorth;
	BufferedImage m_destroyerSouth;
	BufferedImage m_destroyerEast;
	BufferedImage m_destroyerWest;

	int m_life;

	/**
	 * @param pos
	 *            Initial Position of the Destroyer
	 * @param sprite
	 *            Destroyer Sprite
	 * @param model
	 *            Internal Model
	 * @param dir
	 *            Initial Direction of the Destroyer
	 */

	public Destroyer(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir)
			throws Map_exception {
		super(pos, true, sprite, underSprite, model, dir);
		m_life = Options.DESTROYER_LIFE;

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
		case SOUTH:
			m_sprite = m_destroyerSouth;
			break;
		}

	}

	private void loadSprites() {
		m_destroyerSouth = m_sprite.getSubimage(0, 0, 32, 32);
		m_destroyerWest = m_sprite.getSubimage(0, 32, 32, 32);
		;
		m_destroyerEast = m_sprite.getSubimage(0, 64, 32, 32);
		;
		m_destroyerNorth = m_sprite.getSubimage(0, 96, 32, 32);
		;
	}

	@Override
	public void step(long now) {

	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {

	}

}
