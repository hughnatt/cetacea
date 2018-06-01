package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Island extends Static_entity {

	protected Island(Location position, Object action, BufferedImage sprite) {
		super(position, true, true, action, sprite);
	}

	@Override
	public void paint(Graphics g) {

	}

}
