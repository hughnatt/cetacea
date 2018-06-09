package edu.ricm3.game.whaler.Interpretor;


public abstract class ICondition {
	
	public abstract boolean eval();

	
	public static class ITrue extends ICondition {
		
		public ITrue(){
			
		}
		
		public boolean eval(){
			return true;
		}
	}
	
	public static class IKey extends ICondition {
		String m_key;
		
		public IKey(String key){
			m_key = key;
		}
		
		public boolean eval() {
			return true; //TODO
		}
	}

	public static class IMyDir extends ICondition {
		String m_dir;
		
		public IMyDir(String string){
			m_dir = string;
		}
		
		public boolean eval() {
			return true; //TODO
		}
	}

	public static class ICell extends ICondition  {
		String m_entity;
		String m_dir;
		
		public ICell(String entity, String dir){
			m_entity = entity;
			m_dir = dir;
		}
		
		public boolean eval() {
			return true; //TODO
		}
	}
	
	public static class IClosest extends ICondition  {
		String m_entity;
		String m_dir;
		
		public IClosest(String entity, String dir){
			m_entity = entity;
			m_dir = dir;
		}
		
		public boolean eval() {
			return true; //TODO
		}
	}
	
	public static class IGetPower extends ICondition  {
		
		public IGetPower(){
		}
		
		public boolean eval() {
			return false; //TODO
		}
	}
	
public static class IGotStuff extends ICondition  {
		
		public IGotStuff(){
		}
		
		public boolean eval() {
			return false; //TODO
		}
	}
	
	public static class IAnd extends ICondition {
		ICondition m_a;
		ICondition m_b;

		public IAnd(ICondition a, ICondition b) {
			m_a = a;
			m_b = b;
		}

		public boolean eval() {
			return (m_a.eval() && m_b.eval());
		}
	}

	public static class IOr extends ICondition {
		ICondition m_a;
		ICondition m_b;

		public IOr(ICondition a, ICondition b) {
			m_a = a;
			m_b = b;
		}

		public boolean eval() {
			return (m_a.eval() || m_b.eval());
		}
	}

	public static class INot extends ICondition {
		ICondition m_a;

		public INot(ICondition a) {
			m_a = a;
		}

		public boolean eval() {
			return !(m_a.eval());
		}
	}
	
}