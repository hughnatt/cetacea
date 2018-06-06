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


import edu.ricm3.game.GameModel;

public class Model extends GameModel {
	// Sprite-sheets (BufferedImage) and instances of elements
	Score m_score;
	public Model() {
		loadSprites();
		m_score = new Score(this, 20, 50, 1);
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

		// call the method step of each instances
	}

	private void loadSprites() {
		// example of a load

		/*
		 * Description; extention; size (64x64for example) px sprite size Author (source
		 * site)
		 */
		// File imageFile = new File("game.sample/sprites/random_sprite.png");
		// try {
		// BufferedImage_instance = ImageIO.read(imageFile);
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// System.exit(-1);
		// }
	}

}
