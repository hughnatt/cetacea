package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Player extends Mobile_Entity {

	public static boolean UNDER_WATER = false;

	/**
	 * @param m_pos
	 * @param m_solid
	 * @param m_sprite
	 * @param m_model
	 * @param last_move
	 */
	protected Player(Location m_pos, boolean m_solid, BufferedImage m_sprite, Model m_model, long last_move) {
		super(m_pos, m_solid, m_sprite, m_model, last_move);
	}

	@Override
	public void paint(Graphics g, Location p_ref_map) {

	}

	@Override
	public void step(long now) {

	}
}
