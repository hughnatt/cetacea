package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Whale extends Mobile_entity {

	protected Whale(Model model, Location position, BufferedImage im_sprites, int nrows, int ncols) {
		super(model, position, true, false, im_sprites, nrows, ncols);
	}

	@Override
	public void paint(Graphics g) {

	}

	@Override
	public void step(long now) {

	}
}
