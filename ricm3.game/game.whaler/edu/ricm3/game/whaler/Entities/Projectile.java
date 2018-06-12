package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Tile;
import edu.ricm3.game.whaler.Game_exception.Game_exception;
import edu.ricm3.game.whaler.Game_exception.Map_exception;

public class Projectile extends Mobile_Entity {

	int m_remaining; // before destruction, 0 = destruction at the next move
	int m_damage; // The damage that will be done by the projectile

	private long m_speed; // speed of the projectile

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
	 * @throws Game_exception
	 */
	public Projectile(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir,
			int range, int damage) throws Game_exception {
		super(pos, false, sprite, underSprite, model, dir, range);
		m_damage = Options.PROJECTILE_DPS;
		m_speed = Options.PROJECTILE_SPD_STANDARD;

		m_automata = m_model.getAutomata(this);
		m_model.m_projectiles.add(this);

		m_lastStep = -1;
	}

	/**
	 * @param tile
	 * 
	 *            the tile where apply the projectile
	 * @return boolean
	 * 
	 *         true if the projectile hits something, else false
	 * @throws Map_exception
	 */
	private boolean hasHitSomething(int x, int y) throws Map_exception {
		Tile tile = m_model.map().tile(x, y);
		Iterator<Entity> iter = tile.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			if (e.isSolid()) {
				switch (e.getType()) {
				case PLAYER:
				case DESTROYER:
				case WHALER:
					Mobile_Entity me = (Mobile_Entity) e;
					me.m_life -= m_damage;
				default:
					return true;
				}
			}
		}

		return false;

	}

	public void destroy() throws Game_exception {
		m_model.map().tile(m_pos).remove(this);
		m_model.m_garbage.add(this);
	}

	@Override
	public void step(long now) throws Game_exception {

		if (this.m_lastStep == -1) {
			m_lastStep = now;
		}

		long elapsed = now - this.m_lastStep;


		if (elapsed > m_speed) { // the projectile position is updated according to its speed
			m_lastStep = now;
			
			if ((hasHitSomething(this.getx(), this.gety())) || (m_life <= 0)) { // if the projectile hit nothing
				this.destroy();
				return;
			}

			// Automata Transition

			m_life--;

			try {
				m_automata.step(m_model, this);
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {
		// nothing
	}

	public void pop() {
		m_speed = Options.PROJECTILE_SPD_IMPROVED;
	}

	public void wizz() {
		m_direction = m_model.rand_direction();
	}

	public void hit() {
	}
	
	public void pick() {
		this.pop();
	}

}
