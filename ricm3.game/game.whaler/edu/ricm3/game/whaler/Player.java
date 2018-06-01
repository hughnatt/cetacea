package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Player extends Mobile_entity {

	protected Player(Location position, BufferedImage im_sprites, int nrows, int ncols) {
		super(position, true, true, im_sprites, nrows, ncols);
	}

	@Override
	public void paint(Graphics g) {

	}
	
	@Override
	public void step(long now) {
		
	}
}
