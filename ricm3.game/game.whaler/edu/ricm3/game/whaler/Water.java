package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Water extends Background {

	/**
	 * @param sprites
	 * @param width_sprite
	 * @param height_sprite
	 * @param num_sprite
	 * @param max_sprite
	 * @param model
	 */
	protected Water(BufferedImage[] sprites, int width_sprite, int height_sprite, int num_sprite, int max_sprite,
			Model model) {
		super(sprites, width_sprite, height_sprite, num_sprite, max_sprite, model);
	}

	@Override
	public void step(long now) {

	}

	@Override
	public void paint(Graphics g, Location p_ref_map) {

	}
}
