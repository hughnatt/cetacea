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
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.whaler.Entities.Bulle;
import edu.ricm3.game.whaler.Entities.Destroyer;
import edu.ricm3.game.whaler.Entities.Iceberg;
import edu.ricm3.game.whaler.Entities.Island;
import edu.ricm3.game.whaler.Entities.Oil;
import edu.ricm3.game.whaler.Entities.Player;
import edu.ricm3.game.whaler.Entities.Projectile;
import edu.ricm3.game.whaler.Entities.Stone;
import edu.ricm3.game.whaler.Entities.Whale;
import edu.ricm3.game.whaler.Entities.Whaler;
import edu.ricm3.game.whaler.Game_exception.Map_exception;

public class Model extends GameModel {
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

	private BufferedImage m_underSprite;
	private BufferedImage m_bulleSprite;

	// Boolean indiquant si le joueur est underwater
	public static boolean UNDER_WATER = false;

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

	public Model() throws Map_exception {
		// Loading Sprites Model
		loadSprites();

		// Animated Ocean Background
		m_ocean = new Water(m_waterSprite, this);
		m_underwater = new Underwater(m_underSprite, this);
		m_current_background = m_ocean;

		/*** Creating the map ***/

		m_map = new Map(this);

		// Bulles
		new Bulle(new Location(2, 2), m_bulleSprite, this);

		// Stones
		for (int i = 0; i < Options.DIMX_MAP; i++) {
			new Stone(new Location(i, 0), m_stoneSprite, this);
			new Stone(new Location(i, Options.DIMY_MAP - 1), m_stoneSprite, this);
		}
		for (int i = 0; i < Options.DIMY_MAP; i++) {
			new Stone(new Location(0, i), m_stoneSprite, this);
			new Stone(new Location(Options.DIMX_MAP - 1, i), m_stoneSprite, this);
		}

		// Islands
		new Island(new Location(3, 6), m_islandSprite, this);
		// Icebergs
		new Iceberg(new Location(3, 7), m_icebergSprite, this);

		// Entities

		// Oil
		m_oil = new Oil[Options.MAX_OIL];
		m_oil[0] = new Oil(new Location(3, 2), m_oilSprite, this, Direction.WEST);

		// Destroyers
		m_destroyers = new Destroyer[Options.MAX_DESTROYERS];
		m_destroyers[0] = new Destroyer(new Location(3, 4), m_destroyerSprite, this, Direction.WEST);

		// Whalers
		m_whalers = new Whaler[Options.MAX_WHALERS];
		m_whalers[0] = new Whaler(new Location(3, 5), m_whalerSprite, this, Direction.WEST);

		// Whales
		m_whales = new Whale[Options.MAX_WHALES];
		m_whales[0] = new Whale(new Location(3, 8), m_whaleSprite, this, Direction.WEST);

		// Projectiles
		m_projectiles = new Projectile[Options.MAX_PROJECTILES];
		m_projectiles[0] = new Projectile(new Location(3, 9), m_projectileSprite, this, Direction.WEST, 0, 0);

		// Player
		m_player = new Player(new Location(3, 3), m_playerSprite, this, Direction.WEST);
	}

	public Map map() {
		return m_map;
	}

	@Override
	public void shutdown() {

	}

	/**
	 * Simulation step.
	 * 
	 * @param now
	 *            is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {

		m_current_background.step(now);
		// m_map.step(); Is this a good idea ?
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

	private void loadSprites() {

		File imageFile;
		/*
		 * Texture from Minecraft Faithful 32 RessourcePack
		 * https://www.curseforge.com/minecraft/texture-packs/faithful-32x
		 */
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
			m_bulleSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

}
