package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Iceberg extends Static_entity {

	protected Iceberg(Model model, Location position, BufferedImage sprite) {
		super(model, position, true, false, null, sprite);
	}

	@Override
	public void paint(Graphics g, Location p_ref_map) {

	}

}
