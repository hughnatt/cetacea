package edu.ricm3.game.whaler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu{
	int m_idx;
	private BufferedImage m_sprite_baleine;
	private BufferedImage m_sprite_destroyer;
	private BufferedImage m_sprite_projectile;
	private BufferedImage m_fond_menu;
	int m_x, m_y;
	float m_scale;
	Model m_model;

	Menu(BufferedImage[] img, Model model, int x, int y, float scale) {
		m_model = model;
		m_x = x;
		m_y = y;
		m_sprite_baleine = img[0];
		m_sprite_destroyer = img[1];
		m_sprite_projectile = img[2];
		m_fond_menu=img[3];
		m_scale = scale;
		m_idx = 0;
	}

	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 */
	

	public void paint(Graphics g, int width, int height) {
		Image img5 = m_fond_menu;	
		g.drawImage(img5, 0, 0, width, height,null);
		
		Font f = new Font("Verdana", Font.BOLD, 48);
		g.setFont(f);
		g.setColor(Color.BLUE);
		String s = "CETACEA";
		g.drawString(s, width/2-100, 60);
		
		Image img1 = m_sprite_baleine;
		int w1 = (int) (m_scale * 100);
		int h1 = (int) (m_scale * 100);
		g.drawImage(img1, width-220, 30, w1, h1, null);

		Image img3 = m_sprite_destroyer;
		int w3 = (int) (m_scale * 75);
		int h3 = (int) (m_scale * 75);
		g.drawImage(img3, width/6, 420, w3, h3, null);

		Image img4 = m_sprite_projectile;
		int w4 = (int) (m_scale * 64);
		int h4 = (int) (m_scale * 64);
		g.drawImage(img4, width/3, 320, w4, h4, null);
		
		

	}
}
