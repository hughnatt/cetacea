package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Game_exception.Map_exception;

public final class Oil extends Mobile_Entity {

	boolean is_burning;
	int m_idx;

	BufferedImage[] m_spriteFire;

	/**
	 * @param pos
	 * @param sprite
	 * @param model
	 */
	public Oil(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir)
			throws Map_exception {
		super(pos, false, sprite, underSprite, model, dir);
		this.is_burning = false;

		m_spriteFire = new BufferedImage[32];

		for (int i = 0; i < 32; i++) {
			m_spriteFire[i] = m_model.get_fire_sprite().getSubimage(0, 32 * i, 32, 32);
		}
	}

	@Override
	public void step(long now) {
		if (is_burning) {
			long elapsed = now - m_lastStep;
			if (elapsed > 100L) {
				m_idx = (m_idx + 1) % m_spriteFire.length;
				m_lastStep = now;
			}

		}
	}

	@Override
	public void paint(Graphics g, Location map_ref) {

		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
		if (is_burning) {
			g.drawImage(m_spriteFire[m_idx], (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
		}
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {

	}

	@Override
	public void pop() {
		// TODO
	}

	@Override
	public void wizz() {
		this.is_burning = true;

	}

	@Override
	public void hit() {
		// TODO
	}

}
