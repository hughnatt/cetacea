package edu.ricm3.game.parser;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import edu.ricm3.game.whaler.Interpretor.*;
import edu.ricm3.game.whaler.Interpretor.IAction.*;
import edu.ricm3.game.whaler.Interpretor.ICondition.*;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, june 2018
 *
 * Constructors of the Abstract Syntax Tree of Game Automata
 */

public class Ast {

	/* All this is only for the graphical .dot output of the Abstract Syntax Tree */
	public String kind; /* the name of the non-terminal node */
	public int id = Id.fresh(); /* its unique id as a graph node */

	public String tree_edges() {
		return "undefined";
	}

	public String as_tree_son_of(Ast father) {
		return Dot.edge(father.id, this.id) + as_dot_tree();
	}

	public String as_tree_node() {
		return Dot.non_terminal_node(this.id, this.kind);
	}

	public String as_dot_tree() {
		return this.as_tree_node() + this.tree_edges();
	}

	public String as_dot_automata() {
		return "undefined";
	}

	// public Object make() {
	// return null; // TODO à définir dans la plupart des classes internes
	// ci-dessous.
	// }

	public static abstract class Expression extends Ast {

		public abstract Object make();
	}

	public static class Terminal extends Ast {
		String value;

		Terminal(String string) {
			this.value = string;
		}

		public String as_tree_son_of(Ast father) {
			return Dot.terminal_edge(father.id, value);
		}

		public String make() {
			return value;
		}

	}

	public static class Constant extends Expression {

		Terminal value;

		Constant(String string) {
			this.kind = "Constant";
			this.value = new Terminal(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public Object make() {
			return value.make();
		}

	}

	public static class Variable extends Expression {

		Terminal name;

		Variable(String string) {
			this.kind = "Variable";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}

		public Object make() {
			return name.make();
		}
	}

	public static class Direction extends Expression {

		Expression value;

		Direction(Expression expression) {
			this.kind = "Direction";
			this.value = expression;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public Object make() {
			String s = (String) value.make();
			if (s.equals("E")) {
				
			} else if (s.equals("N")) {
				
			} else if (s.equals("S")) {
				
			} else if (s.equals("O")) {
				
			} else if (s.equals("F")) {
				
			} else if (s.equals("B")) {
				
			} else if (s.equals("L")) {
				
			} else {
				
			}
		}
	}

	public static class Entity extends Expression {

		Expression value;

		Entity(Expression expression) {
			this.kind = "Entity";
			this.value = expression;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		@Override
		public Object make() {
			return value.make();
		}
	}

	public static class UnaryOp extends Expression {

		Terminal operator;
		Expression operand;

		UnaryOp(String operator, Expression operand) {
			this.kind = "UnaryOp";
			this.operator = new Terminal(operator);
			this.operand = operand;
		}

		public String tree_edges() {
			return operator.as_tree_son_of(this) + operand.as_tree_son_of(this);
		}

		@Override
		public Object make() {
			return new INot(null);
		}
	}

	public static class BinaryOp extends Expression {

		Terminal operator;
		Expression left_operand;
		Expression right_operand;

		BinaryOp(Expression l, String operator, Expression r) {
			this.kind = "BinaryOp";
			this.operator = new Terminal(operator);
			this.left_operand = l;
			this.right_operand = r;
		}

		public String tree_edges() {
			return left_operand.as_tree_son_of(this) + operator.as_tree_son_of(this)
					+ right_operand.as_tree_son_of(this);
		}

		public Object make() {

			if (operator.make().equals("\\")) {
				return new IOr((ICondition) left_operand.make(), (ICondition) right_operand.make());

			} else if (operator.make().equals("&")) {
				return new IAnd((ICondition) left_operand.make(), (ICondition) right_operand.make());
			} 
			return null;

		}
	}

	public static class FunCall extends Expression {

		Terminal name;
		List<Expression> parameters;

		FunCall(String name, List<Expression> parameters) {
			this.kind = "FunCall";
			this.name = new Terminal(name);
			this.parameters = parameters;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			ListIterator<Expression> Iter = this.parameters.listIterator();
			while (Iter.hasNext()) {
				Expression expression = Iter.next();
				output += expression.as_tree_son_of(this);
			}
			return output;
		}

		public Object make() {

			Iterator<Expression> iter = parameters.iterator();
			/*
			 * CONDITIONS
			 */
			if (name.make().equals("True")) {
				return new ITrue();
			} else if (name.make().equals("Key")) {
				
				return new IKey((String) iter.next().make());
			} else if (name.make().equals("MyDir")) {

				return new IMyDir();
			} else if (name.make().equals("Cell")) {

				return new ICell();
			} else if (name.make().equals("Closest")) {

				return new IClosest();
			} else if (name.make().equals("GetPower")) {

				return new IGetPower();
			}
			
			/*
			 * ACTIONS
			 */
			else if (name.make().equals("Move")) {
				return new IMove();
			} else if (name.make().equals("Jump")) {
				return new IJump();
			} else if (name.make().equals("Wizz")) {
				return new IWizz();
			} else if (name.make().equals("Pop")) {
				return new IPop();
			} else if (name.make().equals("Turn")) {
				return new ITurn();
			} else if (name.make().equals("Hit")) {
				return new IHit();
			} else if (name.make().equals("Protect")) {
				return new IProtect();
			} else if (name.make().equals("Pick")) {
				return new IPick();
			} else if (name.make().equals("Throw")) {
				return new IThrow();
			} else if (name.make().equals("Store")) {
				return new IStore();
			} else if (name.make().equals("Get")) {
				return new IGet();
			} else if (name.make().equals("Power")) {
				return new IPower();
			} else if (name.make().equals("Kamikaze")) {
				return new IKamikaze();
			}
		}
	}

	public static class Condition extends Ast {

		Expression expression;

		Condition(Expression expression) {
			this.kind = "Condition";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}

		public ICondition make() {
			return (ICondition) expression.make();
		}
	}

	public static class Action extends Ast {

		Expression expression;

		Action(Expression expression) {
			this.kind = "Action";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}

		public IAction make() {
			return (IAction) expression.make();
		}
	}

	public static class State extends Ast {

		Terminal name;

		State(String string) {
			this.kind = "State";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}

		public IState make() {
			return new IState(name.make());
		}
	}

	public static class AI_Definitions extends Ast {

		List<Automaton> automata;

		AI_Definitions(List<Automaton> list) {
			this.kind = "AI_Definitions";
			this.automata = list;
		}

		public String tree_edges() {
			String output = new String();
			ListIterator<Automaton> Iter = this.automata.listIterator();
			while (Iter.hasNext()) {
				Automaton automaton = Iter.next();
				output += automaton.as_tree_son_of(this);
			}
			return output;
		}

		public String as_dot_tree() {
			return Dot.graph("AST", this.as_tree_node() + this.tree_edges());
		}

		public String as_dot_automata() {
			return Dot.graph("Automata", this.as_tree_node());
		}

		public List<IAutomata> make() {

			LinkedList<IAutomata> list = new LinkedList<IAutomata>();

			Iterator<Automaton> iter = automata.iterator();
			while (iter.hasNext()) {
				Automaton a = iter.next();

				list.add(a.make());
			}

			return list;
		}
	}

	public static class Automaton extends Ast {

		Terminal name;
		State entry;
		List<Behaviour> behaviours;

		Automaton(String name, State entry, List<Behaviour> behaviours) {
			this.kind = "Automaton";
			this.name = new Terminal(name);
			this.entry = entry;
			this.behaviours = behaviours;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			output += entry.as_tree_son_of(this);
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			while (Iter.hasNext()) {
				Behaviour behaviour = Iter.next();
				output += behaviour.as_tree_son_of(this);
			}
			return output;
		}

		public IAutomata make() {
			List<IBehaviour> l = new LinkedList<IBehaviour>();

			Iterator<Behaviour> iter = behaviours.iterator();
			while (iter.hasNext()) {
				Behaviour b = iter.next();
				l.add(b.make());
			}

			return new IAutomata(name.make(), l, entry.make());
		}
	}

	public static class Behaviour extends Ast {

		State source;
		List<Transition> transitions;

		Behaviour(State state, List<Transition> transitions) {
			this.kind = "Behaviour";
			this.source = state;
			this.transitions = transitions;
		}

		public IBehaviour make() {

			List<ITransition> l = new LinkedList<ITransition>();
			Iterator<Transition> iter = transitions.iterator();
			while (iter.hasNext()) {
				Transition t = iter.next();
				l.add(t.make());
			}

			return new IBehaviour(l, source.make());
		}

		public String tree_edges() {
			String output = new String();
			output += source.as_tree_son_of(this);
			ListIterator<Transition> Iter = this.transitions.listIterator();
			while (Iter.hasNext()) {
				Transition transition = Iter.next();
				output += transition.as_tree_son_of(this);
			}
			return output;
		}
	}

	public static class Transition extends Ast {

		Condition condition;
		Action action;
		State target;

		Transition(Condition condition, Action action, State target) {
			this.kind = "Transition";
			this.condition = condition;
			this.action = action;
			this.target = target;
		}

		public String tree_edges() {
			return condition.as_tree_son_of(this) + action.as_tree_son_of(this) + target.as_tree_son_of(this);
		}

		public ITransition make() {
			return new ITransition(action.make(), target.make(), condition.make());
		}
	}
}
