package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;

public class RedCoral extends Static_Entity{
	
	public RedCoral(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model) throws Map_exception {
		super(pos, false, sprite, underSprite, model);
	}

	@Override
	public void step(long now) throws Map_exception, Tile_exception {
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {
		g.drawImage(m_underSprite, (this.getx() - map_ref.x) * 32, (this.gety() - map_ref.y) * 32, 32, 32, null);		
	}

}
