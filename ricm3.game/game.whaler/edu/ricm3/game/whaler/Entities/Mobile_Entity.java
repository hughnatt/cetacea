package edu.ricm3.game.whaler.Entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;

public abstract class Mobile_Entity extends Entity {


	long m_lastStep;
	Direction m_direction;

	/**
	 * @param pos
	 * @param solid
	 * @param sprite
	 * @param model
	 */
	protected Mobile_Entity(Location pos, boolean solid, BufferedImage sprite, Model model, Direction dir) {
		super(pos, solid, sprite, model);
		m_direction = dir;
	}

	/*
	public abstract void up();
	public abstract void down();
	public abstract void right();
	public abstract void left();
	public abstract void wizz();
	public abstract void pop();
	public abstract void hit();
	*/
	
}
