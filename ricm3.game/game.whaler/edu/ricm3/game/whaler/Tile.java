package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;


public class Tile {
	LinkedList<Entity> m_level;

	protected Tile() {
		m_level = new LinkedList<Entity>();
	}

	/**
	 * @param e
	 */
	protected void addBackground(Entity e) {
		m_level.addFirst(e);
	}
	
	/**
	 * @param e
	 */
	protected void addForeground(Entity e) {
		m_level.addLast(e);
	}
	
	/**
	 * @param e
	 */
	protected void remove(Entity e) {
		m_level.remove(e);
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
}
