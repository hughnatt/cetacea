package edu.ricm3.game.whaler.Interpretor;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Model;

public abstract class ICondition {

	public abstract boolean eval(Model model); // Il y aura besoin de rajouter (au moins) la map (voir model complet) et
	// l'entité courante

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

	/**
	 * La condition est toujours vérifiée
	 */
	public static class ITrue extends ICondition {

		public ITrue() {

		}

		public boolean eval(Model model) {
			return true;
		}
	}

	/**
	 * La Touche m_key est enfoncée m_key peut valoir : [a-z] [A-Z] [0-9] "SPACE"
	 * "ENTER" "FU" "FD" "FR" "FL"
	 */
	public static class IKey extends ICondition {
		String m_key;

		public IKey(String key) {
			m_key = key;
		}

		public boolean eval(Model model) {
			int length = m_key.length();
			char carac = m_key.charAt(0);
			int ascii = (int) carac;
			if (length>1) {
				char carac2 = m_key.charAt(1);
				if(carac=='S') {
					ascii=32;
				}
				else if(carac=='E') {
					ascii=10;
				}
				else if(carac2=='U') {
					ascii=38;
				}
				else if(carac2=='D') {
					ascii=40;
				}
				else if(carac2=='R') {
					ascii=39;
				}
				else if(carac2=='L') {
					ascii=37;
				}
			}
			return model.keyPressed[ascii];
		}
	}

	/**
	 * 
	 * L'entité courante est orientée dans la direction m_dir NB (@Tanguy) : Il
	 * faudra passer l'entité courante en argument de eval ce qui risque d'être pas
	 * mal chiant
	 */
	public static class IMyDir extends ICondition {
		Direction m_dir;

		public IMyDir(String string) {
			m_dir = strToDir(string);
		}

		public boolean eval(Model model) {
			return true;
		}
	}

	/**
	 * 
	 * La cellule dans la direction m_dir contient une entité de type m_entity
	 * m_entity peut valoir : V T A D P J G M NB: Une entité dangereux pour le
	 * joueur n'est pas dangereux pour un Destroyer "DANGER" est donc à définir en
	 * fonction de l'entité courante
	 */
	public static class ICell extends ICondition {
		String m_entity;
		Direction m_dir;

		public ICell(String entity, String dir) {
			m_entity = entity;
			m_dir = strToDir(dir);
		}

		public boolean eval(Model model) {
			return true;
		}
	}

	/**
	 * La plus proche entité de type m_entity est dans la direction m_dir NB :
	 * (@Tanguy) : Celle là elle a pas l'air facile à faire, il faudra passer la map
	 * complète en argument galère, galère, ... m_entity peut valoir : V T A D P J G
	 * M NB2 : Une entité dangereux pour le joueur n'est pas dangereux pour un
	 * Destroyer "DANGER" est donc à définir en fonction de l'entité courante
	 */
	public static class IClosest extends ICondition {
		String m_entity;
		Direction m_dir;

		public IClosest(String entity, String dir) {
			m_entity = entity;
			m_dir = strToDir(dir);
		}

		public boolean eval(Model model) {
			return true;
		}
	}

	/**
	 * Reste t-il de l'énergie à l'entité NB : Regarder pour faire un truc relatif à
	 * la vie
	 */
	public static class IGetPower extends ICondition {

		public IGetPower() {
		}

		public boolean eval(Model model) {
			return true;
		}
	}

	/**
	 * Reste t-il des choses dans le store ? NB (@Tanguy) : Celle là on a qu'à la
	 * laisser à FAUX tout le temps
	 */
	public static class IGotStuff extends ICondition {

		public IGotStuff() {
		}

		public boolean eval(Model model) {
			return true;
		}
	}

	/**
	 * 
	 * Conjonction : Condition m_a & Condition m_b
	 *
	 */
	public static class IAnd extends ICondition {
		ICondition m_a;
		ICondition m_b;

		public IAnd(ICondition a, ICondition b) {
			m_a = a;
			m_b = b;
		}

		public boolean eval(Model model) {
			return (m_a.eval(model) && m_b.eval(model));
		}
	}

	/**
	 * 
	 * Disjonction : Condition m_a || Condition m_b
	 *
	 */
	public static class IOr extends ICondition {
		ICondition m_a;
		ICondition m_b;

		public IOr(ICondition a, ICondition b) {
			m_a = a;
			m_b = b;
		}

		public boolean eval(Model model) {
			return (m_a.eval(model) || m_b.eval(model));
		}
	}

	/**
	 * 
	 * Negation : not(Condition m_a)
	 *
	 */
	public static class INot extends ICondition {
		ICondition m_a;

		public INot(ICondition a) {
			m_a = a;
		}

		public boolean eval(Model model) {
			return !(m_a.eval(model));
		}
	}

}