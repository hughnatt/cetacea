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
	
	//some public static variables, final or not, initialized or not
	public static boolean VERBOSE = false;

	// some public static variables, final or not, initialized or not

	public static final int DIM_BLOC = 32; // coté d'un bloc en pixel

	public static final int DIMX_MAP = 1000; // dim de la map
	public static final int DIMY_MAP = 1000;

	public static final int DIMX_WINDOW = 1000; // dim de la fenêtre en pixel
	public static final int DIMY_WINDOW = 720;

	public static final int DIMX_VIEW = 31; // dim de la view en pixel
	public static final int DIMY_VIEW = 21;
	
	public static final int MAX_WHALERS = 10; //nb max d'entités
	public static final int MAX_DESTROYERS = 10;
	public static final int MAX_PROJECTILES = 10;
	public static final int MAX_OIL = 10;
	public static final int MAX_WHALES = 10;
}
