package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Stone extends Static_Entity {

	/**
	 * @param m_pos
	 * @param m_solid
	 * @param m_sprite
	 * @param m_model
	 */
	protected Stone(Location pos, BufferedImage sprite, Model model) {
		super(pos, true, sprite, model);
		m_model.m_map.m_tiles[pos.x][pos.y].addBackground(this);
	}

	@Override
	public void paint(Graphics g, Location ref_map) {
		g.drawImage(m_sprite, (this.getx() - ref_map.x) * 32, (this.gety() - ref_map.y) * 32, 32, 32, null);
	}

}
