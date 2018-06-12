package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Game_exception.Automata_Exception;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public abstract class Entity {

	protected Location m_pos;
	private boolean m_solid;
	BufferedImage m_sprite;
	BufferedImage m_underSprite;
	public Model m_model;

	/**
	 * @param pos
	 *            location of the entity
	 * @param solid
	 *            the entity is solid or not
	 * @param sprite
	 *            sprite at the surface
	 * @param underSprite
	 *            sprite under water
	 * @param model
	 * @throws Game_exception
	 */
	protected Entity(Location pos, boolean solid, BufferedImage sprite, BufferedImage underSprite, Model model)
			throws Game_exception {

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

	/**
	 * Indicate whether the Entity is solid (Boats, Stone, ...) or not (Oil,
	 * Projectile, ...)
	 */
	public boolean isSolid() {
		return m_solid;
	}

	/**
	 * @param now
	 * @throws Game_exception
	 */
	public abstract void step(long now) throws Game_exception, Automata_Exception;

	/**
	 * @param g
	 * @param map_ref
	 *            location of the left-up corner of the view
	 */
	public abstract void paint(Graphics g, Location map_ref);

	/**
	 * @param g
	 * @param map_ref
	 *            location of the left-up corner of the view
	 */
	public abstract void paint_under(Graphics g, Location map_ref);

}
