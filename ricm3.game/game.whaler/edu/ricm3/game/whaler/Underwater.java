package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Underwater extends Background{
	
	public Underwater(BufferedImage sprite, Model model) {
		super(sprite, model);
	}
	

	@Override
	public void step(long now) {		
	}

	@Override
	public void paint(Graphics g) {
		int m_scale = 2; // Display the picture 2 times bigger than normal (for better aesthetic)
		int w = (int) (m_scale * 32);
		int h = (int) (m_scale * 32);
		
		for (int j = 0; j <= Options.DIMY_VIEW; j += m_scale) { 
			for (int i = 0; i <= Options.DIMX_VIEW; i += m_scale) {
				g.drawImage(m_sprite, (i * m_scale * 16), (j * m_scale * 16), w, h, null);
			}
		}
	}

}