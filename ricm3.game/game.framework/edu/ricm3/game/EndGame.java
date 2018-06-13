package edu.ricm3.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.GameUI.Screen;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public class EndGame {
	JFrame m_gameover;
	JLabel m_title;
	JLabel m_gameover_score;
	JLabel m_dead;
	JPanel m_ctr;
	GameUI m_g;
	

	public EndGame(GameUI g) {
		m_g = g;
	}

	public void create_frame() {

		m_gameover = new JFrame();
		m_gameover.setTitle("Cetacea - GameOver"); // Nom de la fenêtre
		m_gameover.setSize(Options.DIMX_WINDOW, Options.DIMY_WINDOW); // Dimension
		m_gameover.setLocationRelativeTo(null);

		m_title = new JLabel("", JLabel.CENTER);
		m_title.setText("GameOver");
		m_title.setFont(new Font("Serif", Font.PLAIN, 68));
		m_gameover.add(m_title, BorderLayout.NORTH); // Affichage du titre
		
		
		Model m = (Model) m_g.m_model;
		GridLayout grid = new GridLayout (2,1);
		
		
		m_ctr= new JPanel();
		m_ctr.setLayout(grid);
		
		m_gameover_score = new JLabel("", JLabel.CENTER  );
		m_gameover_score.setText("Your score : "+Integer.toString(m.m_score.nombre));
		m_gameover_score.setFont(new Font("Serif", Font.PLAIN, 60));
		
		m_ctr.add(m_gameover_score);
		m_dead = new JLabel("", JLabel.CENTER  );
		
		if(m.m_player.m_life <=0) {
			m_dead.setText("You are dead !");
		}else if (m.m_player.m_oil_jauge <=0){
			m_dead.setText("Your fuel gauge is empty!");
		}else {
			m_dead.setText("Do you want to replay ?");
		}
		
		m_dead.setFont(new Font("Serif", Font.PLAIN, 30));
		m_ctr.add(m_dead);
		
		m_gameover.add(m_ctr);
		// Lecture & affichage de l'icone du jeu
		File f = new File("game.whaler/sprites/cetacea.png");
		Image icone;
		try {
			icone = ImageIO.read(f);
			m_gameover.setIconImage(icone);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		m_gameover.setVisible(true);
	}
	

	public void create_endgame() {

		// Creation des bouttons
		CreateButton replay = new CreateButton("REPLAY", "game.whaler/sprites/replay_button_over.png",
				"game.whaler/sprites/replay_button.png");
		CreateButton exit = new CreateButton("EXIT", "game.whaler/sprites/exit.png",
				"game.whaler/sprites/exit.png");

		// Initialisation
		replay.setButton();
		exit.setButton();

		// Verification du clique sur l'un des bouttons
		replay.addActionListener(new IsClicked());
		exit.addActionListener(new IsClicked());
		
		//Options et ajout des boutons
		JPanel replay_button = new JPanel();
		replay_button.setOpaque(false);
		replay_button.add(replay);
		
		JPanel exit_button = new JPanel();
		exit_button.setOpaque(false);
		exit_button.add(exit);

		// Ajout du bouton play au 1er étage
		JPanel b1 = new JPanel();
		b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
		b1.add(replay_button);
		b1.add(exit_button);
		m_gameover.add(b1,BorderLayout.SOUTH);
		m_gameover.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_gameover.setVisible(true);
	}
	
	public class IsClicked implements ActionListener {
		//On verifie où on clique
		@Override
		public void actionPerformed(ActionEvent e) {
			String event = e.getActionCommand();
			if (event.equals("REPLAY")) {
				Model m = (Model) m_g.m_model;
				
				try {
					m.restartModel();
				} catch (Game_exception e1) {
					e1.printStackTrace();
					System.exit(-6);
				}
				
				m_g.setScreen(Screen.MENU);
				m_g.createWindow(new Dimension(Options.DIMX_WINDOW, Options.DIMY_WINDOW));
				m_gameover.dispose();
			
			} else if ( event.equals("EXIT")) {
				m_gameover.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				m_gameover.dispose();
			}
		}
	}

}
