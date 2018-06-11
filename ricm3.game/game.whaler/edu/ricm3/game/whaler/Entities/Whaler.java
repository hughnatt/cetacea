package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public class Whaler extends Mobile_Entity {

	private BufferedImage m_whalerSouth;
	private BufferedImage m_whalerWest;
	private BufferedImage m_whalerEast;
	private BufferedImage m_whalerNorth;

	int m_life;
	int m_damage;

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
		super(pos, true, sprite, underSprite, model, dir);

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
		case SOUTH:
			m_sprite = m_whalerSouth;
			break;
		}
	}

	/*
	 * 
	 */
	public void loadSprites() {
		m_whalerSouth = m_sprite.getSubimage(0, 0, 32, 32);
		m_whalerWest = m_sprite.getSubimage(0, 32, 32, 32);
		m_whalerEast = m_sprite.getSubimage(0, 64, 32, 32);
		m_whalerNorth = m_sprite.getSubimage(0, 96, 32, 32);
	}

	@Override
	public void step(long now) {

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
		this.m_life++;
	}

	@Override
	public void wizz() throws Game_exception {
		Location new_pos = this.pos_front();

		Entity result = m_model.map().tile(new_pos).contain(Whale.class); // Is there a whale ?
		if (result != null) {
			Whale result_whale = (Whale) result;
			result_whale.m_capture += 3; // if yes, caught gauge increases by 3
		}

	}

	@Override
	public void hit() throws Game_exception {
		Location new_pos = this.pos_front();

		Entity result = m_model.map().tile(new_pos).contain(Whale.class); // Is there a whale ?
		if (result != null) {
			Whale result_whale = (Whale) result;
			result_whale.m_capture++; // if yes, caught gauge increases
		}

	}

}
