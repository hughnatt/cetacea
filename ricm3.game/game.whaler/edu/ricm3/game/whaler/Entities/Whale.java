package edu.ricm3.game.whaler.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Game_exception.Automata_Exception;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public final class Whale extends MobileEntity {

	// public int m_capture; // Catch gauge
	int m_damage; // DPS to the hit
	private boolean m_pop_triggered; // true until the jet reach its maximal height

	/* BufferedImage Array to store the sprite */
	int m_sprite_idx;
	BufferedImage[] m_sprites;

	int captureTick;

	/**
	 * @param pos
	 * @param sprite
	 * @param underSprite
	 * @param model
	 * @param dir
	 * @throws Game_exception
	 */

	public Whale(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir)
			throws Game_exception {

		super(pos, true, sprite, underSprite, model, dir, Options.WHALE_LIFE);
		m_damage = Options.WHALE_DPS;
		m_pop_triggered = false;

		m_automata = m_model.getAutomata(this);

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

	public void capture() throws Game_exception {

		if (captureTick < 50) {
			return;
		}
		captureTick = 0;

		Iterator<Entity> iter_N = m_model.map().tile(m_pos.x, m_pos.y - 1).iterator();
		Iterator<Entity> iter_S = m_model.map().tile(m_pos.x, m_pos.y + 1).iterator();
		Iterator<Entity> iter_E = m_model.map().tile(m_pos.x + 1, m_pos.y).iterator();
		Iterator<Entity> iter_O = m_model.map().tile(m_pos.x - 1, m_pos.y).iterator();

		Entity e = null;

		while (iter_N.hasNext()) {

			e = iter_N.next();

			if (e instanceof Whaler) {
				m_life--;
			} else if (e instanceof Player && !(m_model.UNDER_WATER)) {
				m_life++;
			}
		}

		e = null;
		while (iter_S.hasNext()) {

			e = iter_S.next();

			if (e instanceof Whaler) {
				m_life--;
			} else if (e instanceof Player && !(m_model.UNDER_WATER)) {
				m_life++;
			}
		}

		e = null;
		while (iter_E.hasNext()) {

			e = iter_E.next();

			if (e instanceof Whaler) {
				m_life--;
			} else if (e instanceof Player && !(m_model.UNDER_WATER)) {
				m_life++;
			}
		}

		e = null;
		while (iter_O.hasNext()) {

			e = iter_O.next();

			if (e instanceof Whaler) {
				m_life--;
			} else if (e instanceof Player && !(m_model.UNDER_WATER)) {
				m_life++;
			}
		}

		if (m_life <= 0) { // Catching or liberation of the whale
			this.destroy();
			m_model.m_score.sub();
		}

		if (m_life >= Options.WHALE_LIFE_MAX) {
			this.destroy();
			m_model.m_score.add();
		}

	}

	@Override
	public void step(long now) throws Game_exception {
		long elapsed = now - this.m_lastStep;
		if (elapsed > 2000L) { // speed for the changement of sprites

			m_lastStep = now;
			try {
				m_automata.step(m_model, this);
				// En cas de problème, on capture l'exception et ke jeu continue
			} catch (Automata_Exception e) {
			} catch (Exception e) {
			}

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

		capture();
		captureTick++;
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprites[m_sprite_idx], (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
		g.setColor(Color.RED);
		g.fillRect((m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32,
				(int) (((double) m_life / (double) (Options.WHALE_LIFE_MAX)) * 32), 2);
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {
		// nothing
	}

	@Override
	public void pop() {
		m_pop_triggered = true; // Triggering of the jet
	}

	@Override
	public void wizz() throws Game_exception {
		boolean valid = false;

		Location new_pos = null;
		while (!valid) {
			int transl_x = m_model.rand.nextInt(Options.MAX_RANGE_WHALE_ESCAPE); // calculation of the translation of
																					// the
																					// whale in x
			if (m_model.rand.nextInt(2) == 1) {
				transl_x *= -1;
			}

			int transl_y = m_model.rand.nextInt(Options.MAX_RANGE_WHALE_ESCAPE); // calculation of the translation of
																					// the
																					// whale in y
			if (m_model.rand.nextInt(2) == 1) {
				transl_y *= -1;
			}

			new_pos = new Location(this.getx() + transl_x, this.gety() + transl_y);

			if (m_model.map().tile(new_pos).isSolid()) {
				new_pos = null;
			} else {
				valid = true;
			}

		}

		m_model.map().tile(this.getx(), this.gety()).remove(this);
		this.m_pos = null;
		this.m_pos = new_pos;

		m_model.map().tile(this.getx(), this.gety()).addForeground(this);

	}

	@Override
	public void hit() throws Game_exception {
		Location new_pos = this.pos_front();

		Entity result = m_model.map().tile(new_pos).contain(EntityType.PLAYER); // Is there a player ?
		if (result != null) {
			Player result_player = (Player) result;
			result_player.m_life -= m_damage; // if yes, it takes damages
		}

		result = m_model.map().tile(new_pos).contain(EntityType.WHALER); // Is there a whaler ?
		if (result != null) {
			Whaler result_whaler = (Whaler) result;
			result_whaler.m_life -= m_damage; // if yes, it takes damages
		}

		result = m_model.map().tile(new_pos).contain(EntityType.DESTROYER); // Is there a destroyer ?
		if (result != null) {
			Destroyer result_destroyer = (Destroyer) result;
			result_destroyer.m_life -= m_damage; // if yes, it takes damages
		}

		result = m_model.map().tile(new_pos).contain(EntityType.WHALE); // Is there a whale ?
		if (result != null) {
			Whale result_whale = (Whale) result;
			result_whale.m_life -= m_damage; // if yes, it takes damages
		}
	}

	public void pick() {
		this.pop();
	}

	public boolean isSolidUnder() {
		return false;
	}

	@Override
	public EntityType getType() {
		return EntityType.WHALE;
	}

}
