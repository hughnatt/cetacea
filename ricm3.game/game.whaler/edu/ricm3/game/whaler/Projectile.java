package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

import com.sun.corba.se.impl.orbutil.graph.Graph;

public class Projectile extends Mobile_entity {

	protected Projectile(Model model, Location position, BufferedImage im_sprites, int nrows, int ncols) {
		super(model, position, false, false, im_sprites, nrows, ncols);
	}

	@Override
	public void paint(Graph g, Location p_ref_map) {

	}

	@Override
	public void step(long now) {

	}
}
