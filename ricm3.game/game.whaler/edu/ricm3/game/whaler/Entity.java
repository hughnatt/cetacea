package edu.ricm3.game.whaler;

import java.awt.Graphics;

public abstract class Entity {

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
