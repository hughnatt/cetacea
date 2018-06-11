package edu.ricm3.game.whaler.Interpretor;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Entities.Mobile_Entity;
import edu.ricm3.game.whaler.Game_exception.Location_exception;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;

public abstract class IAction {

	abstract void step(Mobile_Entity e) throws Exception;

	public static Direction strToDir(String str) { // TODO, création d'une méthode IString avec méthodes de conversion
													// incluse à la place de fonctions statiques
		if (str.equals("N")) {
			return Direction.NORTH;
		} else if (str.equals("S")) {
			return Direction.SOUTH;
		} else if (str.equals("E")) {
			return Direction.EAST;
		} else if (str.equals("O")) {
			return Direction.WEST;
		} else if (str.equals("F")) {
			return Direction.FORWARD;
		} else if (str.equals("B")) {
			return Direction.BACKWARD;
		} else if (str.equals("R")) {
			return Direction.RIGHT;
		} else if (str.equals("L")) {
			return Direction.LEFT;
		} else {
			System.out.println("Unknown Direction, will be interpreted as FORWARD");
			return Direction.FORWARD;
		}
	}

	public static class IMove extends IAction {

		Direction m_dir;

		public IMove(String dir) {
			m_dir = strToDir(dir);
		}

		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
			switch (m_dir) {
			case NORTH:
				e.movenorth();
				break;
			case SOUTH:
				e.movesouth();
				break;
			case EAST:
				e.moveeast();
				break;
			case WEST:
				e.movewest();
				break;
			case FORWARD:
				switch (e.m_direction) {
				case EAST:
					e.moveeast();
					break;
				case WEST:
					e.movewest();
					break;
				case NORTH:
					e.movenorth();
					break;
				case SOUTH:
					e.movesouth();
					break;
				default:
					break;
				}
				break;
			case BACKWARD:
				switch (e.m_direction) {
				case EAST:
					e.movewest();
					break;
				case WEST:
					e.moveeast();
					break;
				case NORTH:
					e.movesouth();
					break;
				case SOUTH:
					e.movenorth();
					break;
				default:
					break;
				}
				break;
			case RIGHT:
				switch (e.m_direction) {
				case EAST:
					e.movesouth();
					break;
				case WEST:
					e.movenorth();
					break;
				case NORTH:
					e.moveeast();
					break;
				case SOUTH:
					e.movewest();
					break;
				default:
					break;
				}
				break;
			case LEFT:
				switch (e.m_direction) {
				case EAST:
					e.movenorth();
					break;
				case WEST:
					e.movesouth();
					break;
				case NORTH:
					e.movewest();
					break;
				case SOUTH:
					e.moveeast();
					break;
				default:
					break;
				}
				break;
			}
		}
	}

	public static class IJump extends IAction {

		Direction m_dir;

		public IJump(String dir) {
			m_dir = strToDir(dir);
		}

		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
			//TODO
		}
	}

	public static class IWizz extends IAction {

		Direction m_dir;

		public IWizz(String dir) {
			m_dir = strToDir(dir);
		}

		void step(Mobile_Entity e) throws Exception {
			e.wizz();
		}

	}

	public static class IPop extends IAction {

		Direction m_dir;

		public IPop(String dir) {
			m_dir = strToDir(dir);
		}

		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
			e.pop();
		}
	}

	public static class ITurn extends IAction {

		Direction m_dir;

		public ITurn(String dir) {
			m_dir = strToDir(dir);
		}

		void step(Mobile_Entity e) {
			switch (m_dir) {
			case RIGHT:
				e.turnright();
			case BACKWARD:
				e.turnright();
				e.turnright();
			case LEFT:
				e.turnright();
				e.turnright();
				e.turnright();
			default:
				break;
			}
		}
	}

	public static class IHit extends IAction {

		Direction m_dir;

		public IHit(String dir) {
			m_dir = strToDir(dir);
		}

		void step(Mobile_Entity e) throws Exception {
			e.hit();
		}

	}

	public static class IProtect extends IAction {

		Direction m_dir;

		public IProtect(String dir) {
			m_dir = strToDir(dir);
		}

		void step(Mobile_Entity e) throws Exception {
			//TODO
		}

	}

	public static class IPick extends IAction {

		Direction m_dir;

		public IPick(String dir) {
			m_dir = strToDir(dir);
		}

		void step(Mobile_Entity e) throws Exception {
			//TODO
		}

	}

	public static class IThrow extends IAction {

		Direction m_dir;

		public IThrow(String dir) {
			m_dir = strToDir(dir);
		}

		void step(Mobile_Entity e) throws Exception {
			//TODO
		}

	}

	public static class IStore extends IAction {

		public IStore() {
		}

		void step(Mobile_Entity e) throws Exception {
			//TODO
		}

	}

	public static class IGet extends IAction {

		public IGet() {
		}

		void step(Mobile_Entity e) throws Exception {
			//TODO
		}

	}

	public static class IPower extends IAction {

		public IPower() {
		}

		void step(Mobile_Entity e) throws Exception{
			//TODO
		}

	}

	public static class IKamikaze extends IAction {

		public IKamikaze() {
		}

		void step(Mobile_Entity e) throws Exception {
			//TODO
		}

	}

	public static class IOr extends IAction {
		IAction m_a;
		IAction m_b;

		public IOr(IAction a, IAction b) {
			m_a = a;
			m_b = b;
		}


		void step(Mobile_Entity e) throws Exception {
			int r = e.m_model.rand.nextInt(2);
			if (r == 0) {
				m_a.step(e);
			} else {
				m_b.step(e);
			}
		}
	}

}
