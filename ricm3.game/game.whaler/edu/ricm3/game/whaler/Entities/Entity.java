package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;

public abstract class Entity {

	Location m_pos;
	boolean m_solid;
	BufferedImage m_sprite;
	BufferedImage m_underSprite;
	public Model m_model;

	/**
	 * @param pos
	 * @param solid
	 * @param sprite
	 * @param model
	 */

	protected Entity(Location pos, boolean solid, BufferedImage sprite, BufferedImage underSprite, Model model)
			throws Map_exception {

		this.m_pos = pos;
		this.m_solid = solid;
		this.m_sprite = sprite;
		this.m_model = model;

		this.m_underSprite = underSprite;

		// Adding the Entity to the Map (for rendering and fast access to locations)
		m_model.map().tile(m_pos.x, m_pos.y).addForeground(this);

	}
	
	public enum EntityType {
		DESTROYER, OIL, PLAYER, PROJECTILE, WHALE, WHALER, ISLAND, STONE, ICEBERG, VOID
	}
	
	public EntityType getType() {
		
		if(this instanceof Destroyer) {
			return EntityType.DESTROYER;
		}
		else if(this instanceof Oil) {
			return EntityType.OIL;
		}
		else if(this instanceof Player) {
			return EntityType.PLAYER;
		}
		else if(this instanceof Projectile) {
			return EntityType.PROJECTILE;
		}
		else if(this instanceof Whale) {
			return EntityType.WHALE;
		}
		else if(this instanceof Whaler) {
			return EntityType.WHALER;
		}
		else if(this instanceof Island) {
			return EntityType.ISLAND;
		}
		else if(this instanceof Stone) {
			return EntityType.STONE;
		}
		else if(this instanceof Iceberg) {
			return EntityType.ICEBERG;
		}
		else {
			return EntityType.VOID; //TODO
		}
	}
	
	public int getx() {
		return m_pos.x;
	}

	public int gety() {
		return m_pos.y;
	}

	/*
	 * Indicate whether the Entity is solid (Boats, Stone, ...) or not (Oil,
	 * Projectile, ...)
	 */
	public boolean isSolid() {
		return m_solid;
	}

	/**
	 * @param now
	 * @throws Exception 
	 */
	public abstract void step(long now) throws Exception;

	/**
	 * @param g
	 * @param map_ref
	 */
	public abstract void paint(Graphics g, Location map_ref);

	/**
	 * @param g
	 * @param map_ref
	 */
	public abstract void paint_under(Graphics g, Location map_ref);

}
