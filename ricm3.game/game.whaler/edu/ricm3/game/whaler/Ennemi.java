package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

public abstract class Ennemi extends Mobile_Entity {

	/**
	 * @param m_pos
	 * @param m_solid
	 * @param m_sprite
	 * @param m_model
	 * @param last_move
	 */
	protected Ennemi(Location m_pos, boolean m_solid, BufferedImage m_sprite, Model m_model, long last_move) {
		super(m_pos, m_solid, m_sprite, m_model, last_move);
	}

}
