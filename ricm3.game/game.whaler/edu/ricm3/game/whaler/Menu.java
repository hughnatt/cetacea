package edu.ricm3.game.whaler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Menu{
	int m_idx;
	BufferedImage m_sprite_baleine;
	BufferedImage m_sprite_button;
	BufferedImage m_sprite_gouvernail;
	BufferedImage m_sprite_destroyer;
	BufferedImage m_sprite_projectile;
	int m_x, m_y;
	float m_scale;
	Model m_model;

	Menu(Model model, int x, int y, float scale) {
		m_model = model;
		m_x = x;
		m_y = y;
		m_sprite_button = m_model.m_buttonSprite;
		m_sprite_baleine = m_model.m_baleinemenuSprite;
		m_sprite_destroyer = m_model.m_destroyer_menuSprite;
		m_sprite_projectile = m_model.m_projectile_menuSprite;
		m_scale = scale;
		m_idx = 0;
	}

	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 */
	

	public void paint(Graphics g, int width) {
		Font f = new Font("Verdana", Font.BOLD, 48);
		g.setFont(f);
		g.setColor(Color.BLUE);
		String s = "CETACEA";
		g.drawString(s, width/2-100, 80);
		
		Image img1 = m_sprite_baleine;
		int w1 = (int) (m_scale * 129);
		int h1 = (int) (m_scale * 100);
		g.drawImage(img1, (width/2)-w1, 130, w1, h1, null);

		Image img3 = m_sprite_destroyer;
		int w3 = (int) (m_scale * 64);
		int h3 = (int) (m_scale * 64);
		g.drawImage(img3, 80, 430, w3, h3, null);

		Image img4 = m_sprite_projectile;
		int w4 = (int) (m_scale * 64);
		int h4 = (int) (m_scale * 64);
		g.drawImage(img4, 230, 330, w4, h4, null);

	}

}
