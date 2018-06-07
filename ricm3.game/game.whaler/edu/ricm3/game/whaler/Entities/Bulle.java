package edu.ricm3.game.whaler.Entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import java.awt.Graphics;

public class Bulle extends Static_Entity {
	
	/* BufferedImage Array to store the sprite */
	int m_sprite_idx;
	BufferedImage[] m_sprites;

	/* Controlling Step Speed */
	long m_lastStep;
	long m_stepSpeed = 100L; // Medium Animation Speed
	
	public Bulle(Location pos, BufferedImage sprite, Model model) {
		super(pos, false, sprite, model);
		
		// Default sprite index is 0 (first image)
		m_sprite_idx = 0;

		// Loading the different part of the sprite
		assert (m_sprite.getHeight() == 32 * 4);
		assert (m_sprite.getWidth() == 32);

		m_sprites = new BufferedImage[4];
		
		for (int i = 0; i < 4; i++) {
			m_sprites[i] = m_sprite.getSubimage(0, 32 * i, 32, 32);
		}
		
	}
	
	public void step(long now) {
		long elapsed = now - m_lastStep;

		if (elapsed > m_stepSpeed) {
			m_lastStep = now;
			m_sprite_idx = (m_sprite_idx + 1) % m_sprites.length;
		}
		
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {
		BufferedImage img = m_sprites[m_sprite_idx];
		g.drawImage(img, (this.getx() - map_ref.x) * 32, (this.gety() - map_ref.y) * 32, 32, 32, null);
	}
}
