package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;

public final class Whale extends Mobile_Entity {

	/**
	 * @param pos
	 * @param solid
	 * @param sprite
	 * @param model
	 */
	public Whale(Location pos, BufferedImage sprite, Model model, Direction dir) {
		super(pos, true, sprite, model, dir);
	}

	

	@Override
	public void step(long now) {

	}
	
	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}
}
