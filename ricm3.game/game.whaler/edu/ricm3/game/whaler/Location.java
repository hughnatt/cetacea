package edu.ricm3.game.whaler;

import edu.ricm3.game.whaler.Game_exception.Location_exception;

public class Location {
	public int x; // number of cases, not pixels
	public int y;

	/**
	 * @param new_x
	 * @throws Location_exception
	 * 
	 *             function to check the validity of the x value
	 */
	private void check_x(int new_x) throws Location_exception {
		if ((new_x < 0) | (new_x >= Options.DIMX_MAP)) {
			throw new Location_exception("unfitted x value");
		}
	}

	/**
	 * @param new_y
	 * @throws Location_exception
	 * 
	 *             function to check the validity of the y value
	 */
	private void check_y(int new_y) throws Location_exception {
		if ((new_y < 0) | (new_y >= Options.DIMY_MAP)) {
			throw new Location_exception("unfitted y value");
		}
	}

	public Location(int x, int y) throws Location_exception {

		check_x(x);
		check_y(y);

		this.x = x;
		this.y = y;
	}

	public Location(Location l) throws Location_exception {
		this(l.x,l.y);
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
	
	public Location getEast() throws Location_exception {
		return new Location(this.x + 1, this.y);
	}
	public Location getWest() throws Location_exception {
		return new Location(this.x - 1, this.y);
	}
	public Location getNorth() throws Location_exception {	
		return new Location(this.x, this.y - 1);
	}
	public Location getSouth() throws Location_exception {	
		return new Location(this.x, this.y + 1);
	}
	
	
	
	public String toString(){
		return "( "+x+" , "+y+" )";
	}
}
