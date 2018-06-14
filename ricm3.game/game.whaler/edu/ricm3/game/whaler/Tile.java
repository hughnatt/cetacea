package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import edu.ricm3.game.whaler.Entities.Entity;
import edu.ricm3.game.whaler.Entities.Entity.EntityType;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;

public class Tile {
	LinkedList<Entity> m_level;
	public Location m_loc;

	public LinkedList<Entity> getList() {
		return m_level;
	}

	/*
	 * List Representation of multiple entities superposed
	 */
	protected Tile(Location loc) {
		m_level = new LinkedList<Entity>();
		m_loc = loc;
	}

	public Iterator<Entity> iterator() {
		return m_level.iterator();
	}

	/**
	 * Add an entity at the bottom (The entity will be rendered in background)
	 * 
	 * @param e
	 */
	public void addBackground(Entity e) {
		m_level.addFirst(e);
	}

	/**
	 * Add an entity at the top (The entity will be rendered in foreground
	 * 
	 * @param e
	 */
	public void addForeground(Entity e) {
		m_level.addLast(e);
	}

	/**
	 * Remove the specified entity from the Tile
	 * 
	 * @param e
	 * @throws Tile_exception
	 *             if the entity to remove isn't found
	 */
	public void remove(Entity e) throws Tile_exception {
		boolean result = m_level.remove(e);
		if (!result) {
			throw new Tile_exception("Entity to remove not found");
		}
	}

	/**
	 * Search the first occurence of an EntityType et, from the bottom of the Tile.
	 * 
	 * @param c
	 *            the EntityType
	 * @return Entity or null if there is no entity of EntityType
	 */
	public Entity contain(EntityType et) {
		Iterator<Entity> iter = m_level.iterator();
		while (iter.hasNext()) {
			Entity E = iter.next();
			if (E.getType() == et) {
				return E;
			}
		}
		return null;
	}

	/*
	 * Return whether the Tile is solid or not A Tile is solid if it has a solid
	 * entity
	 */
	public boolean isSolid() {
		Iterator<Entity> iter = m_level.iterator();
		while (iter.hasNext()) {
			Entity E = iter.next();
			if (E.isSolid()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return whether the Tile is solid or not A Tile is solid if it has a solid
	 * entity
	 * 
	 * @return
	 */
	public boolean isSolidUnder() {
		Iterator<Entity> iter = m_level.iterator();
		while (iter.hasNext()) {
			Entity E = iter.next();
			if (E.isSolidUnder()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param now
	 */
	public void step(long now) {

	}

	/**
	 * Paint the content of the tile at the surface
	 * 
	 * @param g
	 */
	public void paint(Graphics g, Location map_ref) {
		Iterator<Entity> iter = m_level.iterator();
		Entity t;
		while (iter.hasNext()) {
			t = iter.next();
			t.paint(g, map_ref);
		}
	}

	/**
	 * paint the content of the tile underwater
	 * 
	 * @param g
	 */
	public void paint_under(Graphics g, Location map_ref) {
		Iterator<Entity> iter = m_level.iterator();
		Entity t;
		while (iter.hasNext()) {
			t = iter.next();
			t.paint_under(g, map_ref);
		}
	}
}
