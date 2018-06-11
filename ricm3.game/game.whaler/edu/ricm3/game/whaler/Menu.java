package edu.ricm3.game.whaler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu {
	int m_idx;
	int m_x, m_y;
	float m_scale;
	Model m_model;
	
	
	BufferedImage m_fondmenu;
	BufferedImage m_whaleSprite;
	BufferedImage m_destroyerSprite;
	BufferedImage m_projectileSprite;

	Menu(Model model, int x, int y, float scale) {
		m_model = model;
		m_x = x;
		m_y = y;
		m_scale = scale;
		m_idx = 0;
		
		loadSprites();
	}

	
	private void loadSprites() {
		
		File imageFile;
		
		imageFile = new File("game.whaler/sprites/Destroyer_menu.png");
		try {
			m_destroyerSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		imageFile = new File("game.whaler/sprites/Projectile_menu.png");
		try {
			m_projectileSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	
		imageFile = new File("game.whaler/sprites/help.png");
		try {
			m_whaleSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.whaler/sprites/fond.png");
		try {
			m_fondmenu = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	
	}
	
	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 */

	public void paint(Graphics g, int width, int height) {
		Image img5 = m_fondmenu;
		g.drawImage(img5, 0, 0, width, height, null);

		Font f = new Font("Verdana", Font.BOLD, 48);
		g.setFont(f);
		g.setColor(Color.BLUE);
		String s = "CETACEA";
		g.drawString(s, width / 2 - 100, 60);

		Image img1 = m_whaleSprite;
		int w1 = (int) (m_scale * 100);
		int h1 = (int) (m_scale * 100);
		g.drawImage(img1, width - 220, 30, w1, h1, null);

		
		Image img3 = m_destroyerSprite;
		int w3 = (int) (m_scale * 75);
		int h3 = (int) (m_scale * 75);
		g.drawImage(img3, width / 6, 420, w3, h3, null);

		
		
		Image img4 = m_projectileSprite;
		int w4 = (int) (m_scale * 64);
		int h4 = (int) (m_scale * 64);
		g.drawImage(img4, width / 3, 320, w4, h4, null);

	}
}
