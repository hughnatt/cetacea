package edu.ricm3.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.GameUI.Screen;
import edu.ricm3.game.whaler.Options;

public class Rules {
	JFrame m_rules;
	JLabel m_title;
	JPanel m_ctr;
	GameUI m_g;

	public Rules(GameUI g) {
		m_g = g;
	}

	public void create_frame() {
		m_rules = new JFrame();
		m_rules.setTitle("Cetacea - Rules"); //Nom de la fenêtre
		m_rules.setSize(Options.DIMX_WINDOW, Options.DIMY_WINDOW);
		m_rules.setLocationRelativeTo(null);

		m_title = new JLabel("", JLabel.CENTER);
		m_title.setText("Rules");
		m_title.setFont(new Font("Serif", Font.PLAIN, 68));
		m_rules.add(m_title, BorderLayout.NORTH);

		// Lecture & affichage de l'icone du jeu
		File f = new File("game.whaler/sprites/cetacea.png");
		Image icone;
		try {
			icone = ImageIO.read(f);
			m_rules.setIconImage(icone);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}


		m_rules.setVisible(true);
	}

	public void create_rules() {

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

		//Affichage image des règles
		m_ctr =new JPanel();
	    BufferedImage image;
		try {
			image = ImageIO.read(new File("game.whaler/sprites/fond_rules.jpg"));
			 JLabel label = new JLabel(new ImageIcon(image));
			 m_ctr.add(label);
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
		m_rules.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate the process of clicking on the red cross
		m_rules.add(m_ctr, BorderLayout.CENTER);
		m_rules.add(b1, BorderLayout.SOUTH);
		m_rules.setVisible(true);
	}

	public class IsClicked implements ActionListener {
		//On verifie où on clique
		@Override
		public void actionPerformed(ActionEvent e) {
			String event = e.getActionCommand();
			if (event.equals("RETURN")) {
				m_g.setScreen(Screen.MENU);
				m_g.createWindow(new Dimension(Options.DIMX_WINDOW, Options.DIMY_WINDOW));
				//m_rules.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				m_rules.dispose();
			}
		}
	}
}
