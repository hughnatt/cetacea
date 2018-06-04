package edu.ricm3.game.whaler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ricm3.game.whaler.Game_exception.Map_exception;

public class Tile {
	List<Entity> level;

	protected Tile() {
		level = new LinkedList<Entity>();
	}

	protected void add(Entity e) throws Map_exception {
		Class<Entity> e_class = e.getClass();

		Iterator<Entity> iter = level.iterator();

		if (!iter.hasNext()) {
			level.add(e);
		}

		Entity tmp = iter.next();
		if (tmp instanceof Island) {
			throw new Map_exception("Island presented, impossible to add a new entity");
		} else if (tmp instanceof Stone) {
			throw new Map_exception("Stone presented, impossible to add a new entity");
		} else if ((tmp instanceof Iceberg) && ((!(e instanceof Player)) || (!Player.UNDER_WATER))) {
			throw new Map_exception("Iceberg presented, impossible to add a new entity,excepted a player under water");
		}

		if (!iter.hasNext()) {

			switch (e.getClass()) {
			default:
				throw new Map_exception("Unfitted entity");
			}
		}

	}
}
