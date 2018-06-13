package edu.ricm3.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import edu.ricm3.game.Rules.IsClicked;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;

public class Pause {
	JFrame m_pause;
	JLabel m_title;
	JPanel m_ctr;
	GameUI m_g;

	public Pause(GameUI g) {
		m_g = g;
	}

	public void create_frame() {
		m_pause = new JFrame();
		m_pause.setTitle("Pause"); //Nom de la fenêtre
		m_pause.setSize(300, 400);
		m_pause.setLocationRelativeTo(null);

		m_title = new JLabel("", JLabel.CENTER);
        m_title.setText("Pause");
        m_title.setFont(new Font("Serif", Font.BOLD, 25));
        m_pause.add(m_title, BorderLayout.NORTH); // Affichage du titre


        Model m = (Model) m_g.m_model;
        GridLayout grid = new GridLayout (3,1);

        m_ctr= new JPanel();
        m_ctr.setLayout(grid);

        JLabel info = new JLabel("", JLabel.CENTER  );
        info.setText("Current score ");
        info.setFont(new Font("Serif", Font.PLAIN, 25));
        
        JLabel score = new JLabel("", JLabel.CENTER  );
        score.setText(Integer.toString(m.m_score.nombre));
        score.setFont(new Font("Serif", Font.BOLD, 25));
        
        m_ctr.add(info);
        m_ctr.add(score);

        m_pause.add(m_ctr);
		// Lecture & affichage de l'icone du jeu
		File f = new File("game.whaler/sprites/cetacea.png");
		Image icone;
		try {
			icone = ImageIO.read(f);
			m_pause.setIconImage(icone);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}


		m_pause.setVisible(true);
	}

	public void create_pause() {
		Model m = (Model) m_g.m_model;
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

		
		m_pause.add(b1, BorderLayout.SOUTH);
		m_pause.setVisible(true);
	}

	public class IsClicked implements ActionListener {
		//On verifie où on clique
		@Override
		public void actionPerformed(ActionEvent e) {
			Model m = (Model) m_g.m_model;
			String event = e.getActionCommand();
			if (event.equals("RETURN")) {
				m.m_pause =false;
				m_g.setScreen(Screen.PLAY);
				
				//m_g.createWindow(new Dimension(Options.DIMX_WINDOW, Options.DIMY_WINDOW));
				//m_rules.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				m_pause.dispose();
			}
		}
	}
}
