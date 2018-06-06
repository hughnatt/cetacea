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

public class Model extends GameModel {
	// Sprite-sheets (BufferedImage) and instances of elements

	private BufferedImage m_whaleSprite;
	private BufferedImage m_playerSprite;
	private BufferedImage m_stoneSprite;
	private BufferedImage m_projectileSprite;
	private BufferedImage m_whalerSprite;
	private BufferedImage m_boomSprite;
	private BufferedImage m_waterSprite;
	
	//Background
	Background m_ocean;

	//Map
	Map m_map;
	
	//Entity List
	Player m_player;
	
	
	public Model() {
		//Loading Sprites Model
		loadSprites();
		
		//Animated Ocean Background
		m_ocean = new Water(m_waterSprite,this);
		
		//Creating the map
		m_map = new Map(this);
		
		//Entities
		m_player = new Player(new Location(3,3), true, m_playerSprite,this);
		
		
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

		m_ocean.step(now);
		//m_map.step(); Is this a good idea ?
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
		imageFile = new File("game.whaler/sprites/destroyer.png");
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

	}

}
