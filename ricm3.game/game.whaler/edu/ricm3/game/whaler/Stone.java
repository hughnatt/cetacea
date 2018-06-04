package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Stone extends Static_entity {

	public Stone(Model model, Location position, BufferedImage sprite) {
		super(model, position, true, true, null, sprite);
	}

	@Override
	public void paint(Graphics g, Location p_ref_map) {

	}

}
