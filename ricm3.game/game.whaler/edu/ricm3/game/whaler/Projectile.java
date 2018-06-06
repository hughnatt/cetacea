package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Projectile extends Mobile_Entity {

	int bloc_remaining; // before destruction, 0 = destruction at the next move

	/**
	 * @param m_pos
	 * @param m_solid
	 * @param m_sprite
	 * @param m_model
	 * @param last_move
	 * @param bloc_remaining
	 */
	protected Projectile(Location m_pos, boolean m_solid, BufferedImage m_sprite, Model m_model, long last_move,
			int bloc_remaining) {
		super(m_pos, m_solid, m_sprite, m_model, last_move);
		this.bloc_remaining = bloc_remaining;
	}

	@Override
	public void paint(Graphics g, Location p_ref_map) {

	}

	@Override
	public void step(long now) {

	}
}
