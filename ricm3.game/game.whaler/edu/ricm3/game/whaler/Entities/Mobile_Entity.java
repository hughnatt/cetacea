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
	protected Mobile_Entity(Location pos, boolean solid, BufferedImage sprite, Model model, Direction dir)
			throws Map_exception {
		super(pos, solid, sprite, model);
		m_direction = dir;
	}

	public void up() throws Map_exception, Tile_exception {
		m_model.map().tile(this.getx(), this.gety()).remove(this);
		this.m_pos.up();
		m_model.map().tile(this.getx(), this.gety()).addForeground(this);
	}

	public void down() throws Map_exception, Tile_exception {
		m_model.map().tile(this.getx(), this.gety()).remove(this);
		this.m_pos.down();
		m_model.map().tile(this.getx(), this.gety()).addForeground(this);
	}

	public void right() throws Map_exception, Tile_exception {
		m_model.map().tile(this.getx(), this.gety()).remove(this);
		this.m_pos.right();
		m_model.map().tile(this.getx(), this.gety()).addForeground(this);
	}

	public void left() throws Map_exception, Tile_exception {
		m_model.map().tile(this.getx(), this.gety()).remove(this);
		this.m_pos.left();
		m_model.map().tile(this.getx(), this.gety()).addForeground(this);
	}

	public abstract void wizz();

	public abstract void pop();

	public abstract void hit();

}
