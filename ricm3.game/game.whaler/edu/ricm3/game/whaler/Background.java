package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Background {

	BufferedImage m_sprite;
	Model m_model;

	/**
	 * @param sprite
	 * @param model
	 */
	protected Background(BufferedImage sprite, Model model) {
		m_sprite = sprite;
		m_model = model;
	}

	/**
	 * For Background Animation
	 * @param now
	 */
	public abstract void step(long now);

	
	/**
	 * Paint the Background
	 * @param g
	 */
	public abstract void paint(Graphics g);

}
