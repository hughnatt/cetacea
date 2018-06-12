package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Game_exception.Game_exception;
import edu.ricm3.game.whaler.Game_exception.Map_exception;

public final class Whale extends Mobile_Entity {

	// public int m_capture; // Catch gauge
	int m_damage; // DPS to the hit
	private boolean m_pop_triggered; // true until the jet reach its maximal height

	/* BufferedImage Array to store the sprite */
	int m_sprite_idx;
	BufferedImage[] m_sprites;

	/**
	 * @param pos
	 * @param sprite
	 * @param underSprite
	 * @param model
	 * @param dir
	 * @throws Game_exception
	 */

	public Whale(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir, int life)
			throws Game_exception {
		super(pos, true, sprite, underSprite, model, dir, life);
		m_damage = Options.WHALE_DPS;
		m_pop_triggered = false;

		// Default sprite index is 0 (first image)
		m_sprite_idx = 0;

		// Loading the different part of the sprite
		assert (m_sprite.getHeight() == 32 * 6);
		assert (m_sprite.getWidth() == 32);

		m_sprites = new BufferedImage[6];

		for (int i = 0; i < 6; i++) {
			m_sprites[i] = m_sprite.getSubimage(0, 32 * i, 32, 32);
		}
	}

	@Override
	public void destroy() throws Game_exception {
		m_model.map().tile(m_pos).remove(this);
		m_model.m_garbage.add(this);
	}

	@Override
	public void step(long now) throws Game_exception {
		if ((m_life == 0) || (m_life == Options.WHALE_CAPTURE_MAX)) { // Catching or liberation of the whale
			m_model.map().tile(this.getx(), this.gety()).remove(this);
		}

		long elapsed = now - this.m_lastStep;
		if (elapsed > 200L) { // speed for the changement of sprites

			m_lastStep = now;

			if (m_pop_triggered) {

				if (m_sprite_idx < 5) { // the jet increases
					m_sprite_idx++;
				} else {
					m_pop_triggered = false;
				}

			} else {
				if (m_sprite_idx != 0) { // the jet decreases
					m_sprite_idx--;
				}
			}
		}
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprites[m_sprite_idx], (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {

	}

	@Override
	public void pop() {
		m_pop_triggered = true; // Triggering of the jet
	}

	@Override
	public void wizz() throws Game_exception {
		int transl_x = m_model.rand.nextInt(Options.MAX_RANGE_WHALE_ESCAPE); // calculation of the translation of the
																				// whale in x
		if (m_model.rand.nextInt(2) == 1) {
			transl_x *= -1;
		}

		int transl_y = m_model.rand.nextInt(Options.MAX_RANGE_WHALE_ESCAPE); // calculation of the translation of the
																				// whale in y
		if (m_model.rand.nextInt(2) == 1) {
			transl_y *= -1;
		}

		m_model.map().tile(this.getx(), this.gety()).remove(this);
		this.m_pos = new Location(this.getx() + transl_x, this.gety() + transl_y);

		m_model.map().tile(this.getx(), this.gety()).addForeground(this);
	}

	@Override
	public void hit() throws Game_exception {
		Location new_pos = this.pos_front();

		Entity result = m_model.map().tile(new_pos).contain(Player.class); // Is there a player ?
		if (result != null) {
			Player result_player = (Player) result;
			result_player.m_life -= m_damage; // if yes, it takes damages
		}

		result = m_model.map().tile(new_pos).contain(Whaler.class); // Is there a whaler ?
		if (result != null) {
			Whaler result_whaler = (Whaler) result;
			result_whaler.m_life -= m_damage; // if yes, it takes damages
		}

		result = m_model.map().tile(new_pos).contain(Destroyer.class); // Is there a destroyer ?
		if (result != null) {
			Destroyer result_destroyer = (Destroyer) result;
			result_destroyer.m_life -= m_damage; // if yes, it takes damages
		}
	}

}
