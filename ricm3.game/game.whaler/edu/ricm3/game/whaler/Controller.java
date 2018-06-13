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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import edu.ricm3.game.GameController;
import edu.ricm3.game.whaler.Entities.Oil;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

/**
 * This class is to illustrate the most simple game controller. It does not
 * much, but it shows how to obtain the key strokes, mouse buttons, and mouse
 * moves.
 * 
 * With ' ', you see what you should never do, SLEEP. With '+' and '-', you can
 * add or remove some simulated overheads.
 * 
 * @author Pr. Olivier Gruber
 */

public class Controller extends GameController implements ActionListener {
	// The two main panels, the main one has the buttons and is displayed on all
	// menus, the cont one is only displayed on the automaton menu

	Model m_model;

	public Controller(Model m) {
		m_model = m;
	}

	/**
	 * Simulation step. Warning: the model has already executed its step.
	 * 
	 * @param now
	 *            is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

		/*
		 * if (e.getKeyChar() == 'u' || e.getKeyChar() == 'U') { m_model.swap(); }
		 * 
		 * if (e.getKeyChar() == 'i' || e.getKeyChar() == 'I') {
		 * m_model.m_whales.get(0).pop(); }
		 * 
		 * if (e.getKeyChar() == 'o' || e.getKeyChar() == 'O') {
		 * 
		 * m_model.m_whales.get(0).m_life = 20;
		 * 
		 * }
		 */

		if (e.getKeyChar() == 'u' || e.getKeyChar() == 'U') {
			Iterator<Oil> iter = m_model.m_oils.iterator();
			while (iter.hasNext()) {
				Oil tmp = iter.next();
				try {
					tmp.pop();
				} catch (Game_exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	/*
	 * keyPressed est un tableau de booléens de 127 cases, on met une case à true
	 * quand on appuie sur la touche correspondant au numéro de la case
	 */

	@Override
	public void keyPressed(KeyEvent e) {

		try {
			m_model.keyPressed[e.getKeyCode()] = true;
		} catch (Exception err) {
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			m_model.keyPressed[e.getKeyCode()] = false;
		} catch (Exception err) {
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public void notifyVisible() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
