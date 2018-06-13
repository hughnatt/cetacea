package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import edu.ricm3.game.whaler.Game_exception.Game_exception;
import edu.ricm3.game.whaler.Game_exception.Location_exception;
import edu.ricm3.game.whaler.Game_exception.Map_exception;

public class Map {

	private Tile m_tiles[][];
	Model m_model;

	/**
	 * Game Map for instantly accessing entities' location
	 * 
	 * @param model
	 *            Internal Model
	 * @throws Location_exception 
	 */
	public Map(Model model) throws Location_exception {

		// Initializing the Tiles
		m_tiles = new Tile[Options.DIMX_MAP][Options.DIMY_MAP];
		for (int i = 0; i < Options.DIMX_MAP; i++) {
			for (int j = 0; j < Options.DIMY_MAP; j++) {
				m_tiles[i][j] = new Tile(new Location(i,j));
			}
		}

		m_model = model;
	}

	/**
	 * @param x
	 * @param y
	 *            coordonnees of the tile
	 * @return Tile searched
	 * @throws Map_exception
	 */
	public Tile tile(int x, int y) throws Map_exception {
		if ((x < 0) || (x >= Options.DIMX_MAP)) {
			throw new Map_exception("Coordonnee x unfitted");
		}
		if ((y < 0) || (y >= Options.DIMY_MAP)) {
			throw new Map_exception("Coordonnee y unfitted");
		}
		return m_tiles[x][y];

		/*
		 * try { return m_tiles[x][y]; } catch (IndexOutOfBoundsException e) { throw new
		 * Map_exception(e.toString()); }
		 */

	}

	public Tile tile(Location l) throws Map_exception {
		return tile(l.x, l.y);
	}

	/**
	 * Return a list of the adjacent tiles from a position on the map
	 * 
	 * @param pos
	 * @return List of Tile with a length of 2,3 or 4
	 * @throws Game_exception
	 */
	public List<Location> posAdjacent(Location pos) throws Game_exception {
		List<Location> result = new LinkedList<Location>();

		Location tmp_pos = new Location(pos);
		tmp_pos.up();

		if ((tmp_pos.x != pos.x) || (tmp_pos.y != pos.y)) {
			result.add(tmp_pos);
		}

		tmp_pos = new Location(pos);
		tmp_pos.down();

		if ((tmp_pos.x != pos.x) || (tmp_pos.y != pos.y)) {
			result.add(tmp_pos);
		}

		tmp_pos = new Location(pos);
		tmp_pos.left();

		if ((tmp_pos.x != pos.x) || (tmp_pos.y != pos.y)) {
			result.add(tmp_pos);
		}

		tmp_pos = new Location(pos);
		tmp_pos.right();

		if ((tmp_pos.x != pos.x) || (tmp_pos.y != pos.y)) {
			result.add(tmp_pos);
		}

		return result;
	}

	/**
	 * 'Viewport' rendering of the map
	 * 
	 * @param g
	 * @throws Game_exception
	 */
	public void paint(Graphics g) throws Game_exception {
		// We'll render only the part of the map which is visible

		// Getting player location
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
				m_tiles[i][j].paint(g, new Location(map_x, map_y));
			}
		}
	}

	/**
	 * 'Viewport' rendering of the map
	 * 
	 * @param g
	 * @throws Game_exception
	 */
	public void paint_under(Graphics g) throws Game_exception {
		// We'll render only the part of the map which is visible

		// Getting player location
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
				m_tiles[i][j].paint_under(g, new Location(map_x, map_y));
			}
		}
	}
}
