package edu.ricm3.game.whaler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
	public int nombre;
	int m_x, m_y;
	float m_scale;
	Model m_model;

	Score(Model model, int x, int y, float scale) {
		m_model = model;
		m_scale = scale;
		m_x = x;
		m_y = y;
		nombre = 0;
	}
	
	public void add() {
		nombre+=100;
	}
	
	public void sub() {
		nombre-=100;
	}
	

}
