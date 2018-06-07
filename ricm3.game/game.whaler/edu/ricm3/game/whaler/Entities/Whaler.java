package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;

public class Whaler extends Mobile_Entity {


	private BufferedImage m_whalerSouth;
	private BufferedImage m_whalerWest;
	private BufferedImage m_whalerEast;
	private BufferedImage m_whalerNorth;

	/**
	 * @param pos
	 * @param sprite
	 * @param model
	 * @param dir
	 */
	public Whaler(Location pos, BufferedImage sprite, Model model, Direction dir) {
		super(pos, true, sprite, model, dir);
		loadSprites();
		switch(dir) {
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

}
