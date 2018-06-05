package edu.ricm3.game.whaler;

import java.awt.Graphics;

public abstract class Entity {

	protected class Location {
		int x, y; // number of cases, not pixels

		protected Location(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void up() {
			if (this.y > 0) {
				this.y--;
			}
		}

		public void down() {
			if (this.y < Options.DIMY_MAP) {
				y++;
			}
		}

		public void left() {
			if (this.x > 0) {
				this.x--;
			}
		}

		public void right() {
			if (this.x < Options.DIMX_MAP) {
				x++;
			}
		}
	}

	Location position;
	boolean collision_up;
	boolean collision_down;
	Model model;

	// TODO ajouter commentaires eclipses paramètres et return

	protected Entity(Model model, Location position, boolean collision_up, boolean collision_down) {
		this.model = model;
		this.position = position;
		this.collision_up = collision_up;
		this.collision_down = collision_down;
	}

	public abstract void paint(Graphics g, Location p_ref_map);

}
