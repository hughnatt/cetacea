package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

import com.sun.corba.se.impl.orbutil.graph.Graph;

public final class Island extends Static_entity {

	protected Island(Model model, Location position, Object action, BufferedImage sprite) {
		super(model, position, true, true, action, sprite);
	}

	@Override
	public void paint(Graph g, Location p_ref_map) {

	}

}
