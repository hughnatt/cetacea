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
	Panel cont;
	Panel p;
	Panel main;
	Model m_model;
	JButton play;
	Music m_player;
	JButton option;
	JButton annuler;
	JButton retour;
	Boolean GameOn = false;
	Boolean op = false;
	JLabel infoLabel;
	JComboBox<?> b[];

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
		Object s = e.getSource();
		if (s == play) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/play_clicked.png");
			play.setIcon(img);
		}
		if (s == retour) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/retour_click.png");
			retour.setIcon(img);
		}
		if (s == option) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option_click.png");
			option.setIcon(img);
		}
		if (s == annuler) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/annuler_click.png");
			annuler.setIcon(img);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Object s = e.getSource();
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
		if (s == option) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option.png");
			option.setIcon(img);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object s = e.getSource();
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
		if (s == option) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option_hover.png");
			option.setIcon(img);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object s = e.getSource();
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
		if (s == option) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option.png");
			option.setIcon(img);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	public void notifyVisible() {
		cont = new Panel();
		p = new Panel();
		main = new Panel();
		main.setLayout(new GridLayout(9, 1, 0, 15));
		main.setBackground(Color.WHITE);

		main.setVisible(false);
		cont.setBackground(Color.WHITE);

		ImageIcon image = new ImageIcon("game.whaler/sprites/play_unclicked.png");
		play = new JButton(image);
		play.addActionListener(this);
		play.addMouseListener(this);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		play.setFocusPainted(false);
		cont.add(play);
		
		ImageIcon image1 = new ImageIcon("game.whaler/sprites/option.png");
		option = new JButton(image1);
		option.addActionListener(this);
		option.addMouseListener(this);
		option.setContentAreaFilled(false);
		option.setBorderPainted(false);
		option.setFocusPainted(false);
		cont.add(option);

		ImageIcon image2 = new ImageIcon("game.whaler/sprites/retour.png");
		retour = new JButton(image2);
		retour.addActionListener(this);
		retour.addMouseListener(this);
		retour.setContentAreaFilled(false);
		retour.setBorderPainted(false);
		retour.setFocusPainted(false);
		retour.setVisible(false);
		cont.add(retour);
		
		ImageIcon image3 = new ImageIcon("game.whaler/sprites/annuler.png");
		annuler = new JButton(image3);
		annuler.addActionListener(this);
		annuler.addMouseListener(this);
		annuler.setContentAreaFilled(false);
		annuler.setBorderPainted(false);
		annuler.setFocusPainted(false);
		annuler.setVisible(false);
		cont.add(annuler);
		
		infoLabel = new JLabel("Sélectionnez un item");
		infoLabel.setVisible(false);

		// ici on récupère les automates et on en fait une liste
		String[] items = { "Baleine", "Pétrole", "Baleinier", "Destroyer", "Joueur", "Projectile" };
		b = new JComboBox[6];
		for (int i = 0; i < 6; i++) {
			b[i] = new JComboBox<Object>(items);
			main.add(b[i]);
			b[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object o = ((JComboBox<?>) e.getSource()).getSelectedItem();
					String s = (String) o;
					infoLabel.setText(s);
					//ici on affecte à l'entité correspondante l'automate sélectionné
				}
			});
		}
		
		cont.add(infoLabel);
		m_game.addSouth(cont);
		m_game.addEast(main);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object s = e.getSource();
		if (s == play) {
			m_model.setScreen(Screen.GAME);
			cont.setVisible(false);
		}

		if (s == option) {
			m_model.setScreen(Screen.OPTIONS);
			option.setVisible(false);
			play.setVisible(false);
			retour.setVisible(true);
			main.setVisible(true);
			annuler.setVisible(true);
			infoLabel.setVisible(true);
		}
		if (s == retour) {
			m_model.setScreen(Screen.HOME);
			option.setVisible(true);
			play.setVisible(true);
			retour.setVisible(false);
			main.setVisible(false);
			annuler.setVisible(false);
			infoLabel.setVisible(false);
		}
		
		if (s==annuler) {
			infoLabel.setText("Sélectionnez un item");
			/* Ici on met le champ m_automate de toutes les entités à NULL ou on introduit un last automate
			 * On garde en mémoire l'ancien automate assigné quand on en assigne un nouveau
			 * Et en cliquant sur annuler, on remet l'ancien automate  */
		}

	}

}
