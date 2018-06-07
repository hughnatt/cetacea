package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import edu.ricm3.game.whaler.Entities.Entity;


public class Tile {
	LinkedList<Entity> m_level;

	/*
	 * List Representation of multiple entities superposed
	 */
	protected Tile() {
		m_level = new LinkedList<Entity>();
	}

	/**
	 * Add an entity at the bottom (The entity will be rendered in background)
	 * @param e
	 */
	public void addBackground(Entity e) {
		m_level.addFirst(e);
	}
	
	/**
	 * Add an entity at the top (The entity will be rendered in foreground
	 * @param e
	 */
	public void addForeground(Entity e) {
		m_level.addLast(e);
	}
	
	/**
	 * Remove the specified entity from the Tile
	 * @param e
	 */
	protected void remove(Entity e) {
		m_level.remove(e);
	}
	
	/*
	 * Return whether the Tile is solid or not
	 * A Tile is solid if it has a solid entity
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
	 * @param now
	 */
	public void step(long now) {

	}

	/**
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
