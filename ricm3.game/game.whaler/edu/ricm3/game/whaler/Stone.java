package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Stone extends Static_Entity {

	/**
	 * @param m_pos
	 * @param m_solid
	 * @param m_sprite
	 * @param m_model
	 */
	protected Stone(Location m_pos, BufferedImage m_sprite, Model m_model) {
		super(m_pos, true, m_sprite, m_model);
	}

	@Override
	public void paint(Graphics g, Location p_ref_map) {

	}

}
