package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Burning_oil extends Mobile_entity {

	protected Burning_oil(Model model, Location position, BufferedImage im_sprites, int nrows, int ncols) {
		super(model, position, false, false, im_sprites, nrows, ncols);
	}

	@Override
	public void paint(Graphics g, Location p_ref_map) {

	}

	@Override
	public void step(long now) {

	}
}
