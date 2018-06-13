package edu.ricm3.game.whaler.Entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public abstract class StaticEntity extends Entity {

	protected StaticEntity(Location pos, boolean solid, BufferedImage sprite, BufferedImage underSprite, Model model)
			throws Game_exception {
		super(pos, solid, sprite, underSprite, model);
	}

}
