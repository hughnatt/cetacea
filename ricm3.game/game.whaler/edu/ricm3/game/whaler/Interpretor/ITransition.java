package edu.ricm3.game.whaler.Interpretor;

import edu.ricm3.game.whaler.Entities.Mobile_Entity;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;

public class ITransition {

	IAction m_action;
	IState m_destination;
	ICondition m_condition;

	/**
	 * 
	 * @param IAction action
	 * @param IState destination
	 * @param ICondition condition
	 */
	public ITransition(IAction action, IState destination, ICondition condition) {
		this.m_action = action;
		this.m_destination = destination;
		this.m_condition = condition;
	}
	
	public boolean eval() {
		return m_condition.eval();
	}
	
	public void step(Mobile_Entity e) throws Exception {
		m_action.step(e);
		e.m_current = m_destination; 
	}
}