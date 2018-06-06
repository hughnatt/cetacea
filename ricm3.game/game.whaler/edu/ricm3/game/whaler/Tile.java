package edu.ricm3.game.whaler;

import java.util.LinkedList;

import edu.ricm3.game.whaler.Game_exception.Map_exception;

public class Tile {
	LinkedList<Entity> level;

	protected Tile() {
		level = new LinkedList<Entity>();
	}

	protected void add(Entity e) throws Map_exception {

		if (level.size() == 0) {
			level.add(e);
			return;
		}

		if (e instanceof Static_entity) {
			throw new Map_exception("Impossible to add a static_entity on a no-empty bloc");
		}

		Entity tmp1 = level.get(1);

		if ((e.getClass() == tmp1.getClass()) && (!(e instanceof Projectile))) {
			throw new Map_exception("Entity already presented");
		}

		if (level.size() == 1) {
			if (tmp1 instanceof Island) {
				throw new Map_exception("Island presented, impossible to add the new entity");
			} else if (tmp1 instanceof Stone) {
				throw new Map_exception("Stone presented, impossible to add the new entity");
			} else if ((tmp1 instanceof Iceberg) && ((!(e instanceof Player)) || (!Player.UNDER_WATER))) {
				throw new Map_exception(
						"Iceberg presented, impossible to add the new entity,excepted a player under water");
			}

			if ((tmp1 instanceof Oil) || (tmp1 instanceof Iceberg)) {
				level.addLast(e);
				return;
			} else if ((tmp1 instanceof Player) || (tmp1 instanceof Ennemi) || (tmp1 instanceof Whale)) {
				if (e instanceof Projectile) {
					level.addLast(e);
				} else if (e instanceof Oil) {
					level.addFirst(e);
				} else {
					throw new Map_exception("Mobile entity opresented, impossible to add the new entity");
				}
				return;
			} else if (tmp1 instanceof Projectile) {
				if (e instanceof Projectile) {
					level.addLast(e);
				} else {
					level.addFirst(e);
				}
				return;
			} else {
				throw new Map_exception("Unfitted entity");
			}

		} else if (level.size() == 2) {
			Entity tmp2 = level.get(2);

			if ((e.getClass() == tmp2.getClass()) && (!(e instanceof Projectile))) {
				throw new Map_exception("Entity already presented");
			}
			if (((tmp1 instanceof Iceberg) && (tmp2 instanceof Player) && (Player.UNDER_WATER))
					|| (((tmp1 instanceof Player) || (tmp1 instanceof Ennemi) || (tmp1 instanceof Whale))
							&& (tmp2 instanceof Oil))) {
				if (e instanceof Projectile) {
					level.addLast(e);
					return;
				}
				throw new Map_exception("Block available only for a new projectile");
			} else if ((tmp1 instanceof Oil) && (tmp2 instanceof Projectile)) {
				level.add(2, e);
				return;
			} else if ((tmp1 instanceof Projectile) && (tmp2 instanceof Projectile)) {
				level.addFirst(e);
				return;
			} else {
				throw new Map_exception("Unfitted entity");
			}
		} else {
			if (e instanceof Projectile) {
				level.addLast(e);
				return;
			}
			throw new Map_exception("Too many entities for this new entity");
		}
	}
}
