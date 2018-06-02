package edu.ricm3.game.whaler;

public class Location {
	int x, y; // number of cases, not pixels

	protected Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// TODO down et right selon la taille de la map

	public void up() {
		if (this.y > 0) {
			this.y--;
		}
	}

	public void left() {
		if (this.x > 0) {
			this.x--;
		}
	}
}
