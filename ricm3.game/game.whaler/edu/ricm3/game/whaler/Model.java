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
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.parser.Ast;
import edu.ricm3.game.parser.Ast.*;
import edu.ricm3.game.parser.AutomataParser;
import edu.ricm3.game.whaler.Entities.Bulle;
import edu.ricm3.game.whaler.Entities.Coral;
import edu.ricm3.game.whaler.Entities.Destroyer;
import edu.ricm3.game.whaler.Entities.Iceberg;
import edu.ricm3.game.whaler.Entities.Island;
import edu.ricm3.game.whaler.Entities.Oil;
import edu.ricm3.game.whaler.Entities.Player;
import edu.ricm3.game.whaler.Entities.Projectile;
import edu.ricm3.game.whaler.Entities.Stone;
import edu.ricm3.game.whaler.Entities.Whale;
import edu.ricm3.game.whaler.Entities.Whaler;
import edu.ricm3.game.whaler.Interpretor.IAutomata;
import edu.ricm3.game.whaler.Entities.YellowAlgae;
import edu.ricm3.game.whaler.Game_exception.Location_exception;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;

public class Model extends GameModel {

	//enum for the menu, to determine which screen should be displayed
	public enum Screen {
		AUTOMATA,HOME,GAME,PREFERENCES;
	}

	private Screen m_screen;

	//getter for Screen
	public Screen currentScreen() {
		return m_screen;
	}

	//setter for Screen
	public void setScreen(Screen s) {
		m_screen = s;
	}

	// Sprite-sheets (BufferedImage) and instances of elements

	private BufferedImage m_whaleSprite;
	private BufferedImage m_playerSprite;
	private BufferedImage m_stoneSprite;
	private BufferedImage m_projectileSprite;
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

	// Entity List
	Player m_player;
	Destroyer[] m_destroyers;
	Whaler[] m_whalers;
	Projectile[] m_projectiles;
	Whale[] m_whales;
	Oil[] m_oil;

	// Random generation
	public Random rand = new Random();

	public Model() throws Exception {

		// Set the current screen on the home menu
		m_screen = Screen.HOME;
		
		new AutomataParser(new BufferedReader(new FileReader("game.parser/example/automata.txt")));
		//Loading automate file
		Ast ast = AutomataParser.Run();
		//ast = AutomataParser.Run();
		IAutomata[] automata_array = ((AI_Definitions) ast).make();

		
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

		// Bulles

		new Bulle(new Location(2, 2), null, m_bulleUnderSprite, this);
		new Bulle(new Location(8, 16), null, m_bulleUnderSprite, this);
		new Bulle(new Location(23, 6), null, m_bulleUnderSprite, this);

		// Algues
		new YellowAlgae(new Location(6, 10), null, m_yellowAlgaeUnderSprite, this);
		new YellowAlgae(new Location(22, 18), null, m_yellowAlgaeUnderSprite, this);
		new YellowAlgae(new Location(4, 17), null, m_yellowAlgaeUnderSprite, this);

		// Corail
		new Coral(new Location(10, 6), null, m_coralUnderSprite, this);
		new Coral(new Location(20, 18), null, m_coralUnderSprite, this);
		new Coral(new Location(20, 2), null, m_coralUnderSprite, this);

		new Bulle(new Location(2, 2), null, m_bulleUnderSprite, this);

		// Stones
		for (int i = 0; i < Options.DIMX_MAP; i++) {

			new Stone(new Location(i, 0), m_stoneSprite, m_stoneUnderSprite, this);
			new Stone(new Location(i, Options.DIMY_MAP - 1), m_stoneSprite, m_stoneUnderSprite, this);
		}
		for (int i = 0; i < Options.DIMY_MAP; i++) {
			new Stone(new Location(0, i), m_stoneSprite, m_stoneUnderSprite, this);
			new Stone(new Location(Options.DIMX_MAP - 1, i), m_stoneSprite, m_stoneUnderSprite, this);

		}

		// Islands

		new Island(new Location(3, 6), m_islandSprite, null, this);
		// Icebergs
		new Iceberg(new Location(3, 7), m_icebergSprite, null, this);

		// Entities

		// TODO problème à régler : une instance qui n'est plus sur la map, reste dans
		// les tableaux et donc en mémoire. Il faut mettre à jour les tableaux ou écrire
		// une fonction DEL. Cette fonctionnalité serait utile dans Projectile et Whale
		// déjà

		// Oil
		m_oil = new Oil[Options.MAX_OIL];

		m_oil[0] = new Oil(new Location(3, 2), m_oilSprite, null, this, Direction.WEST);

		// Destroyers
		m_destroyers = new Destroyer[Options.MAX_DESTROYERS];
		m_destroyers[0] = new Destroyer(new Location(3, 4), m_destroyerSprite, null, this, Direction.WEST);

		// Whalers
		m_whalers = new Whaler[Options.MAX_WHALERS];
		m_whalers[0] = new Whaler(new Location(3, 5), m_whalerSprite, null, this, Direction.WEST);

		// Whales
		m_whales = new Whale[Options.MAX_WHALES];
		m_whales[0] = new Whale(new Location(3, 8), m_whaleSprite, null, this, Direction.WEST);

		// Projectiles
		m_projectiles = new Projectile[Options.MAX_PROJECTILES];

		m_projectiles[0] = new Projectile(new Location(3, 9), m_projectileSprite, null, this, Direction.WEST, 0, 0);

		// Player
		m_player = new Player(new Location(3, 3), m_playerSprite, m_playerUnderSprite, this, Direction.WEST, automata_array[0]);

	}

	public Map map() {
		return m_map;
	}

	@Override
	public void step(long now) {
	
		try {
			m_player.step(now);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			m_current_background.step(now);
			m_whales[0].step(now);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
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
	}
}
