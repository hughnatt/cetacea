package edu.ricm3.game.whaler;

public abstract class Static_entity extends Entity {

	Location position;
	boolean collision_up;
	boolean collision_down;
	Object action;

	protected Static_entity(Location position, boolean collision_up, boolean collision_down, Object action) {
		super(position, collision_up, collision_down);
		this.action = action;
	}

	abstract public void paint();
}
