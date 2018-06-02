package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

public abstract class Static_entity extends Entity {

	Location position;
	boolean collision_up;
	boolean collision_down;
	Object action;

	BufferedImage sprite; // sprite
	int width_sprite; // size
	int height_sprite;

	protected Static_entity(Model model, Location position, boolean collision_up, boolean collision_down,
			Object action, BufferedImage sprite) {
		super(model, position, collision_up, collision_down);
		this.action = action;
		this.sprite = sprite;
	}

}
