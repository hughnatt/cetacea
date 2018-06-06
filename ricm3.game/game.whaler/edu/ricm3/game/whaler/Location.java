package edu.ricm3.game.whaler;

public class Location {
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
