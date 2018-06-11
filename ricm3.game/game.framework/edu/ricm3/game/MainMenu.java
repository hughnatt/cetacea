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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainMenu {

	JFrame m_menu;
	JPanel m_ctr;
	GameUI m_g;
	
	public MainMenu(GameUI g) {
		m_g = g;
	}

	public void create_frame() {
		m_menu = new JFrame(); 
		m_menu.setTitle("Cetacea");
		
		m_menu.setSize(Options.DIMX_WINDOW,Options.DIMY_WINDOW );
		
		m_menu.setLayout(new BorderLayout());
		GridLayout menu_grid = new GridLayout(1, 3);
		menu_grid.setVgap(5);
		
		
		//Lecture & affichage de l'icone du jeu
		File f = new File("game.whaler/sprites/cetacea.png");
		Image icone;
		try {
			icone = ImageIO.read(f);
			m_menu.setIconImage(icone);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		/*Charge fond
		f = new File("game.whaler/sprites/cetacea.png");
		try {
			icone = ImageIO.read(f);
			m_menu.setIconImage(icone);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}	*/	
		
		m_ctr = new JPanel();
		
		m_menu.setContentPane(m_ctr);
		m_menu.setVisible(true);
	}
	
	public void create_menu() {
		CreateButton play = new CreateButton
				("PLAY", "game.whaler/sprites/Play.png", "game.whaler/sprites/Play_over.png" );
		CreateButton automata = new CreateButton
				("AUTOMATA", "game.whaler/sprites/Automata.png", "game.whaler/sprites/Automata_over.png");
		CreateButton options = new CreateButton
				("OPTIONS", "game.whaler/sprites/Options.png", "game.whaler/sprites/Options_over.png");
		
		play.setButton();
		automata.setButton();
		options.setButton();
		
		play.addActionListener(new IsClicked());
		automata.addActionListener(new IsClicked());
		options.addActionListener(new IsClicked());

		JLabel title =new JLabel("Cetacea",null, JLabel.CENTER);
		/*File f = new File("game.whaler/sprites/help.png");
		try {
			ImageIO.read(f);
			title.setIcon(new ImageIcon("game.whaler/sprites/help.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}*/
		
		JPanel play_button = new JPanel();
		play_button.setOpaque(false);
		play_button.add(play);
		
		JPanel automata_button = new JPanel();
		automata_button.setOpaque(false);
		automata_button.add(automata);
		
		JPanel options_button = new JPanel();
		options_button.setOpaque(false);
		options_button.add(options);
		
		
		
		m_menu.add(play_button);
		m_menu.add(automata_button);
		m_menu.add(options_button);
		
		
		m_menu.setVisible(true);
	}
	
	public class IsClicked implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String event = e.getActionCommand();
			if(event.equals("PLAY")) {
				m_g.setScreen(Screen.PLAY);
				m_g.createWindow(new Dimension(Options.DIMX_WINDOW,Options.DIMY_WINDOW));
				m_g.createTimer();
				m_menu.dispose();
			}
	}
}}
