package edu.ricm3.game.whaler;

/**
 * Cardinal Directions and Relative Position
 */
public enum Direction {
	NORTH, SOUTH, WEST, EAST, FORWARD, BACKWARD, RIGHT, LEFT;

	public Direction RotationToRight() {
		switch (this) {
		case NORTH:
			return EAST;
		case EAST:
			return SOUTH;
		case SOUTH:
			return WEST;
		case WEST:
			return NORTH;
		default:
			System.out.println("Unknown Direction, will be interpreted as NORTH");
			return Direction.NORTH;
		}
	}

	public Direction RotationToLeft() {
		switch (this) {
		case NORTH:
			return WEST;
		case EAST:
			return NORTH;
		case SOUTH:
			return EAST;
		case WEST:
			return SOUTH;
		default:
			System.out.println("Unknown Direction, will be interpreted as NORTH");
			return Direction.NORTH;
		}
	}
}
