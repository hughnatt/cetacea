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
import edu.ricm3.game.whaler.Entities.Bulle;
import edu.ricm3.game.whaler.Entities.Coral;
import edu.ricm3.game.whaler.Entities.Destroyer;
import edu.ricm3.game.whaler.Entities.Entity.EntityType;
import edu.ricm3.game.whaler.Entities.Iceberg;
import edu.ricm3.game.whaler.Entities.Island;
import edu.ricm3.game.whaler.Entities.MobileEntity;
import edu.ricm3.game.whaler.Entities.Oil;
import edu.ricm3.game.whaler.Entities.Player;
import edu.ricm3.game.whaler.Entities.Projectile;
import edu.ricm3.game.whaler.Entities.RedCoral;
import edu.ricm3.game.whaler.Entities.StaticEntity;
import edu.ricm3.game.whaler.Entities.Stone;
import edu.ricm3.game.whaler.Entities.Whale;
import edu.ricm3.game.whaler.Entities.Whaler;
import edu.ricm3.game.whaler.Entities.YellowAlgae;
import edu.ricm3.game.whaler.Game_exception.Automata_Exception;
import edu.ricm3.game.whaler.Game_exception.Game_exception;
import edu.ricm3.game.whaler.Game_exception.Location_exception;
import edu.ricm3.game.whaler.Interpretor.IAutomata;
import edu.ricm3.game.whaler.Interpretor.ICondition;

public class Model extends GameModel {
	private BufferedImage m_whaleSprite;
	private BufferedImage m_playerSprite;
	private BufferedImage m_whalerSprite;
	private BufferedImage m_waterSprite;
	private BufferedImage m_destroyerSprite;
	private BufferedImage m_islandSprite;
	private BufferedImage m_icebergSprite;
	private BufferedImage m_oilSprite;
	private BufferedImage m_boomSprite;
	private BufferedImage m_underSprite;
	private BufferedImage m_bulleUnderSprite;
	private BufferedImage m_yellowAlgaeUnderSprite;
	private BufferedImage m_coralUnderSprite;
	private BufferedImage m_playerUnderSprite;
	private BufferedImage m_fireSprite;
	private BufferedImage m_redCoralUnderSprite;

	private BufferedImage m_projectileSprite;
	private BufferedImage m_stoneSprite;
	private BufferedImage m_stoneUnderSprite;

	// Automaton array
	public IAutomata[] automata_array;
	// Automata Choices
	int[] automata_choices;

	// Boolean true if the player is under the surface
	public boolean UNDER_WATER;

	// Background
	Background m_current_background;
	Background m_ocean;
	Background m_underwater;

	// Map
	private Map m_map;

	List<StaticEntity> m_statics = new LinkedList<StaticEntity>();

	// Mobile Entity List
	public Player m_player;
	public List<Destroyer> m_destroyers = new LinkedList<Destroyer>();
	public List<Whaler> m_whalers = new LinkedList<Whaler>();
	public List<Projectile> m_projectiles = new LinkedList<Projectile>();
	public List<Whale> m_whales = new LinkedList<Whale>();
	public List<Oil> m_oils = new LinkedList<Oil>();
	public List<MobileEntity> m_garbage;

	public boolean[] keyPressed;
	// Random generation
	public Random rand = new Random();

	long m_lastSwap;

	public Score m_score;

	/*
	 * Side Panel Icon, leave PUBLIC ! ! !
	 */
	public BufferedImage m_bartopSprite;
	public BufferedImage m_baremptySprite;
	public BufferedImage m_lifefullSprite;
	public BufferedImage m_bardownSprite;
	public BufferedImage m_oilfullSprite;

	public Model() throws FileNotFoundException, Automata_Exception, Game_exception, ParseException {

		new AutomataParser(new BufferedReader(new FileReader("game.parser/example/automata.txt")));
		// Loading automate file
		Ast ast = AutomataParser.Run();
		automata_array = ((AI_Definitions) ast).make();

		// Loading setting file
		// 6 Entities
		automata_choices = new int[EntityType.values().length];

		// Setting up the Danger Level Matrice
		ICondition.fillDangerLevelMatrice();

		BufferedReader bis = null;
		try {
			bis = new BufferedReader(new FileReader(new File("game.whaler/sprites/choix_automates.txt")));

			automata_choices[EntityType.WHALE.ordinal()] = Integer.parseInt(bis.readLine());
			automata_choices[EntityType.WHALER.ordinal()] = Integer.parseInt(bis.readLine());
			automata_choices[EntityType.DESTROYER.ordinal()] = Integer.parseInt(bis.readLine());
			automata_choices[EntityType.PLAYER.ordinal()] = Integer.parseInt(bis.readLine());
			automata_choices[EntityType.OIL.ordinal()] = Integer.parseInt(bis.readLine());
			automata_choices[EntityType.PROJECTILE.ordinal()] = Integer.parseInt(bis.readLine());
			// TODO
			bis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Loading Sprites Model
		loadSprites();

		// Animated Ocean Background
		m_ocean = new Water(m_waterSprite, this);
		m_underwater = new Underwater(m_underSprite, this);
		m_current_background = m_ocean;

		// Which key is currently pressed ?
		keyPressed = new boolean[128];

		m_score = new Score(this, 20, 50, 1);

		m_lastSwap = Integer.MAX_VALUE;

		/*** Creating the map ***/

		m_map = new Map(this);

		generateMap();

	}

	private void generateMap() throws Location_exception, Game_exception {
		// Underground generation
		undergroundFloreGenerator(Options.UNDERGROUND_FLORE_POURCENTAGE);
		seaGenerator(Options.SEA_ELEMENTS_POURCENTAGE);

		// Stones Border
		for (int i = 0; i < Options.DIMX_MAP; i++) {
			m_statics.add(new Stone(new Location(i, 0), m_stoneSprite, m_stoneUnderSprite, this));
			m_statics.add(new Stone(new Location(i, Options.DIMY_MAP - 1), m_stoneSprite, m_stoneUnderSprite, this));

		}
		for (int i = 0; i < Options.DIMY_MAP; i++) {
			m_statics.add(new Stone(new Location(0, i), m_stoneSprite, m_stoneUnderSprite, this));
			m_statics.add(new Stone(new Location(Options.DIMX_MAP - 1, i), m_stoneSprite, m_stoneUnderSprite, this));
		}

		// TODO : GENERATE ENTITIES AND REPOP PROCESS
		entitiesGenerator();

		// Player
		m_player = new Player(new Location(3, 3), m_playerSprite, m_playerUnderSprite, this, Direction.WEST);

		m_lastSwap = 10000000000L;

	}

	/**
	 * Génère les entités sur la Map
	 * 
	 * @throws Game_exception
	 */
	private void entitiesGenerator() throws Game_exception {
		/*
		 * 1) Générer les Baleines au hasard 2) Générer les Baleiniers (dans un rayon
		 * entre 10 et 20 des baleines 3) Générer les Destroyers 4) Générer le pétrole
		 * (Full Random)
		 */
		
		for (int i = 0; i < Options.MAX_OIL; i++) {
			boolean found_spawnpos = false;
			while (!found_spawnpos) {
				int rx = this.rand.nextInt(Options.DIMX_VIEW);
				int ry = this.rand.nextInt(Options.DIMY_VIEW);
				if (!map().tile(rx, ry).isSolid()) {
					m_oils.add(new Oil(new Location(rx, ry), m_oilSprite, null, this));
				}

				found_spawnpos = true;
			}
		}

		m_lastSwap = 10000000000L;

	}

	public Map map() {
		return m_map;
	}

	@Override
	public void step(long now) {

		m_lastSwap++; // Tick Number

		try {

			m_garbage = new LinkedList<MobileEntity>();

			m_current_background.step(now);

			m_player.step(now);

			Iterator<StaticEntity> iterstatics = m_statics.iterator();
			while (iterstatics.hasNext()) {
				StaticEntity e = iterstatics.next();
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

			Iterator<MobileEntity> iterdestroy = m_garbage.iterator();
			while (iterdestroy.hasNext()) {
				MobileEntity e = iterdestroy.next();
				switch (e.getType()) {
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

	@Override
	public void shutdown() {
	}

	public IAutomata getAutomata(MobileEntity m) throws Game_exception {
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

		if (m_lastSwap > 500) { // Tick Number
			m_lastSwap = 0;
			if (UNDER_WATER) {
				m_current_background = m_ocean;
				UNDER_WATER = false;
			} else {
				m_current_background = m_underwater;
				UNDER_WATER = true;
			}
		}
	}

	/**
	 * G�n�re la flore sous-marine selon un pourcentage donn� en param�tre
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
				
				while(m_map.tile(x,i).isSolid()) {
					x = rand.nextInt((max - min) + 1) + min;
				}

				switch (flore) {
				case 0:
					m_statics.add(new Island(new Location(x, i), m_islandSprite, m_stoneUnderSprite, this));
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
		imageFile = new File("game.whaler/sprites/oil_test.png");
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

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/rocher.png");
		try {
			m_stoneSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		/*
		 * Custom Texture
		 */
		imageFile = new File("game.whaler/sprites/rocherUnder.png");
		try {
			m_stoneUnderSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.whaler/sprites/bar_top.png");
		try {
			m_bartopSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.whaler/sprites/bar_down.png");
		try {
			m_bardownSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.whaler/sprites/lifebar_empty.png");
		try {
			m_baremptySprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.whaler/sprites/oilbar_full.png");
		try {
			m_oilfullSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.whaler/sprites/lifebar_full.png");
		try {
			m_lifefullSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}
