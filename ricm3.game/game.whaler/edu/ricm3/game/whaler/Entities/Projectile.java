package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Tile;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;

public class Projectile extends Mobile_Entity {

	int m_remaining; // before destruction, 0 = destruction at the next move
	int m_damage; // The damage that will be done by the projectile

	private long m_speed;

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

	public Projectile(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir,
			int range, int damage) throws Map_exception {
		super(pos, false, sprite, underSprite, model, dir);
		m_remaining = range;
		m_damage = Options.PROJECTILE_DPS;
		m_speed = Options.PROJECTILE_SPD_STANDARD;
	}

	@Override
	public void step(long now) throws Map_exception, Tile_exception {
		long elapsed = now - this.m_lastStep;

		if (elapsed > m_speed) {

			m_lastStep = now;

			m_model.map().tile(this.getx(), this.gety()).remove(this);
			if (m_remaining != 0) {

				Location new_pos = this.pos_front();

				Tile tile = m_model.map().tile(new_pos); // Tile for the new location

				boolean hit = false;

				Entity result = tile.contain(Whale.class); // Is there a whale ?
				if (result != null) {
					Whale result_whale = (Whale) result;
					result_whale.m_capture += m_damage; // if yes, it takes damages
					hit = true;
				}

				result = tile.contain(Player.class); // Is there a player ?
				if (result != null) {
					Player result_player = (Player) result;
					result_player.m_life -= m_damage; // if yes, it takes damages
					hit = true;
				}

				result = tile.contain(Destroyer.class); // Is there a destroyer ?
				if (result != null) {
					Destroyer result_destroyer = (Destroyer) result;
					result_destroyer.m_life -= m_damage; // if yes, it takes damages
					hit = true;
				}

				result = tile.contain(Whaler.class); // Is there a whaler ?
				if (result != null) {
					Whaler result_whaler = (Whaler) result;
					result_whaler.m_life -= m_damage; // if yes, it takes damages
					hit = true;
				}

				if (!hit) { // if the projectile hasn't hit nothing
					m_model.map().tile(new_pos.x, new_pos.y).addForeground(this); // it goes to the new lcoation
				}
			}
		}
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

	public void pop() {
		m_speed = Options.PROJECTILE_SPD_IMPROVED;
	}

	public void wizz() {
		m_direction = m_model.rand_direction();
	}

	public void hit() {
		// TODO qqch à faire ?
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {

	}

}
