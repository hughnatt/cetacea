package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Game_exception.Location_exception;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;

public final class Whale extends Mobile_Entity {

	int m_capture; // Catch gauge
	int m_damage; // DPS to the hit

	/**
	 * @param pos
	 * @param sprite
	 * @param underSprite
	 * @param model
	 * @param dir
	 * @throws Map_exception
	 */
	public Whale(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir)
			throws Map_exception {
		super(pos, true, sprite, underSprite, model, dir);
		m_capture = Options.WHALE_CAPTURE_INIT;
		m_damage = Options.WHALE_DPS;
	}

	@Override
	public void step(long now) {
		// TODO à faire
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {

	}

	@Override
	public void pop() {
		// TODO
	}

	@Override
	public void wizz() throws Map_exception, Tile_exception, Location_exception {
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
		this.m_pos = new Location(this.getx() + transl_x, this.gety() + transl_y); // TODO valide ? pas de fuite mémoire
																					// ou autre méthode ?
		m_model.map().tile(this.getx(), this.gety()).addForeground(this);
	}

	@Override
	public void hit() throws Map_exception {
		Location new_pos = this.pos_front();
		Entity result = m_model.map().tile(new_pos).contain(Player.class); // Is there a player ?
		if (result != null) {
			Player result_player = (Player) result;
			result_player.m_life -= m_damage; // if yes, it takes damages
		}
	}

}
