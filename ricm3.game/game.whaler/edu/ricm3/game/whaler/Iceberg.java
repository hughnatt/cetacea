package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

import com.sun.corba.se.impl.orbutil.graph.Graph;

public final class Iceberg extends Static_entity {

	protected Iceberg(Model model, Location position, BufferedImage sprite) {
		super(model, position, true, false, null, sprite);
	}

	@Override
	public void paint(Graph g, Location p_ref_map) {

	}

}
