package edu.ricm3.game.whaler.Interpretor;

import java.util.Iterator;
import java.util.List;

import edu.ricm3.game.whaler.Entities.Mobile_Entity;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public class IBehaviour {
	List<ITransition> m_transitions;
	IState m_source;

	/**
	 * 
	 * @param transitions
	 * @param source
	 */
	public IBehaviour(List<ITransition> transitions, IState source) {
		m_transitions = transitions;
		m_source = source;
	}

	public void step(Mobile_Entity e) throws Game_exception {
		Iterator<ITransition> iter = m_transitions.iterator();
		while (iter.hasNext()) {
			ITransition t = iter.next();
			if (t.eval() == true) {
				t.step(e);
			}
		}
	}

}
