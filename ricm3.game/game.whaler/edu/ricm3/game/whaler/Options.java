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

public class Options {

	// some public static variables, final or not, initialized or not

	public static final boolean USE_DOUBLE_BUFFERING = true;

	public static final int UNDERGROUND_FLORE_POURCENTAGE = 12;
	public static final int SEA_ELEMENTS_POURCENTAGE = 4;

	public static final int DIM_BLOC = 32; // coté d'un bloc en pixel

	public static final int DIMX_MAP = 500; // dim de la map en bloc
	public static final int DIMY_MAP = 500;

	public static final int DIMX_WINDOW = 1040;// dim de la fenêtre en pixel
	public static final int DIMY_WINDOW = 752;

	public static final int DIMX_VIEW = 31; // dim de la view en blocs
	public static final int DIMY_VIEW = 21;

	// TODO il faudra fixer des limites de spawn
	public static final int MAX_WHALERS = 10; // nb max d'entités
	public static final int MAX_DESTROYERS = 10;
	public static final int MAX_PROJECTILES = 10;
	public static final int MAX_OIL = 1000;
	public static final int MAX_WHALES = 10;

	public static final int PLAYER_LIFE = 20;
	public static final int DESTROYER_LIFE = 20;
	public static final int WHALER_LIFE = 20;
	public static final int OIL_LIFE = 10;
	public static final int WHALE_LIFE = 10;

	public static final int PROJECTILE_DPS = 3;
	public static final long PROJECTILE_SPD_STANDARD = 150L;
	public static final long PROJECTILE_SPD_IMPROVED = 100L;
	public static final int PROJECTILE_RANGE = 6;

	public static final int MAX_RANGE_WHALE_ESCAPE = 10;
	public static final int WHALE_DPS = 1;
	public static final int WHALE_LIFE_MAX = 20;

	public static final long DESTROYER_SPD_STANDARD = 500L;
	public static final long DESTROYER_SPD_IMPROVED = 1000L;

	public static final long OIL_PICKED = 5;

	public static final int OIL_POURCENTAGE_POP = 100;

	public static final long BURNING_OIL_SPD_BURNING = 200L;
	public static final long BURNING_OIL_SPD_SPREAD = 300L;
	public static final int BURNING_OIL_DPS = 3;
	
	public static final int PLAYER_OIL_GAUGE = 21;

	public static final double FPS = 30.0;
	public static final int REPAINT_DELAY = (int) (1000.0 / FPS);

}
