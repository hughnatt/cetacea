package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Background {

	BufferedImage m_sprite;

	Model model;

	/**
	 * @param m_sprite
	 * @param model
	 */
	protected Background(BufferedImage m_sprite, Model model) {
		super();
		this.m_sprite = m_sprite;
		this.model = model;
	}

	public abstract void step(long now);

	public abstract void paint(Graphics g);

}
