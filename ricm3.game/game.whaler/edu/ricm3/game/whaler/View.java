/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.ricm3.game.whaler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.ricm3.game.GameView;

public class View extends GameView {

	private static final long serialVersionUID = 1L;

	Color m_background = Color.blue;
	long m_last;
	int m_npaints;
	int m_fps;
	Model m_model;
	Controller m_ctr;
	Boolean afficherOptions = true;

	public View(Model m, Controller c) {
		m_model = m;
		m_ctr = c;
	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, "npaints=" + m_npaints);
		m_npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (!(m_ctr.op)) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			m_model.m_menu.paint(g, getWidth());
		} else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			Font f = new Font("Verdana", Font.BOLD, 35);
			g.setFont(f);
			g.setColor(Color.BLACK);
			g.drawString("Baleine", 200, 40);
			g.drawString("Baleinier", 200, 120);
			g.drawString("Destroyer", 200, 200);
			g.drawString("Joueur", 200, 270);
			g.drawString("Pétrole", 200, 340);
			g.drawString("Projectile", 200, 420);
		}
		if (m_ctr.GameOn) {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, getWidth(), getHeight());

			// call the method paint on all the instances you want to print
			m_model.m_ocean.paint(g);

			// Viewport paint of the map
			m_model.m_map.paint(g);
		}
	}
}
