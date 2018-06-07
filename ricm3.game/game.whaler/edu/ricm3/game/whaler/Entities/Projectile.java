package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Game_exception.Map_exception;

public class Projectile extends Mobile_Entity {

	int m_remaining; // before destruction, 0 = destruction at the next move
	int m_damage; // The damage that will be done by the projectile

	/**
	 * @param pos
	 *            Initial position of the Projectile
	 * @param sprite
	 * @param model
	 * @param direction
	 *            Indicate the direction of the projectile
	 * @param range
	 *            Indicate the range of the projectile
	 * @param damage
	 *            Damage power
	 */
	public Projectile(Location m_pos, BufferedImage m_sprite, Model m_model, Direction dir, int range, int damage)
			throws Map_exception {
		super(m_pos, false, m_sprite, m_model, dir);
		m_remaining = range;
		m_damage = damage;
	}

	@Override
	public void step(long now) {
		long elapsed = this.m_lastStep - now;
		if (elapsed > 500L) {
			if (m_remaining == 0) {

			}
		}
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

	public void wizz() {
		// TODO
	}

	public void pop() {
		// TODO
	}

	public void hit() {
		// TODO
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {

	}

}
