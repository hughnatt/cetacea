package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

public abstract class Mobile_Entity extends Entity {

	protected enum Direction {
		NORTH, SOUTH, WEST, EAST
	}

	long m_lastStep;

	/**
	 * @param m_pos
	 * @param m_solid
	 * @param m_sprite
	 * @param m_model
	 * @param last_move
	 */
	protected Mobile_Entity(Location m_pos, boolean m_solid, BufferedImage m_sprite, Model m_model) {
		super(m_pos, m_solid, m_sprite, m_model);
	}

	/**
	 * @param now
	 */
	public abstract void step(long now);
}
