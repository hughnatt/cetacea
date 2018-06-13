package edu.ricm3.game.whaler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Score {
	int nombre;
	int m_x, m_y;
	float m_scale;
	Model m_model;

	Score(Model model, int x, int y, float scale) {
		m_model = model;
		m_scale = scale;
		m_x = x;
		m_y = y;
		nombre=0;
	}
	
	public void step(long now){
		if(now>0)
			nombre+=100;
		else
			nombre-=100;
	}
	
	
	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 */
	void paint(Graphics g) {
		Font f = new Font("Verdana", Font.BOLD, 32);
		g.setFont(f);
		g.setColor(Color.WHITE);
		String s= "Score: " + Integer.toString(nombre);
		g.drawString(s, 400, 24);
		}
}
