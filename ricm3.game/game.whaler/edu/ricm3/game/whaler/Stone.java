package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

import com.sun.corba.se.impl.orbutil.graph.Graph;

public final class Stone extends Static_entity {

	public Stone(Model model, Location position, BufferedImage sprite) {
		super(model, position, true, true, null, sprite);
	}

	@Override
	public void paint(Graph g, Location p_ref_map) {

	}

}
