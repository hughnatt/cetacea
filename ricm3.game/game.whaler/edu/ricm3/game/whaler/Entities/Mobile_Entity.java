package edu.ricm3.game.whaler.Entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;

public abstract class Mobile_Entity extends Entity {

	long m_lastStep;
	Direction m_direction;

	/**
	 * @param pos
	 * @param solid
	 * @param sprite
	 * @param model
	 */
	protected Mobile_Entity(Location pos, boolean solid, BufferedImage sprite, BufferedImage underSprite, Model model,
			Direction dir) throws Map_exception {
		super(pos, solid, sprite, underSprite, model);
		m_direction = dir;
	}

	/**
	 * @throws Map_exception
	 * @throws Tile_exception
	 */
	public void up() throws Map_exception, Tile_exception {
		m_model.map().tile(this.getx(), this.gety()).remove(this); // We remove the entity from the map
		this.m_pos.up(); // We update its location
		m_model.map().tile(this.getx(), this.gety()).addForeground(this); // We add it at the top of the Tile for its
																			// new location
	}

	/**
	 * @throws Map_exception
	 * @throws Tile_exception
	 */
	public void down() throws Map_exception, Tile_exception {
		m_model.map().tile(this.getx(), this.gety()).remove(this);
		this.m_pos.down();
		m_model.map().tile(this.getx(), this.gety()).addForeground(this);
	}

	/**
	 * @throws Map_exception
	 * @throws Tile_exception
	 */
	public void right() throws Map_exception, Tile_exception {
		m_model.map().tile(this.getx(), this.gety()).remove(this);
		this.m_pos.right();
		m_model.map().tile(this.getx(), this.gety()).addForeground(this);
	}

	/**
	 * @throws Map_exception
	 * @throws Tile_exception
	 */
	public void left() throws Map_exception, Tile_exception {
		m_model.map().tile(this.getx(), this.gety()).remove(this);
		this.m_pos.left();
		m_model.map().tile(this.getx(), this.gety()).addForeground(this);
	}

	public abstract void pop();

	public abstract void wizz();

	public abstract void hit();

}
