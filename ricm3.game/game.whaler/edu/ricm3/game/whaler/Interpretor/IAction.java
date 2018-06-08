package edu.ricm3.game.whaler.Interpretor;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Map;
import edu.ricm3.game.whaler.Entities.Mobile_Entity;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;

public abstract class IAction{


	public IAction() {
	}

	abstract void step(Mobile_Entity e) throws Map_exception, Tile_exception;

	
	public class ITurn extends IAction {

		Direction m_dir;
		
		public ITurn(Direction dir) {
			m_dir = dir;
		}

		void step(Mobile_Entity e) {
		}
	}

	public class IMove extends IAction {

		Direction m_dir;
		public IMove(Direction dir) {
			m_dir = dir;
		}

		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
			switch (m_dir) {
			case EAST:
				e.right();
				break;
			case WEST:
				e.left();
				break;
			case SOUTH:
				e.down();
				break;
			case NORTH:
				e.up();
				break;
			}
		}
	}
	
	
	
	public class IJump extends IAction {
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}	
	}
	
	public class IWizz extends IAction {
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
			e.wizz();
		}
		
	}
	public class IPop extends IAction {

		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
			e.pop();
		}
		
	}
	
	
	public class IHit extends IAction {
		
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
			e.hit();
		}
		
	}
	
	public class IProtect extends IAction {

		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public class IPick extends IAction {

		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public class IThrow extends IAction {

		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public class IStore extends IAction {

		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public class IGet extends IAction {

		void step(Mobile_Entity e) throws Map_exception, Tile_exception {	
		}
		
	}
	
	public class IPower extends IAction {

		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public class IKamikaze extends IAction {

		@Override
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public class IOr extends IAction {
		IAction m_a;
		IAction m_b;

		public IOr(IAction a, IAction b) {
			m_a = a;
			m_b = b;
		}

		@Override
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
			int r = e.m_model.rand.nextInt(2);
			if (r == 0) {
				m_a.step(e);
			} else {
				m_b.step(e);
			}
		}
	}

}

/*
 * IWhizz IPop IMove IJump ITurn IHit IProtect IPick IThrow IStore IGet IPower
 * IKamikaze
 */
