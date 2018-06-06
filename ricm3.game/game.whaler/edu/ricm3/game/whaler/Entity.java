package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {

	protected class Location {
		int x, y; // number of cases, not pixels

		protected Location(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void up() {
			if (this.y > 0) {
				this.y--;
			}
		}

		public void down() {
			if (this.y < Options.DIMY_MAP) {
				y++;
			}
		}

		public void left() {
			if (this.x > 0) {
				this.x--;
			}
		}

		public void right() {
			if (this.x < Options.DIMX_MAP) {
				x++;
			}
		}
	}

	Location m_pos;
	boolean m_solid;

	BufferedImage m_sprite;

	Model m_model;

	/**
	 * @param m_pos
	 * @param m_solid
	 * @param m_sprite
	 * @param m_model
	 */
	protected Entity(Location m_pos, boolean m_solid, BufferedImage m_sprite, Model m_model) {
		this.m_pos = m_pos;
		this.m_solid = m_solid;
		this.m_sprite = m_sprite;
		this.m_model = m_model;
	}

	/**
	 * @param g
	 * @param p_ref_map
	 */
	public abstract void paint(Graphics g, Location p_ref_map);

}
