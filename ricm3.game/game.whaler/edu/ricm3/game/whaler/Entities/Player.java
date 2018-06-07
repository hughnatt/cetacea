package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;

public final class Player extends Mobile_Entity {

	
	BufferedImage m_playerNorth;
	BufferedImage m_playerSouth;
	BufferedImage m_playerEast;
	BufferedImage m_playerWest;
	
	/**
	 * Entité Joueur (1 par map)
	 * @param pos Position initiale du joueur
	 * @param sprite Sprite du Joueur (4 images, h:32, w:128)
	 * @param model Modèle interne
	 */
	public Player(Location pos, BufferedImage sprite, Model model, Direction dir) {
		super(pos, true, sprite, model, dir);
		loadSprites();
		switch(dir) {
		case EAST:
			m_sprite = m_playerEast;
			break;
		case WEST:
			m_sprite = m_playerWest;
			break;
		case NORTH:
			m_sprite = m_playerNorth;
			break;
		case SOUTH:
			m_sprite = m_playerSouth;
			break;
		}
	}
	
	
	/*
	 * 
	 */
	public void loadSprites() {
		m_playerSouth = m_sprite.getSubimage(0, 0, 32, 32);
		m_playerWest = m_sprite.getSubimage(0, 32, 32, 32);
		m_playerEast = m_sprite.getSubimage(0, 64, 32, 32);
		m_playerNorth = m_sprite.getSubimage(0, 96, 32, 32);
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
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

}
