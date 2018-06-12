package edu.ricm3.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.GameUI.Screen;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Interpretor.IAutomata;

public class AutomataSelection {

	JFrame m_select;
	JFrame frame;
	JLabel m_title;
	JPanel m_ctr;
	GameUI m_g;
	JComboBox<?> b[];

	public AutomataSelection(GameUI g) {
		m_g = g;
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

		CreateButton bvalider = new CreateButton("VALIDER", "game.whaler/sprites/check.png",
				"game.whaler/sprites/check_over.png");

		// Initialisation
		breturn.setButton();
		bvalider.setButton();

		// Verification du clique sur le bouton retour
		breturn.addActionListener(new IsClicked());
		bvalider.addActionListener(new IsClicked());

		JPanel return_button = new JPanel();
		return_button.setOpaque(false);
		return_button.add(breturn);

		JPanel valide_button = new JPanel();
		valide_button.setOpaque(false);
		valide_button.add(bvalider);

		JPanel b1 = new JPanel();
		b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
		b1.add(return_button);
		b1.add(valide_button);

		// Création tableau déroulant avec les bons automates
		frame = new JFrame();
		GridLayout grid = new GridLayout(6, 2);
		frame.setLayout(grid);
		grid.setHgap(20);
		grid.setVgap(20);

		JPanel b2 = new JPanel();
		b2.setLayout(grid);

		Model m_model = (Model) m_g.m_model;
		IAutomata[] items = m_model.automata_array;

		BufferedImage[] image = new BufferedImage[6];
		try {
			image[0] = ImageIO.read(new File("game.whaler/sprites/cetacea.png"));
			image[1] = ImageIO.read(new File("game.whaler/sprites/cetacea.png"));
			image[2] = ImageIO.read(new File("game.whaler/sprites/cetacea.png"));
			image[3] = ImageIO.read(new File("game.whaler/sprites/cetacea.png"));
			image[4] = ImageIO.read(new File("game.whaler/sprites/cetacea.png"));
			image[5] = ImageIO.read(new File("game.whaler/sprites/cetacea.png"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		b = new JComboBox[6];
		int i = 0;
		while (i < 6) {
			JLabel label = new JLabel(new ImageIcon(image[i]));
			b2.add(label);
			// We create 6 ComboBox, one for each entity, and add it to the panel
			b[i] = new JComboBox<Object>(items);
			b2.setSize(new Dimension(50, 50));
			b2.add(b[i]);
			i++;
		}

		m_select.add(b1, BorderLayout.SOUTH); // bouton retour
		m_select.add(b2, BorderLayout.CENTER);
		m_select.setVisible(true);
	}

	public void validate_automata() {
		File file = new File("game.whaler/sprites/choix_automates.txt");
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < 6; i++) {
				out.write(String.valueOf(b[i].getSelectedIndex()));
				if (i != 5)
					out.write('\n');
			}
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

	public class IsClicked implements ActionListener {
		// On verifie où on clique
		@Override
		public void actionPerformed(ActionEvent e) {
			String event = e.getActionCommand();
			if (event.equals("RETURN")) {
				m_g.setScreen(Screen.MENU);
				m_g.createWindow(new Dimension(Options.DIMX_WINDOW, Options.DIMY_WINDOW));
				m_select.dispose();
			}
			if (event.equals("VALIDER")) {
				validate_automata();
				m_g.setScreen(Screen.MENU);
				m_g.createWindow(new Dimension(Options.DIMX_WINDOW, Options.DIMY_WINDOW));
				m_select.dispose();
			}
		}
	}
}
