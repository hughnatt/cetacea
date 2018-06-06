package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Oil extends Mobile_Entity {

	boolean is_burning;

	/**
	 * @param m_pos
	 * @param m_solid
	 * @param m_sprite
	 * @param m_model
	 * @param last_move
	 * @param is_burning
	 */
	protected Oil(Location m_pos, boolean m_solid, BufferedImage m_sprite, Model m_model, long last_move,
			boolean is_burning) {
		super(m_pos, m_solid, m_sprite, m_model);
		this.is_burning = is_burning;
	}

	@Override
	public void paint(Graphics g, Location p_ref_map) {

	}

	@Override
	public void step(long now) {

	}

}
