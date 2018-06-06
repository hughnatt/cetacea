package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.util.LinkedList;

public class Tile {
	LinkedList<Entity> m_level;

	protected Tile() {
		m_level = new LinkedList<Entity>();
	}

	/**
	 * @param e
	 */
	protected void add(Entity e) {
	}

	/**
	 * @param now
	 */
	public void step(long now) {

	}

	/**
	 * @param g
	 */
	public void paint(Graphics g) {

	}
}
