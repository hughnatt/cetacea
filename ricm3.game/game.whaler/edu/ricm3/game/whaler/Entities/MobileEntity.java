package edu.ricm3.game.whaler.Entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Tile;
import edu.ricm3.game.whaler.Game_exception.Game_exception;
import edu.ricm3.game.whaler.Game_exception.Location_exception;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;
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
		super(pos, solid, sprite, underSprite, model,add_back);
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

	// Retourne direction asbolue depuis Backward (B)
	public Direction getBDir() {
		switch (this.m_direction) {
		case NORTH:
			return Direction.SOUTH;
		case SOUTH:
			return Direction.NORTH;
		case EAST:
			return Direction.WEST;
		case WEST:
			return Direction.EAST;
		default:
			System.out.println("Unknown Direction, will be interpreted as NORTH");
			return Direction.NORTH;
		}
	}

	// Retourne direction asbolue depuis Right (R)
	public Direction getRDir() {
		switch (this.m_direction) {
		case NORTH:
			return Direction.EAST;
		case SOUTH:
			return Direction.WEST;
		case EAST:
			return Direction.SOUTH;
		case WEST:
			return Direction.NORTH;
		default:
			System.out.println("Unknown Direction, will be interpreted as NORTH");
			return Direction.NORTH;
		}
	}

	// Retourne direction asbolue depuis Left (L)
	public Direction getLDir() {
		switch (this.m_direction) {
		case NORTH:
			return Direction.WEST;
		case SOUTH:
			return Direction.EAST;
		case EAST:
			return Direction.NORTH;
		case WEST:
			return Direction.SOUTH;
		default:
			System.out.println("Unknown Direction, will be interpreted as NORTH");
			return Direction.NORTH;
		}
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
			nextTile.addBackground(this);
			m_pos = nextTile.m_loc;
		}
	}

	public void moveOver(Tile nextTile) throws Game_exception {
		
		Tile currentTile = m_model.map().tile(this.getx(), this.gety());
		
		if (!nextTile.isSolid()) {
			currentTile.remove(this);
			nextTile.addBackground(this);
			m_pos = nextTile.m_loc;
		}
	}



	/**
	 * 
	 */
	public void turnright() {
		switch (m_direction) {
		case SOUTH:
			m_direction = Direction.WEST;
			break;
		case NORTH:
			m_direction = Direction.EAST;
			break;
		case WEST:
			m_direction = Direction.NORTH;
			break;
		case EAST:
			m_direction = Direction.SOUTH;
			break;
		default:
			break;
		}
	}

	public void turnleft() {
		switch (m_direction) {
		case SOUTH:
			m_direction = Direction.EAST;
			break;
		case NORTH:
			m_direction = Direction.WEST;
			break;
		case WEST:
			m_direction = Direction.SOUTH;
			break;
		case EAST:
			m_direction = Direction.NORTH;
			break;
		default:
			break;
		}
	}

	public void turndown() {
		switch (m_direction) {
		case SOUTH:
			m_direction = Direction.NORTH;
			break;
		case NORTH:
			m_direction = Direction.SOUTH;
			break;
		case WEST:
			m_direction = Direction.EAST;
			break;
		case EAST:
			m_direction = Direction.WEST;
			break;
		default:
			break;
		}
	}

	// Specific Actions
	public abstract void pop() throws Game_exception;

	public abstract void wizz() throws Game_exception;

	public abstract void hit() throws Game_exception;

	public abstract void pick() throws Game_exception;

	// TODO
	// Placebo actions (decide if Specific or Generic)
	// public abstract void jump();
	// public abstract void protect();
	// public abstract void pick();
	// public abstract void throw();
	// public abstract void store();
	// public abstract void get();
	// public abstract void power();
	// public abstract void kamikaze();

}
