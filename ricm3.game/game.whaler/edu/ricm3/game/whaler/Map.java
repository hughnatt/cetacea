package edu.ricm3.game.whaler;

import java.awt.Graphics;


public class Map {

	Tile m_tiles[][];
	Model m_model;
	
	/**
	 * Game Map for instantly accessing entities' location
	 * @param model Internal Model
	 */
	public Map(Model model) {
		
		//Initializing the Tiles
		m_tiles = new Tile[Options.DIMX_MAP][Options.DIMY_MAP];
		for (int i = 0; i < Options.DIMX_MAP; i++) {
			for (int j = 0; j < Options.DIMY_MAP; j++) {
				m_tiles[i][j] = new Tile();
			}
		}
		
		m_model = model;
	}

	public Tile tile(int x, int y) {
		return m_tiles[x][y];
	}
	
	/**
	 * 'Viewport' rendering of the map
	 * @param g
	 */
	public void paint(Graphics g) {
		// We'll render only the part of the map which is visible

		//Getting player location
		int px = m_model.m_player.getx();
		int py = m_model.m_player.gety();

		// Reference Point for rendering the map (top left corner Tile)
		int map_x = px - 15;
		int map_y = py - 10;

		// If the reference point is out of the map, we get it back in
		if (map_x < 0) {
			map_x = 0;
		}
		if (map_x > Options.DIMX_MAP - Options.DIMX_VIEW) {
			map_x = Options.DIMX_MAP - Options.DIMX_VIEW;
		}
		if (map_y < 0) {
			map_y = 0;
		}
		if (map_y > Options.DIMY_MAP - Options.DIMY_VIEW) {
			map_y = Options.DIMY_MAP - Options.DIMY_VIEW;
		}
		

		// Render the map with X*Y Tiles
		for (int i = map_x; i < map_x + Options.DIMX_VIEW; i++) {
			for (int j = map_y; j < map_y + Options.DIMY_VIEW; j++) {
				m_tiles[i][j].paint(g, new Location(map_x,map_y));
			}
		}
	}
	
	/**
	 * 'Viewport' rendering of the map
	 * @param g
	 */
	public void paint_under(Graphics g) {
		// We'll render only the part of the map which is visible

		//Getting player location
		int px = m_model.m_player.getx();
		int py = m_model.m_player.gety();

		// Reference Point for rendering the map (top left corner Tile)
		int map_x = px - 15;
		int map_y = py - 10;

		// If the reference point is out of the map, we get it back in
		if (map_x < 0) {
			map_x = 0;
		}
		if (map_x > Options.DIMX_MAP - Options.DIMX_VIEW) {
			map_x = Options.DIMX_MAP - Options.DIMX_VIEW;
		}
		if (map_y < 0) {
			map_y = 0;
		}
		if (map_y > Options.DIMY_MAP - Options.DIMY_VIEW) {
			map_y = Options.DIMY_MAP - Options.DIMY_VIEW;
		}
		

		// Render the map with X*Y Tiles
		for (int i = map_x; i < map_x + Options.DIMX_VIEW; i++) {
			for (int j = map_y; j < map_y + Options.DIMY_VIEW; j++) {
				m_tiles[i][j].paint_under(g, new Location(map_x,map_y));
			}
		}
	}
}
