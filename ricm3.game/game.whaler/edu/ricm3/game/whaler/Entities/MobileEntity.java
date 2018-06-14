package edu.ricm3.game.whaler.Entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Tile;
import edu.ricm3.game.whaler.Game_exception.Game_exception;
import edu.ricm3.game.whaler.Game_exception.Location_exception;
import edu.ricm3.game.whaler.Interpretor.IAutomata;
import edu.ricm3.game.whaler.Interpretor.IState;

public abstract class MobileEntity extends Entity {

	long m_lastStep;
	public Direction m_direction;
	public int m_life;
	// L'automate associé à l'entité mobile
	public IAutomata m_automata;
	public IState m_current; // !\ Ne pas initialiser, l'initialisation aura lieu au premier step de
								// l'automate

	/**
	 * @param pos
	 * @param solid
	 * @param sprite
	 * @param underSprite
	 * @param model
	 * @param dir
	 * 
	 */
	protected MobileEntity(Location pos, boolean solid, BufferedImage sprite, BufferedImage underSprite, Model model,
			Direction dir, int life) throws Game_exception {
		super(pos, solid, sprite, underSprite, model);
		m_life = life;
		m_lastStep = 0;
		m_direction = dir;
	}

	protected MobileEntity(Location pos, boolean solid, BufferedImage sprite, BufferedImage underSprite, Model model,
			Direction dir, int life, boolean add_back) throws Game_exception {
		super(pos, solid, sprite, underSprite, model, add_back);
		m_life = life;
		m_lastStep = 0;
		m_direction = dir;
	}

	public abstract void destroy() throws Game_exception;

	/**
	 * // Calculation of the front location
	 * 
	 * @return Location
	 * @throws Location_exception
	 */
	protected Location pos_front() throws Location_exception {
		Location front = new Location(this.m_pos);
		switch (m_direction) {
		case NORTH:
			front.up();
			break;
		case EAST:
			front.right();
			break;
		case SOUTH:
			front.down();
			break;
		case WEST:
			front.left();
			break;
		default:
			break;
		}
		return front;
	}

	// Retourne direction asbolue depuis Forward (F)
	public Direction getFDir() {
		return this.m_direction;
	}

	// Retourne direction absolue depuis Backward (B)
	public Direction getBDir() {
		return (this.m_direction.RotationToRight()).RotationToRight();
	}

	// Retourne direction asbolue depuis Right (R)
	public Direction getRDir() {
		return this.m_direction.RotationToRight();
	}

	// Retourne direction asbolue depuis Left (L)
	public Direction getLDir() {
		return this.m_direction.RotationToLeft();
	}

	/**
	 * @throws Game_exception
	 */
	public void movenorth() throws Game_exception {
		Tile nextTile = m_model.map().tile(this.m_pos.getNorth());

		move(nextTile);
	}

	/**
	 * @throws Game_exception
	 */
	public void movesouth() throws Game_exception {
		Tile nextTile = m_model.map().tile(this.m_pos.getSouth());
		move(nextTile);
	}

	/**
	 * @throws Game_exception
	 */
	public void moveeast() throws Game_exception {

		Tile nextTile = m_model.map().tile(this.m_pos.getEast());
		move(nextTile);
	}

	/**
	 * @throws Game_exception
	 */
	public void movewest() throws Game_exception {
		Tile nextTile = m_model.map().tile(this.m_pos.getWest());
		move(nextTile);
	}

	public void move(Tile nextTile) throws Game_exception {

		if (m_model.UNDER_WATER) {
			moveUnder(nextTile);
		} else {
			moveOver(nextTile);
		}

	}

	public void moveUnder(Tile nextTile) throws Game_exception {

		Tile currentTile = m_model.map().tile(this.getx(), this.gety());

		if (!nextTile.isSolidUnder()) {
			currentTile.remove(this);
			nextTile.addForeground(this);
			m_pos = nextTile.m_loc;
		}
	}

	public void moveOver(Tile nextTile) throws Game_exception {

		Tile currentTile = m_model.map().tile(this.getx(), this.gety());

		if ((!nextTile.isSolid() || this.getType() == EntityType.PROJECTILE)
				&& ((this.getType() != EntityType.OIL) || (nextTile.contain(EntityType.OIL) == null))) {
			/*
			 * It can move if : the next tile is not solid or it is a projectile or it isn't
			 * oil which go on a tile with oil
			 */
			currentTile.remove(this);
			nextTile.addForeground(this);
			m_pos = nextTile.m_loc;
		}
	}

	/**
	 * 
	 */
	public void turnright() {
		m_direction = m_direction.RotationToRight();
	}

	public void turnleft() {
		m_direction = m_direction.RotationToLeft();
	}

	public void turndown() {
		m_direction = (m_direction.RotationToRight()).RotationToRight();
	}

	// Specific Actions
	public abstract void pop() throws Game_exception;

	public abstract void wizz() throws Game_exception;

	public abstract void hit() throws Game_exception;

	public abstract void pick() throws Game_exception;

}
