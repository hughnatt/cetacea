package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

public abstract class Static_Entity extends Entity {

	/**
	 * @param m_pos
	 * @param m_solid
	 * @param m_sprite
	 * @param m_model
	 */
	protected Static_Entity(Location m_pos, boolean m_solid, BufferedImage m_sprite, Model m_model) {
		super(m_pos, m_solid, m_sprite, m_model);
	}

}
