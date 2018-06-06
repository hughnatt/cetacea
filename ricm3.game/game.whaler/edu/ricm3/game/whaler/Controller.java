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

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import edu.ricm3.game.GameController;

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
	Panel cont;
	Model m_model;
	JButton play;
	Music m_player;
	JButton option;
	JButton OptionVerbose;
	JButton retour;
	Boolean GameOn = false;
	Boolean op = false;

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
		cont = new Panel();
		cont.setBackground(Color.WHITE);

		ImageIcon image = new ImageIcon("game.whaler/sprites/play_unclicked.png");
		play = new JButton(image);
		play.addActionListener(this);
		play.addMouseListener(this);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		play.setFocusPainted(false);
		cont.add(play);

		option = new JButton(image);
		option.addActionListener(this);
		option.addMouseListener(this);
		option.setContentAreaFilled(false);
		option.setBorderPainted(false);
		option.setFocusPainted(false);
		cont.add(option);

		OptionVerbose = new JButton("Verbose");
		OptionVerbose.addActionListener(this);
		OptionVerbose.setVisible(false);
		cont.add(OptionVerbose);

		retour = new JButton("Retour");
		retour.addActionListener(this);
		retour.setVisible(false);
		cont.add(retour);

		m_game.addSouth(cont);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == play) {
			GameOn = true;
			cont.setVisible(false);
		}

		if (s == option) {
			op = true;
			option.setVisible(false);
			play.setVisible(false);
			OptionVerbose.setVisible(true);
			retour.setVisible(true);
		}
		if(s == retour) {
			op=false;
			option.setVisible(true);
			play.setVisible(true);
			OptionVerbose.setVisible(false);
			retour.setVisible(false);
		}
		
		if(s == OptionVerbose)
			Options.VERBOSE=true;
	}

}
