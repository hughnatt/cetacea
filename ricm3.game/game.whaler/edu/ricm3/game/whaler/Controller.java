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
import javax.swing.JLabel;
import javax.swing.JComboBox;
import edu.ricm3.game.GameController;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Model.Screen;

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
			m_model.m_whales[0].pop();
		}

		if (e.getKeyChar() == 'o' || e.getKeyChar() == 'O') {
			m_model.m_whales[0].m_life = 20;
		}
	}

	/* 
	 * keyPressed est un tableau de booléens de 127 cases, on met une case à true quand on appuie sur la touche correspondant au numéro ascii de la case
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		//Attention, getKeyCode voit toutes les touches alphabétiques en majuscule de façon permanente
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
		Object s = e.getSource();

		/** Changes the buttons' sprites when you click on them **/

		if (s == play) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/play_clicked.png");
			play.setIcon(img);
		}
		if (s == retour) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/retour_click.png");
			retour.setIcon(img);
		}
		if (s == automata) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option_click.png");
			automata.setIcon(img);
		}
		if (s == annuler) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/annuler_click.png");
			annuler.setIcon(img);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Object s = e.getSource();

		/** Changes the buttons' sprites when you stop clicking on them **/

		if (s == play) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/play_unclicked.png");
			play.setIcon(img);
		}
		if (s == retour) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/retour.png");
			retour.setIcon(img);
		}
		if (s == annuler) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/annuler.png");
			annuler.setIcon(img);
		}
		if (s == automata) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option.png");
			automata.setIcon(img);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object s = e.getSource();

		/** Changes the buttons' sprites when you hover (put the mouse) on them **/

		if (s == play) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/play.png");
			play.setIcon(img);
		}
		if (s == retour) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/retour_hover.png");
			retour.setIcon(img);
		}
		if (s == annuler) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/annuler_hover.png");
			annuler.setIcon(img);
		}
		if (s == automata) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option_hover.png");
			automata.setIcon(img);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object s = e.getSource();

		/** Changes the buttons' sprites when you move the mouth away from them **/

		if (s == play) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/play_unclicked.png");
			play.setIcon(img);
		}
		if (s == retour) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/retour.png");
			retour.setIcon(img);
		}
		if (s == annuler) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/annuler.png");
			annuler.setIcon(img);
		}
		if (s == automata) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option.png");
			automata.setIcon(img);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public void notifyVisible() {
		main = new Panel();
		main.setBackground(Color.WHITE);

		// The cont panel has a grid layout to display the ComboBoxes in a grid. In the
		// home menu it's not visible
		cont = new Panel();
		cont.setLayout(new GridLayout(9, 1, 0, 15));
		cont.setBackground(Color.WHITE);
		cont.setVisible(false);

		// The buttons are all but images, without any border and with a mouse and
		// action listener
		ImageIcon image = new ImageIcon("game.whaler/sprites/play_unclicked.png");
		play = new JButton(image);
		play.addActionListener(this);
		play.addMouseListener(this);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		play.setFocusPainted(false);
		main.add(play);

		ImageIcon image1 = new ImageIcon("game.whaler/sprites/option.png");
		automata = new JButton(image1);
		automata.addActionListener(this);
		automata.addMouseListener(this);
		automata.setContentAreaFilled(false);
		automata.setBorderPainted(false);
		automata.setFocusPainted(false);
		main.add(automata);

		ImageIcon image2 = new ImageIcon("game.whaler/sprites/retour.png");
		retour = new JButton(image2);
		retour.addActionListener(this);
		retour.addMouseListener(this);
		retour.setContentAreaFilled(false);
		retour.setBorderPainted(false);
		retour.setFocusPainted(false);
		retour.setVisible(false);
		main.add(retour);

		ImageIcon image3 = new ImageIcon("game.whaler/sprites/annuler.png");
		annuler = new JButton(image3);
		annuler.addActionListener(this);
		annuler.addMouseListener(this);
		annuler.setContentAreaFilled(false);
		annuler.setBorderPainted(false);
		annuler.setFocusPainted(false);
		annuler.setVisible(false);
		main.add(annuler);

		preference = new JButton("Préférences");
		preference.addActionListener(this);
		preference.addMouseListener(this);
		main.add(preference);

		valider = new JButton("Valider");
		valider.addActionListener(this);
		valider.addMouseListener(this);
		valider.setVisible(false);
		main.add(valider);

		// A confirmation text to ensure the automaton assignment has worked
		infoLabel = new JLabel("Automates assignés!");
		infoLabel.setVisible(false);
		main.add(infoLabel);

		// Example items for the moment, but there should be automatons here
		String[] items = { "Baleine", "Baleinier", "Destroyer", "Joueur", "Pétrole", "Projectile" };
		b = new JComboBox[6];
		int i = 0;
		while (i < 6) {
			// We create 6 ComboBox, one for each entity, and add it to the panel
			b[i] = new JComboBox<Object>(items);
			cont.add(b[i]);
			i++;
		}

		m_game.addSouth(main);
		m_game.addEast(cont);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object s = e.getSource();

		/**
		 * When we click on the return button, we should go back to the home menu, so
		 * the automata, play and preferences buttons are visible but anything else
		 * isn't
		 **/

		if (s == retour) {
			m_model.setScreen(Screen.HOME);
			automata.setVisible(true);
			play.setVisible(true);
			preference.setVisible(true);
			retour.setVisible(false);
			cont.setVisible(false);
			annuler.setVisible(false);
			infoLabel.setVisible(false);
			valider.setVisible(false);
		}

		/**
		 * When we click on the discard button, it resets the ComboBoxes to its first
		 * value
		 **/

		if (s == annuler) {
			infoLabel.setText("Sélectionnez un item");
			for (int i = 0; i < 6; i++)
				b[i].setSelectedIndex(0);
		}

		/**
		 * When we click on the play button, it starts the game and erases the main
		 * panel since no buttons are necessary anymore
		 **/

		if (s == play) {
			m_model.setScreen(Screen.GAME);
			main.setVisible(false);
		}

		/**
		 * When we click on the verify button, it writes the automatons' assignment in a
		 * particular file
		 **/

		if (s == valider) {
			File file = new File("game.whaler/sprites/choix_automates.txt");
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new FileWriter(file));
				for (int i = 0; i < 6; i++)
					out.write(b[i].getSelectedItem().toString() + ";");
				infoLabel.setVisible(true);
			} catch (IOException ae) {
				ae.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		/** When we click on the automata button, it displays the automaton menu **/

		if (s == automata) {
			m_model.setScreen(Screen.AUTOMATA);
			automata.setVisible(false);
			play.setVisible(false);
			preference.setVisible(false);
			retour.setVisible(true);
			cont.setVisible(true);
			annuler.setVisible(true);
			infoLabel.setVisible(true);
			valider.setVisible(true);
		}

		/**
		 * When we click on the preferences button, it displays the preferences menu
		 **/

		if (s == preference) {
			m_model.setScreen(Screen.PREFERENCES);
			automata.setVisible(false);
			play.setVisible(false);
			preference.setVisible(false);
			retour.setVisible(true);
			annuler.setVisible(true);

		}
	}
}
