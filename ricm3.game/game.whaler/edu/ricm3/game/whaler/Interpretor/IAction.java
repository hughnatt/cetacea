package edu.ricm3.game.whaler.Interpretor;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Entities.Mobile_Entity;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;

public abstract class IAction{


	abstract void step(Mobile_Entity e) throws Map_exception, Tile_exception;

	public static Direction strToDir(String str){ //TODO, création d'une méthode IString avec méthodes de conversion incluse à la place de fonctions statiques
		if (str.equals("N")) {
			return Direction.NORTH;
		} else if (str.equals("S")){
			return Direction.SOUTH;
		} else if (str.equals("E")){
			return Direction.EAST;
		} else if (str.equals("O")){
			return Direction.WEST;
		} else if (str.equals("F")){
			return Direction.FORWARD;
		} else if (str.equals("B")){
			return Direction.BACKWARD;
		} else if (str.equals("R")){
			return Direction.RIGHT;
		} else if (str.equals("L")){
			return Direction.LEFT;
		}else {
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
				default:
						e.right();
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
		}	
	}
	
	public static class IWizz extends IAction {
		

		Direction m_dir;
		
		public IWizz(String dir) {
			m_dir = strToDir(dir);
		}
		
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
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
		}
	}
	
	
	public static class IHit extends IAction {
		

		Direction m_dir;
		
		public IHit(String dir) {
			m_dir = strToDir(dir);
		}
		
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
			e.hit();
		}
		
	}
	
	public static class IProtect extends IAction {

		Direction m_dir;
		
		public IProtect(String dir) {
			m_dir = strToDir(dir);
		}
		
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public static class IPick extends IAction {

		Direction m_dir;
		
		public IPick(String dir) {
			m_dir = strToDir(dir);
		}
		
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public static class IThrow extends IAction {

		Direction m_dir;
		
		public IThrow(String dir) {
			m_dir = strToDir(dir);
		}
		
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public static class IStore extends IAction {

		public IStore() {}
		
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public static class IGet extends IAction {

		public IGet() {}
		
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {	
		}
		
	}
	
	public static class IPower extends IAction {

		public IPower() {}
		
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public static class IKamikaze extends IAction {

		public IKamikaze() {}
		
		void step(Mobile_Entity e) throws Map_exception, Tile_exception {
		}
		
	}
	
	public static class IOr extends IAction {
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
