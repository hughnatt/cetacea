package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Background {

	BufferedImage m_sprite;

	Model m_model;

	/**
	 * @param m_sprite
	 * @param model
	 */
	protected Background(BufferedImage sprite, Model model) {
		this.m_sprite = sprite;
		this.m_model = model;
	}

	/**
	 * Called at each GameUI Model Tick
	 * @param now
	 */
	public abstract void step(long now);

	/**
	 * Called at each GameUI View Paint
	 * @param g
	 */
	public abstract void paint(Graphics g);

}
