package edu.ricm3.game.whaler;

import java.awt.Graphics;

public abstract class Entity {

	Location position;
	boolean collision_up;
	boolean collision_down;

	protected Entity(Location position, boolean collision_up, boolean collision_down) {
		this.position = position;
		this.collision_up = collision_up;
		this.collision_down = collision_down;
	}

	public abstract void paint(Graphics g);

}
