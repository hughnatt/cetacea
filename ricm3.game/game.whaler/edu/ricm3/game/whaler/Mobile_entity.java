package edu.ricm3.game.whaler;

public abstract class Mobile_entity extends Entity {

	protected Mobile_entity(Location position, boolean collision_up, boolean collision_down) {
		super(position, collision_up, collision_down);
	}
}
