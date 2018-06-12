package edu.ricm3.game.whaler.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Game_exception.Automata_Exception;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public class Whaler extends Mobile_Entity {

	// TODO ajouter plus de fonctionnalités en jeu sur le whaler ?

	private BufferedImage m_whalerSouth;
	private BufferedImage m_whalerWest;
	private BufferedImage m_whalerEast;
	private BufferedImage m_whalerNorth;

	private BufferedImage[] m_explode;
	private int m_explode_idx;

	int m_damage;
	boolean m_exploding;
	long m_lastAnim;


	/**
	 * @param pos
	 * @param sprite
	 * @param underSprite
	 * @param model
	 * @param dir
	 * @throws Game_exception
	 */
	public Whaler(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir)
			throws Game_exception {
		super(pos, true, sprite, underSprite, model, dir, Options.WHALER_LIFE);
		m_exploding = false;
		m_explode_idx = 0;
		m_automata = m_model.getAutomata(this);


		loadSprites();
		switch (dir) {
		case EAST:
			m_sprite = m_whalerEast;
			break;
		case WEST:
			m_sprite = m_whalerWest;
			break;
		case NORTH:
			m_sprite = m_whalerNorth;
			break;
		default:
			m_sprite = m_whalerSouth;
			break;
		}
	}

	@Override
	public void destroy() throws Game_exception {
		m_model.map().tile(m_pos).remove(this);
		m_model.m_garbage.add(this);

	}

	/*
	 * 
	 */
	public void loadSprites() {
		m_whalerSouth = m_sprite.getSubimage(0, 0, 32, 32);
		m_whalerWest = m_sprite.getSubimage(0, 32, 32, 32);
		m_whalerEast = m_sprite.getSubimage(0, 64, 32, 32);
		m_whalerNorth = m_sprite.getSubimage(0, 96, 32, 32);

		m_sprite = m_model.get_boom_sprite();

		m_explode = new BufferedImage[8];
		for (int i = 0; i < 8; i++) {
			m_explode[i] = m_sprite.getSubimage(32 * i, 0, 32, 32);
		}
	}

	@Override
	public void step(long now) throws Game_exception {
		stepAnim(now);

		long elapsed = now - m_lastStep;

		if (!m_exploding && elapsed > 1000L) {
			m_lastStep = now;

			try {
				m_automata.step(m_model, this);
			} catch (Automata_Exception e) {
				e.printStackTrace();
			} catch (Game_exception e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (m_life <= 0) {
				m_exploding = true;
			}
		}
	}

	public void stepAnim(long now) throws Game_exception {
		long elapsed = now - m_lastAnim;
		if (elapsed > 100L) {
			m_lastAnim = now;
			if (m_exploding) {
				m_explode_idx++;

				if (m_explode_idx == 8) {
					this.destroy();
				}
			}
		}
	}

	@Override
	public void paint(Graphics g, Location map_ref) {

		if (m_exploding) {

			g.drawImage(m_explode[m_explode_idx], (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
		} else {
			g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
			g.setColor(Color.RED);
			g.fillRect((m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32,
					(int) (((double) m_life / (double) (Options.WHALER_LIFE)) * 32), 2);
		}

	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {

	}

	@Override
	public void pop() {
		this.m_life++;
	}

	@Override
	public void wizz() throws Game_exception {
		Location new_pos = this.pos_front();

		Entity result = m_model.map().tile(new_pos).contain(EntityType.WHALE); // Is there a whale ?
		if (result != null) {
			Whale result_whale = (Whale) result;
			result_whale.m_life += 3; // if yes, caught gauge increases by 3
		}

	}

	public void pick() {
		this.pop();
	}

	@Override
	public void hit() throws Game_exception {
		Location new_pos = this.pos_front();

		Entity result = m_model.map().tile(new_pos).contain(EntityType.WHALE); // Is there a whale ?
		if (result != null) {
			Whale result_whale = (Whale) result;
			result_whale.m_life++; // if yes, caught gauge increases
		}

	}

}
