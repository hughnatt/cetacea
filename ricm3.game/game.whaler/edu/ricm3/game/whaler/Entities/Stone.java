package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;

public final class Stone extends Static_Entity {

	/**
	 * @param pos
	 * @param solid
	 * @param sprite
	 * @param model
	 */
	public Stone(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model) {
		super(pos, false, sprite, underSprite, model);
	}
	
	@Override
	public void step(long now) {
	}

	@Override
	public void paint(Graphics g, Location ref_map) {
		g.drawImage(m_sprite, (this.getx() - ref_map.x) * 32, (this.gety() - ref_map.y) * 32, 32, 32, null);
	}
	
	@Override
	public void paint_under(Graphics g, Location ref_map) {
		g.drawImage(m_underSprite, (this.getx() - ref_map.x) * 32, (this.gety() - ref_map.y) * 32, 32, 32, null);
	}



}
