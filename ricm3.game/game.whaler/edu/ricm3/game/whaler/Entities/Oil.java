package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;

public final class Oil extends Mobile_Entity {

	boolean is_burning;

	/**
	 * @param pos
	 * @param sprite
	 * @param model
	 */
	public Oil(Location pos, BufferedImage sprite, Model model, Direction dir) {
		super(pos, false , sprite, model, dir);
		this.is_burning = false;
	}


	@Override
	public void step(long now) {

	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}
	
	@Override
	public void paint_under(Graphics g, Location map_ref) {
		
	}

}
