package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {


	Location m_pos;
	boolean m_solid;
	BufferedImage m_sprite;
	Model m_model;

	/**
	 * @param pos
	 * @param solid
	 * @param sprite
	 * @param model
	 */
	protected Entity(Location pos, boolean solid, BufferedImage sprite, Model model) {
		this.m_pos = pos;
		this.m_solid = solid;
		this.m_sprite = sprite;
		this.m_model = model;
		
		//Adding the Entity to the Map (for rendering and fast access to locations)
		m_model.m_map.m_tiles[m_pos.x][m_pos.y].addForeground(this);
	}
	
	public int getx() {
		return m_pos.x;
	}
	
	public int gety() {
		return m_pos.y;
	}

	/**
	 * @param g
	 * @param p_ref_map
	 */
	public abstract void paint(Graphics g, Location map_ref);


}
