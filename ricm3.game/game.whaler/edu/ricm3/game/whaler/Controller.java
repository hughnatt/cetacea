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

import edu.ricm3.game.GameController;
import edu.ricm3.game.whaler.Model;

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

	Model m_model;
	// Button, Music

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
		// if (Options.ECHO_KEYBOARD)
		// System.out.println("KeyTyped: " + e);
		/*
		 * if (e.getKeyChar() == 'a letter') { try { something } catch
		 * (InterruptedException ex) { } }
		 */
		
		
		if (e.getKeyChar() == 'u'|| e.getKeyChar() == 'U' ) {
			m_model.swap();
		}
	      
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
		//Container cont = new Container();
		//cont.setLayout(new FlowLayout());

		// example of initialisation of a button

		/*
		 * name_button = new Button("chain_on_the_button");
		 * name_button.addActionListener(this); cont.add(name_button);
		 */

		// example of initialisation of a music

		/*
		 * File file = new File("game.sample/sprites/Future-RPG.wav"); try { this.music
		 * = new Music(file); cont.add(this.music.getControls()); } catch (Exception ex)
		 * { }
		 */

		//m_game.addSouth(cont);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Object s = e.getSource();
		/*
		 * if (s == some_button_or_other_stuff){ actions; }
		 */
	}

}
