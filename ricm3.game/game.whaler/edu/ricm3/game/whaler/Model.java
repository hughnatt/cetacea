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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;

public class Model extends GameModel {
  //Sprite-sheets (BufferedImage) and instances of elements
	BufferedImage m_buttonSprite;
	BufferedImage m_scoreSprite;
	BufferedImage m_baleinemenuSprite;
	BufferedImage m_destroyer_menuSprite;
	BufferedImage m_projectile_menuSprite;
	Menu m_menu;
	
  public Model() {
    loadSprites();
	m_menu = new Menu(this, 350,150,2);
  }
  
  @Override
  public void shutdown() {
    
  }
  
  /**
   * Simulation step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  @Override
  public void step(long now) {
    
    //call the method step of each instances
  }

  private void loadSprites() {
	  File imageFile;
		
		imageFile = new File("game.whaler/sprites/play.png");
		try {
			m_buttonSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.whaler/sprites/baleine_menu.png");
		try {
			m_baleinemenuSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		
		imageFile = new File("game.whaler/sprites/Destroyer_menu.png");
		try {
			m_destroyer_menuSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		imageFile = new File("game.whaler/sprites/Projectile_menu.png");
		try {
			m_projectile_menuSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	
  }

}
