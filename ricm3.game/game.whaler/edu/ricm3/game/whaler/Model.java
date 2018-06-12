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

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.parser.Ast;
import edu.ricm3.game.parser.Ast.AI_Definitions;
import edu.ricm3.game.parser.AutomataParser;
import edu.ricm3.game.parser.ParseException;

import edu.ricm3.game.whaler.Entities.*;
import edu.ricm3.game.whaler.Entities.Entity.EntityType;

import edu.ricm3.game.whaler.Game_exception.Automata_Exception;
import edu.ricm3.game.whaler.Game_exception.Game_exception;
import edu.ricm3.game.whaler.Game_exception.Location_exception;
import edu.ricm3.game.whaler.Interpretor.IAutomata;

public class Model extends GameModel {

	// enum for the menu, to determine which screen should be displayed
	public enum Screen {
		AUTOMATA, HOME, GAME, PREFERENCES;
	}

	private Screen m_screen;

	// getter for Screen
	public Screen currentScreen() {
		return m_screen;
	}

	// setter for Screen
	public void setScreen(Screen s) {
		m_screen = s;
	}

	// Sprite-sheets (BufferedImage) and instances of elements

	private BufferedImage m_whaleSprite;
	private BufferedImage m_playerSprite;
	private BufferedImage m_stoneSprite;
	private BufferedImage m_whalerSprite;
	private BufferedImage m_waterSprite;
	private BufferedImage m_destroyerSprite;
	private BufferedImage m_islandSprite;
	private BufferedImage m_icebergSprite;
	private BufferedImage m_oilSprite;
	private BufferedImage m_boomSprite;
	private BufferedImage m_scoreSprite;
	private BufferedImage m_underSprite;
	private BufferedImage m_bulleUnderSprite;
	private BufferedImage m_stoneUnderSprite;
	private BufferedImage m_yellowAlgaeUnderSprite;
	private BufferedImage m_coralUnderSprite;
	private BufferedImage m_playerUnderSprite;
	private BufferedImage m_fireSprite;
	private BufferedImage m_redCoralUnderSprite;
	private BufferedImage m_projectileSprite;

	// Automaton array
	public IAutomata[] automata_array;
	// Automata Choices
	int[] automata_choices;

	// Home menu
	Menu m_menu;

	// Boolean true if the player is under the surface
	public boolean UNDER_WATER;

	// Background
	Background m_current_background;
	Background m_ocean;
	Background m_underwater;

	// Map
	private Map m_map;

	// Static Entity tab

	List<Static_Entity> m_statics = new LinkedList<Static_Entity>();

	// Mobile Entity List
	public Player m_player;
	public List<Destroyer> m_destroyers = new LinkedList<Destroyer>();
	public List<Whaler> m_whalers = new LinkedList<Whaler>();
	public List<Projectile> m_projectiles = new LinkedList<Projectile>();
	public List<Whale> m_whales = new LinkedList<Whale>();
	public List<Oil> m_oils = new LinkedList<Oil>();
	public List<Mobile_Entity> m_garbage;

	public boolean[] keyPressed;
	// Random generation
	public Random rand = new Random();

	public Model() throws FileNotFoundException, Automata_Exception, Game_exception, ParseException {

		// Set the current screen on the home menu
		m_screen = Screen.HOME;

		new AutomataParser(new BufferedReader(new FileReader("game.parser/example/automata.txt")));
		// Loading automate file
		Ast ast = AutomataParser.Run();
		automata_array = ((AI_Definitions) ast).make();

		// Loading setting file
		// 6 Entities
		automata_choices = new int[EntityType.values().length];

		BufferedReader bis = null;
		try {
			bis = new BufferedReader(new FileReader(new File("game.whaler/sprites/choix_automates.txt")));

			automata_choices[EntityType.WHALE.ordinal()] = Integer.parseInt(bis.readLine());
			automata_choices[EntityType.WHALER.ordinal()] = Integer.parseInt(bis.readLine());
			automata_choices[EntityType.DESTROYER.ordinal()] = Integer.parseInt(bis.readLine());
			automata_choices[EntityType.PLAYER.ordinal()] = Integer.parseInt(bis.readLine());
			automata_choices[EntityType.OIL.ordinal()] = Integer.parseInt(bis.readLine());
			automata_choices[EntityType.PROJECTILE.ordinal()] = Integer.parseInt(bis.readLine());

			bis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Loading Sprites Model
		loadSprites();

		// Makes a new Menu
		m_menu = new Menu(this, 350, 150, (float) 2);

		// Animated Ocean Background
		m_ocean = new Water(m_waterSprite, this);
		m_underwater = new Underwater(m_underSprite, this);
		m_current_background = m_ocean;

		/*** Creating the map ***/

		m_map = new Map(this);

		undergroundFloreGenerator(8);
		seaGenerator(2);

		// Stones

		for (int i = 0; i < Options.DIMX_MAP; i++) {
			m_statics.add(new Stone(new Location(i, 0), m_stoneSprite, m_stoneUnderSprite, this));
			m_statics.add(new Stone(new Location(i, Options.DIMY_MAP - 1), m_stoneSprite, m_stoneUnderSprite, this));

		}
		for (int i = 0; i < Options.DIMY_MAP; i++) {
			m_statics.add(new Stone(new Location(0, i), m_stoneSprite, m_stoneUnderSprite, this));
			m_statics.add(new Stone(new Location(Options.DIMX_MAP - 1, i), m_stoneSprite, m_stoneUnderSprite, this));

		}

		// Entities

		// Oil
		m_oils.add(new Oil(new Location(3, 2), m_oilSprite, null, this, Direction.WEST, 1));
		m_oils.add(new Oil(new Location(4, 2), m_oilSprite, null, this, Direction.WEST, 1));
		m_oils.add(new Oil(new Location(5, 2), m_oilSprite, null, this, Direction.WEST, 1));

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				m_oils.add(new Oil(new Location(10 + i, 10 + j), m_oilSprite, null, this, Direction.WEST, 1));
			}
		}

		// Destroyers
		m_destroyers.add(new Destroyer(new Location(3, 4), m_destroyerSprite, null, this, Direction.WEST,
				Options.DESTROYER_LIFE));

		// Whalers
		m_whalers.add(new Whaler(new Location(3, 5), m_whalerSprite, null, this, Direction.WEST, Options.WHALER_LIFE));

		// Whales
		m_whales.add(new Whale(new Location(3, 8), m_whaleSprite, null, this, Direction.WEST, 1));

		// Projectiles

		// int indice_automata = st[3];
		// Player
		m_player = new Player(new Location(3, 3), m_playerSprite, m_playerUnderSprite, this, Direction.WEST,
				automata_array[0], Options.PLAYER_LIFE);

		keyPressed = new boolean[128];
	}

	public Map map() {
		return m_map;
	}

	@Override
	public void step(long now) {

		if (m_screen == Screen.GAME) {
			try {

				m_garbage = new LinkedList<Mobile_Entity>();

				m_current_background.step(now);

				m_player.step(now);

				Iterator<Static_Entity> iterstatics = m_statics.iterator();
				while (iterstatics.hasNext()) {
					Static_Entity e = iterstatics.next();
					e.step(now);
				}

				Iterator<Whale> iterwhales = m_whales.iterator();
				while (iterwhales.hasNext()) {
					Whale e = iterwhales.next();
					e.step(now);
				}

				Iterator<Oil> iteroil = m_oils.iterator();

				while (iteroil.hasNext()) {

					Oil tmp = iteroil.next();
					tmp.step(now);
				}

				Iterator<Whaler> iterwhalers = m_whalers.iterator();
				while (iterwhalers.hasNext()) {
					Whaler e = iterwhalers.next();
					e.step(now);
				}

				Iterator<Destroyer> iterdestroyers = m_destroyers.iterator();
				while (iterdestroyers.hasNext()) {
					Destroyer e = iterdestroyers.next();
					e.step(now);
				}

				Iterator<Projectile> iterprojs = m_projectiles.iterator();
				while (iterprojs.hasNext()) {
					Projectile e = iterprojs.next();
					e.step(now);
				}

				// Garbage Iterator

				Iterator<Mobile_Entity> iterdestroy = m_garbage.iterator();
				while (iterdestroy.hasNext()) {
					Mobile_Entity e = iterdestroy.next();
					switch (e.getType()) {
					case PLAYER:
						break;
					case WHALE:
						m_whales.remove(e);
						break;
					case WHALER:
						m_whalers.remove(e);
						break;
					case DESTROYER:
						m_destroyers.remove(e);
						break;
					case OIL:
						m_oils.remove(e);
						break;
					case PROJECTILE:
						m_projectiles.remove(e);
						break;
					default:
						break;
					}
				}
				// Suppression des références
				m_garbage = null;

			} catch (Game_exception | Automata_Exception e1) {
				e1.printStackTrace();
				System.exit(-1);
			}

		}
	}

	@Override
	public void shutdown() {
	}

	public IAutomata getAutomata(Mobile_Entity m) throws Game_exception {
		switch (m.getType()) {
		case PLAYER:
			return automata_array[automata_choices[EntityType.PLAYER.ordinal()]];
		case DESTROYER:
			return automata_array[automata_choices[EntityType.DESTROYER.ordinal()]];
		case WHALE:
			return automata_array[automata_choices[EntityType.WHALE.ordinal()]];
		case WHALER:
			return automata_array[automata_choices[EntityType.WHALER.ordinal()]];
		case PROJECTILE:
			return automata_array[automata_choices[EntityType.PROJECTILE.ordinal()]];
		case OIL:
			return automata_array[automata_choices[EntityType.OIL.ordinal()]];
		default:
			throw new Game_exception("Unexpected Entity. Have you added a new entity ?\n");
		}
	}

	public void swap() {
		if (UNDER_WATER) {
			m_current_background = m_ocean;
			UNDER_WATER = false;
		} else {
			m_current_background = m_underwater;
			UNDER_WATER = true;
		}
	}

	/**
	 * G�n�re la flore sous-marine selon un pourcentage donn� en param�tre <<<<<<<
	 * HEAD
	 * 
	 * ======= >>>>>>> master
	 * 
	 * @param pourcentage
	 * @throws Game_exception
	 * @throws Location_exception
	 */
	private void undergroundFloreGenerator(int pourcentage) throws Location_exception, Game_exception {

		Random rand = new Random();
		int min = 1;
		int max = Options.DIMX_MAP - 2;

		int nbparColonne = (pourcentage * max) / 100;

		for (int i = 1; i < Options.DIMY_MAP; i++) {
			for (int j = 1; j < nbparColonne; j++) {
				int x = rand.nextInt((max - min) + 1) + min;
				int flore = rand.nextInt(4);

				switch (flore) {
				case 0:
					m_statics.add(new Bulle(new Location(x, i), null, m_bulleUnderSprite, this));
					break;
				case 1:
					m_statics.add(new YellowAlgae(new Location(x, i), null, m_yellowAlgaeUnderSprite, this));
					break;
				case 2:
					m_statics.add(new Coral(new Location(x, i), null, m_coralUnderSprite, this));
					break;
				default:
					m_statics.add(new RedCoral(new Location(x, i), null, m_redCoralUnderSprite, this));
					break;
				}

			}
		}
	}

	/**
	 * G�n�re la mer selon un pourcentage donn� en param�tre
	 * 
	 * @param pourcentage
	 * @throws Game_exception
	 * @throws Location_exception
	 */
	private void seaGenerator(int pourcentage) throws Location_exception, Game_exception {

		Random rand = new Random();
		int min = 1;
		int max = Options.DIMX_MAP - 2;

		int nbparColonne = (pourcentage * max) / 100;

		for (int i = 1; i < Options.DIMY_MAP; i++) {
			for (int j = 1; j < nbparColonne; j++) {
				int x = rand.nextInt((max - min) + 1) + min;
				int flore = rand.nextInt(3);

				switch (flore) {
				case 0:
					m_statics.add(new Island(new Location(x, i), m_islandSprite, null, this));
					break;
				case 1:
					m_statics.add(new Iceberg(new Location(x, i), m_icebergSprite, null, this));
					break;
				default:
					m_statics.add(new Stone(new Location(x, i), m_stoneSprite, m_stoneUnderSprite, this));
					break;
				}

			}
		}
	}

	public BufferedImage get_fire_sprite() {
		return m_fireSprite;
	}

	public BufferedImage get_projectile_sprite() {
		return m_projectileSprite;
	}

	public BufferedImage get_boom_sprite() {
		return m_boomSprite;
	}

	public Direction rand_direction() {
		switch (rand.nextInt(4)) {
		case 0:
			return Direction.NORTH;

		case 1:
			return Direction.EAST;

		case 2:
			return Direction.SOUTH;
		default:
			return Direction.WEST;
		}
	}

	private void loadSprites() {
		File imageFile;

		imageFile = new File("game.whaler/sprites/water.png");
		try {
			m_waterSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/whale.png");
		try {
			m_whaleSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/player.png");
		try {
			m_playerSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/stone.png");
		try {
			m_stoneSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/projectile.png");
		try {
			m_projectileSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/whaler.png");
		try {
			m_whalerSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * 
		 */
		imageFile = new File("game.whaler/sprites/boom.png");
		try {
			m_boomSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/destroyer.png");
		try {
			m_destroyerSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/island.png");
		try {
			m_islandSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/iceberg.png");
		try {
			m_icebergSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/oil.png");
		try {
			m_oilSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/underwater.png");
		try {
			m_underSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/bulles.png");
		try {
			m_bulleUnderSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/stoneUnder.png");
		try {
			m_stoneUnderSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/yellow_algae.png");
		try {
			m_yellowAlgaeUnderSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/coral.png");
		try {
			m_coralUnderSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/submarine.png");
		try {
			m_playerUnderSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.whaler/sprites/Fire_Sprite.png");
		try {
			m_fireSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/red_coral.png");
		try {
			m_redCoralUnderSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}
}
