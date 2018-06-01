package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Projectile extends Mobile_entity {

	protected Projectile(Location position, BufferedImage im_sprites, int nrows, int ncols) {
		super(position, false, false, im_sprites, nrows, ncols);
	}

	@Override
	public void paint(Graphics g) {

	}

	@Override
	public void step(long now) {

	}
}
