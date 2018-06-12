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
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import edu.ricm3.game.GameController;
import edu.ricm3.game.whaler.Model;

import edu.ricm3.game.whaler.Model.Screen;
import edu.ricm3.game.whaler.Interpretor.IAutomata;


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
	Panel cont;
	Panel main;

	Model m_model;

	// All the buttons, to navigate between menu screens or to start the game
	JButton play;
	JButton automata;
	JButton annuler;
	JButton retour;
	JButton preference;

	JLabel infoLabel;

	// An array of scrolling box to choose the automatons
	JComboBox<?> b[];

	// A button to write the automaton assignment
	JButton valider;


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

		if (e.getKeyChar() == 'u' || e.getKeyChar() == 'U') {
			m_model.swap();
		}

		if (e.getKeyChar() == 'i' || e.getKeyChar() == 'I') {
			m_model.m_whales.get(0).pop();
		}

		if (e.getKeyChar() == 'o' || e.getKeyChar() == 'O') {

			m_model.m_whales.get(0).m_life = 20;

		}
	}

	/*
	 * keyPressed est un tableau de booléens de 127 cases, on met une case à true
	 * quand on appuie sur la touche correspondant au numéro ascii de la case
	 */

	@Override
	public void keyPressed(KeyEvent e) {
		// Attention, getKeyCode voit toutes les touches alphabétiques en majuscule de
		// façon permanente
		m_model.keyPressed[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		m_model.keyPressed[e.getKeyCode()] = false;
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
