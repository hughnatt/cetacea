package edu.ricm3.game.whaler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class Player extends Mobile_Entity {

	public static boolean UNDER_WATER = false;

	/**
	 * Entité Joueur (1 par map)
	 * @param m_pos Position initiale du joueur
	 * @param m_sprite Sprite du Joueur (4 images, h:32, w:128)
	 * @param m_model Modèle interne
	 */
	protected Player(Location m_pos, BufferedImage m_sprite, Model m_model) {
		super(m_pos, true, m_sprite, m_model);
	}
	
	
	@Override
	public void step(long now) {

	}

	
	
	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}


}
