package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;

public final class Island extends Static_Entity {

	// TODO gérer le shop ou au moins le rechargement de vie



	/**
	 * @param m_pos
	 * @param m_solid
	 * @param m_sprite
	 * @param m_model
	 */
	public Island(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model) {
		super(pos, true, sprite, underSprite, model);
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
