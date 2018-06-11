package edu.ricm3.game.whaler.Interpretor;

import java.util.Iterator;
import java.util.List;

import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Entities.Mobile_Entity;
import edu.ricm3.game.whaler.Game_exception.Automata_Exception;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public class IAutomata {

	List<IBehaviour> m_behaviours;
	String m_name;
	IState m_initial;

	/**
	 * 
	 * @param name
	 * @param trans
	 * @param initial
	 */
	public IAutomata(String name, List<IBehaviour> behaviours, IState initial) {
		this.m_behaviours = behaviours;
		this.m_name = name;
		this.m_initial = initial;
	}

	/**
	 * Automata Step
	 * 
	 * @param e
	 * @throws Automata_Exception
	 * @throws Game_Exception
	 */

	public void step(Model model, Mobile_Entity e) throws Exception, Automata_Exception, Game_exception {

		Iterator<IBehaviour> iter = m_behaviours.iterator();

		// Si l'état courant de l'entité n'est pas fixé
		// On part de l'état initial
		if (e.m_current == null) {
			e.m_current = m_initial;
		}

		// On rechercher le comportement associé à l'état courant
		IBehaviour currentBehaviour = null;
		while (iter.hasNext()) {
			IBehaviour b = iter.next();
			if (b.m_source.toString().equals(e.m_current.toString())) {
				currentBehaviour = b;
				break;
			}
		}

		// Si jamais on ne trouve pas l'état indiqué
		if (currentBehaviour == null) {
			throw new Automata_Exception("Missing State\n");
		}


		currentBehaviour.step(model, e);		
		
	}

	public String toString() {
		return m_name;
	}

}