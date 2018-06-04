package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

import com.sun.corba.se.impl.orbutil.graph.Graph;

public final class Player extends Mobile_entity {

	protected Player(Model model, Location position, BufferedImage im_sprites, int nrows, int ncols) {
		super(model, position, true, true, im_sprites, nrows, ncols);
	}

	@Override
	public void paint(Graph g, Location p_ref_map) {

	}

	@Override
	public void step(long now) {

	}
}
