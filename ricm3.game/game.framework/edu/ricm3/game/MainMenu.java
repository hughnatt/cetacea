package edu.ricm3.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import edu.ricm3.game.GameUI.Screen;
import edu.ricm3.game.whaler.Options;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu {

	JFrame m_menu;
	JLabel m_title;
	JPanel m_ctr;
	GameUI m_g;

	public MainMenu(GameUI g) {
		m_g = g;
	}

	public void create_frame() {
		m_menu = new JFrame();
		m_menu.setTitle("Cetacea");
		m_menu.setSize(Options.DIMX_WINDOW, Options.DIMY_WINDOW);
		m_menu.setLocationRelativeTo(null);

		m_title = new JLabel("", JLabel.CENTER);
		m_title.setText("Cetacea");
		m_title.setFont(new Font("Serif", Font.PLAIN, 68));
		m_menu.add(m_title, BorderLayout.NORTH);

		// Lecture & affichage de l'icone du jeu
		File f = new File("game.whaler/sprites/cetacea.png");
		Image icone;
		try {
			icone = ImageIO.read(f);
			m_menu.setIconImage(icone);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Charge fond f = new File("game.whaler/sprites/cetacea.png"); try { icone =
		 * ImageIO.read(f); m_menu.setIconImage(icone); } catch (IOException ex) {
		 * ex.printStackTrace(); System.exit(-1); }
		 */

		m_menu.setVisible(true);
	}

	public void create_menu() {

		// Creation des bouttons
		CreateButton play = new CreateButton("PLAY", "game.whaler/sprites/Play.png",
				"game.whaler/sprites/Play_over.png");
		CreateButton automata = new CreateButton("AUTOMATA", "game.whaler/sprites/Automata.png",
				"game.whaler/sprites/Automata_over.png");
		CreateButton options = new CreateButton("OPTIONS", "game.whaler/sprites/Options.png",
				"game.whaler/sprites/Options_over.png");
		CreateButton rules = new CreateButton("RULES", "game.whaler/sprites/Rules.png",
				"game.whaler/sprites/Rules_over.png");

		// Initialisation
		play.setButton();
		automata.setButton();
		options.setButton();
		rules.setButton();

		// Verification du clique sur l'un des bouttons
		play.addActionListener(new IsClicked());
		automata.addActionListener(new IsClicked());
		options.addActionListener(new IsClicked());
		rules.addActionListener(new IsClicked());

		JPanel play_button = new JPanel();
		play_button.setOpaque(false);
		play_button.add(play);

		JPanel automata_button = new JPanel();
		automata_button.setOpaque(false);
		automata_button.add(automata);

		JPanel options_button = new JPanel();
		options_button.setOpaque(false);
		options_button.add(options);

		JPanel rules_button = new JPanel();
		rules_button.setOpaque(false);
		rules_button.add(rules);

		// Ajout du bouton play au 1er étage
		JPanel b1 = new JPanel();
		b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
		b1.add(play_button);

		// Ajout des autres boutons au 2eme étage
		JPanel b2 = new JPanel();
		b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
		b2.add(automata_button);
		b2.add(options_button);
		b2.add(rules_button);

		JPanel b4 = new JPanel();
		b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
		b4.add(b1);
		b4.add(b2);

		m_menu.getContentPane().add(b4);
		m_menu.setVisible(true);
	}

	public class IsClicked implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String event = e.getActionCommand();
			if (event.equals("PLAY")) {
				m_g.setScreen(Screen.PLAY);
				m_g.createWindow(new Dimension(Options.DIMX_WINDOW, Options.DIMY_WINDOW));
				m_g.createTimer();
				m_menu.dispose();
			}
			if (event.equals("RULES")) {
				m_g.setScreen(Screen.RULES);
				m_g.createWindow(new Dimension(Options.DIMX_WINDOW, Options.DIMY_WINDOW));
				m_menu.dispose();
			}
			if (event.equals("AUTOMATA")) {
				m_g.setScreen(Screen.AUTOMATA);
				m_g.createWindow(new Dimension(Options.DIMX_WINDOW, Options.DIMY_WINDOW));
				m_menu.dispose();
			}
		}
	}
}
