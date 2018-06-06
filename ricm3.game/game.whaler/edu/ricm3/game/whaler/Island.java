package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Island extends Static_Entity {

	// TODO gérer le shop ou au moins le rechargement de vie

	@Override
	public void paint(Graphics g, Location p_ref_map) {

	}

	/**
	 * @param m_pos
	 * @param m_solid
	 * @param m_sprite
	 * @param m_model
	 */
	protected Island(Location m_pos, BufferedImage m_sprite, Model m_model) {
		super(m_pos, true, m_sprite, m_model);
	}

}
