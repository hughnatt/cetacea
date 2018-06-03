package edu.ricm3.game.whaler;

import java.util.LinkedList;
import java.util.List;

public class Tile {
	List<Entity> level;

	protected Tile() {
		level = new LinkedList<Entity>();
	}
}
