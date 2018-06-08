package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public final class Water extends Background {

	/* BufferedImage Array to store the sprite */
	int m_sprite_idx;
	BufferedImage[] m_sprites;

	/* Controlling Step Speed */
	long m_lastStep;
	long m_stepSpeed = 100L; // Medium Animation Speed

	/**
	 * Constructeur pour Water
	 * 
	 * @param m_sprite
	 *            Sprite d'eau (32 images taille 32*32px)
	 * @param model
	 * @throws SpriteException
	 */
	protected Water(BufferedImage sprite, Model model) {

		super(sprite, model);

		// Default sprite index is 0 (first image)
		m_sprite_idx = 0;

		// Loading the different part of the sprite
		assert (m_sprite.getHeight() == 32 * 32);
		assert (m_sprite.getWidth() == 32);

		m_sprites = new BufferedImage[32];
		
		for (int i = 0; i < 32; i++) {
			m_sprites[i] = m_sprite.getSubimage(0, 32 * i, 32, 32);
		}

	}

	/* (non-Javadoc)
	 * @see edu.ricm3.game.whaler.Background#step(long)
	 */
	@Override
	public void step(long now) {
		long elapsed = now - m_lastStep;

		if (elapsed > m_stepSpeed) {
			m_lastStep = now;
			m_sprite_idx = (m_sprite_idx + 1) % m_sprites.length;
		}
	}

	/* (non-Javadoc)
	 * @see edu.ricm3.game.whaler.Background#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		BufferedImage img = m_sprites[m_sprite_idx];
		int m_scale = 2; // Display the picture 2 times bigger than normal (for better aesthetic)
		int w = (int) (m_scale * 32);
		int h = (int) (m_scale * 32);
		
		for (int j = 0; j <= Options.DIMY_VIEW; j += m_scale) { 
			for (int i = 0; i <= Options.DIMX_VIEW; i += m_scale) {
				g.drawImage(img, (i * m_scale * 16), (j * m_scale * 16), w, h, null);
			}
		}
	}
	
}
