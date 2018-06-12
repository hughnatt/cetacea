package edu.ricm3.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.GameUI.Screen;

import edu.ricm3.game.whaler.Options;

public class AutomataSelection {

	
	JFrame m_select;
	JLabel m_title;
	JPanel m_ctr;
	GameUI m_g;
	JComboBox<?> b[];
	
	public AutomataSelection(GameUI g) {
		m_g =g;
	}
	
	public void create_frame() {
		m_select = new JFrame();
		m_select.setTitle("Cetacea - Automata Selection");
		m_select.setSize(Options.DIMX_WINDOW, Options.DIMY_WINDOW);
		m_select.setLocationRelativeTo(null);

		m_title = new JLabel("", JLabel.CENTER);
		m_title.setText("Choose your automata");
		m_title.setFont(new Font("Serif", Font.PLAIN, 68));
		m_select.add(m_title, BorderLayout.NORTH);

		// Lecture & affichage de l'icone du jeu
		File f = new File("game.whaler/sprites/cetacea.png");
		Image icone;
		try {
			icone = ImageIO.read(f);
			m_select.setIconImage(icone);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Charge fond f = new File("game.whaler/sprites/cetacea.png"); try { icone =
		 * ImageIO.read(f); m_menu.setIconImage(icone); } catch (IOException ex) {
		 * ex.printStackTrace(); System.exit(-1); }
		 */

		m_select.setVisible(true);
	}
	
	public void create_automata_selection() {

		// Creation des bouttons
		CreateButton breturn = new CreateButton("RETURN", "game.whaler/sprites/arrow_return.png",
				"game.whaler/sprites/arrow_return.png");

		// Initialisation
		breturn.setButton();

		// Verification du clique sur le bouton retour
		breturn.addActionListener(new IsClicked());

		JPanel return_button = new JPanel();
		return_button.setOpaque(false);
		return_button.add(breturn);
		
		JPanel b1 = new JPanel();
		b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
		b1.add(return_button);

		
		//Création tableau déroulant 
		//A modifier
		JPanel b2 = new JPanel();
		String[] items = { "Baleine", "Baleinier", "Destroyer", "Joueur", "Pétrole", "Projectile" };
		b = new JComboBox[6];
		int i = 0;
		while (i < 6) {
			//We create 6 ComboBox, one for each entity, and add it to the panel
			b[i] = new JComboBox<Object>(items);
			b2.add(b[i]);
			i++;
		}
		
		
		m_select.add(b1, BorderLayout.SOUTH); //bouton retour
		m_select.add(b2, BorderLayout.CENTER );
		m_select.setVisible(true);
	}

	public class IsClicked implements ActionListener {
		//On verifie où on clique
		@Override
		public void actionPerformed(ActionEvent e) {
			String event = e.getActionCommand();
			if (event.equals("RETURN")) {
				m_g.setScreen(Screen.MENU);
				m_g.createWindow(new Dimension(Options.DIMX_WINDOW, Options.DIMY_WINDOW));
				m_select.dispose();
			}
		}
	}
}
