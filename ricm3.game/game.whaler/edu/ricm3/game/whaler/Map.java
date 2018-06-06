package edu.ricm3.game.whaler;

import java.awt.Graphics;

public class Map {
	// Tile[DIMX_MAP][DIMY_MAP] tiles;
	Tile tiles[][];

	Model m_model;

	/**
	 * 
	 */
	protected Map() {
		tiles = new Tile[Options.DIMX_MAP][Options.DIMY_MAP];
		for (int i = 0; i < Options.DIMX_MAP; i++) {
			for (int j = 0; j < Options.DIMY_MAP; j++) {
				tiles[i][j] = new Tile();
			}
		}
	}

	/**
	 * @param g
	 */
	public void paint(Graphics g) {

	}
}
