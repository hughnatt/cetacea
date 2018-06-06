package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Island extends Static_entity {

	// TODO gérer le shop ou au moins le rechargement de vie

	protected Island(Model model, Location position, Object action, BufferedImage sprite) {
		super(model, position, true, true, sprite);
	}

	@Override
	public void paint(Graphics g, Location p_ref_map) {

	}

}
