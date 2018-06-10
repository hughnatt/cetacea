package edu.ricm3.game.parser;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import edu.ricm3.game.whaler.Game_exception.Automata_Exception;
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

	// AST as tree

	public String dot_id() {
		return Dot.node_id(this.id);
	}

	public String as_tree_son_of(Ast father) {
		return Dot.edge(father.dot_id(), this.dot_id()) + this.as_dot_tree();
	}

	public String as_dot_tree() {
		return this.as_tree_node() + this.tree_edges();
	}

	public String as_tree_node() {
		return Dot.declare_node(this.dot_id(), this.kind, "");
	}

	public String tree_edges() {
		return "undefined: tree_edges";
	}

	// AST as automata in .dot forma

	public String as_dot_automata() {
		return "undefined: as_dot_automata";
	}

	// AST as active automata (interpreter of transitions)

	public Object make() throws Automata_Exception {
		return null;
	}

	public static class Terminal extends Ast {
		String value;

		Terminal(String string) {
			this.kind = "Terminal";
			this.value = string;
		}

		public String toString() {
			return value;
		}
		
		public String make() {
			return value;
		}

		public String tree_edges() {
			String value_id = Dot.node_id(-this.id);
			return Dot.declare_node(value_id, value, "shape=none, fontsize=10, fontcolor=blue")
					+ Dot.edge(this.dot_id(), value_id);
		}

	}

	// Value = Constant U Variable

	public static abstract class Value extends Ast {
		public abstract String make();
	}

	public static class Constant extends Value {

		Terminal value;

		Constant(String string) {
			this.kind = "Constant";
			this.value = new Terminal(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String make() {
			return value.toString();
		}

	}

	public static class Variable extends Value {

		Terminal name;

		Variable(String string) {
			this.kind = "Variable";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}

		public String make() {
			return name.toString();
		}
	}

	// Parameter = Underscore U Key U Direction U Entity
	// Parameter are not Expression (no recursion)

	public static abstract class Parameter extends Ast {
		public abstract String make();
	}

	public static class Underscore extends Parameter {
		Underscore() {
			this.kind = "Any";
		}

		public String tree_edges() {
			return "";
		}

		public String make() {
			return "_";
		}
	}

	public static class Key extends Parameter {

		Constant value;

		Key(String string) {
			this.kind = "Key";
			this.value = new Constant(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String make() {
			return value.make();
		}
	}

	public static class Direction extends Parameter {

		Value value;

		Direction(Value value) {
			this.kind = "Direction";
			this.value = value;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String make() {
			return value.make();
		}
	}

	public static class Entity extends Parameter {

		Value value;

		Entity(Value expression) {
			this.kind = "Entity";
			this.value = expression;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		@Override
		public String make() {
			return value.make();
		}
	}

	// Expression = UnaryOp Expression U Expression BinaryOp Expression U
	// FunCall(Parameters)

	public static abstract class Expression extends Ast {
		public abstract ICondition makeCondition() throws Automata_Exception;

		public abstract IAction makeAction() throws Automata_Exception;
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

		public ICondition makeCondition() throws Automata_Exception {
			if (operator.toString().equals("!")) {
				return new INot(operand.makeCondition());
			} else {
				throw new Automata_Exception("Unkown Operator" + operator.toString() + "\n");
			}
		}

		public IAction makeAction() throws Automata_Exception {
			throw new Automata_Exception("Opérateur Unaire toujours incorrect sur Action");
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

		public ICondition makeCondition() throws Automata_Exception {
			if (operator.make().equals("\\")) {
				return new ICondition.IOr(left_operand.makeCondition(), right_operand.makeCondition());

			} else if (operator.make().equals("&")) {
				return new IAnd((ICondition) left_operand.make(), (ICondition) right_operand.make());
			} else {
				throw new Automata_Exception("Unkown Operator : " + operator.toString() + "\n");
			}

		}

		public IAction makeAction() throws Automata_Exception {

			if (operator.make().equals("\\")) {
				return new IAction.IOr(left_operand.makeAction(), right_operand.makeAction());

			} else if (operator.make().equals("&")) {
				throw new Automata_Exception("Can't execute 2 actions at the same time"); 
			} else {
				throw new Automata_Exception("Unkown Operator : " + operator.toString() + "\n");
			}

		}

	}

	public static class FunCall extends Expression {

		Terminal name;
		List<Parameter> parameters;

		FunCall(String name, List<Parameter> parameters) {
			this.kind = "FunCall";
			this.name = new Terminal(name);
			this.parameters = parameters;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			ListIterator<Parameter> Iter = this.parameters.listIterator();
			while (Iter.hasNext()) {
				Parameter parameter = Iter.next();
				output += parameter.as_tree_son_of(this);
			}
			return output;
		}

		public ICondition makeCondition() throws Automata_Exception {

			/*
			 * CONDITIONS
			 */
			if (name.toString().equals("True")) {

				assert (parameters.size() == 0);
				return new ITrue();

			} else if (name.make().equals("Key")) {

				assert (parameters.size() == 1);
				return new IKey(parameters.get(0).make());

			} else if (name.make().equals("MyDir")) {

				assert (parameters.size() == 1);
				return new IMyDir(parameters.get(0).make());

			} else if (name.make().equals("Cell")) {

				assert (parameters.size() == 2);
				return new ICell(parameters.get(0).make(), parameters.get(1).make());

			} else if (name.make().equals("Closest")) {

				assert (parameters.size() == 2);
				return new IClosest(parameters.get(0).make(), parameters.get(1).make());

			} else if (name.make().equals("GetPower")) {
				
				assert(parameters.size() == 0);
				return new IGetPower();
				
			} else if (name.make().equals("GotStuff")) {
				
				assert(parameters.size() == 0);
				return new IGotStuff();
				
			} else {
				
				throw new Automata_Exception("Unknown Condition : " + name.make() + "\n");
			}
		}

		public IAction makeAction() throws Automata_Exception {

			/*
			 * ACTIONS
			 */
			if (name.toString().equals("Move")) {
				
				assert(parameters.size() == 1);
				return new IMove(parameters.get(0).make());
				
			} else if (name.make().equals("Jump")) {
				
				assert(parameters.size() == 1);
				return new IJump(parameters.get(0).make());
				
			} else if (name.make().equals("Wizz")) {
				
				assert(parameters.size() == 1);
				return new IWizz(parameters.get(0).make());
				
			} else if (name.make().equals("Pop")) {
				
				assert(parameters.size() == 1);
				return new IPop(parameters.get(0).make());
				
			} else if (name.make().equals("Turn")) {
				
				assert(parameters.size() == 1);
				return new ITurn(parameters.get(0).make());
				
			} else if (name.make().equals("Hit")) {
				
				assert(parameters.size() == 1);
				return new IHit(parameters.get(0).make());
				
			} else if (name.make().equals("Protect")) {
				
				assert(parameters.size() == 1);
				return new IProtect(parameters.get(0).make());
				
			} else if (name.make().equals("Pick")) {
				
				assert(parameters.size() == 1);
				return new IPick(parameters.get(0).make());
				
			} else if (name.make().equals("Throw")) {
				
				assert(parameters.size() == 1);
				return new IThrow(parameters.get(0).make());
				
			} else if (name.make().equals("Store")) {
				
				assert(parameters.size() == 0);
				return new IStore();
				
			} else if (name.make().equals("Get")) {
				
				assert(parameters.size() == 0);
				return new IGet();
				
			} else if (name.make().equals("Power")) {
				
				assert(parameters.size() == 0);
				return new IPower();
				
			} else if (name.make().equals("Kamikaze")) {
				
				assert(parameters.size() == 0);
				return new IKamikaze();
				
			} else {
				
				throw new Automata_Exception("Unknown Action : "+name.make()+"\n");
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

		public ICondition make() throws Automata_Exception {
			return expression.makeCondition();
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

		public IAction make() throws Automata_Exception {
			return expression.makeAction();
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

		public String dot_id(Automaton automaton) {
			return Dot.name(automaton.id + "." + name.toString());
		}

		public String as_node_of(Automaton automaton) {
			return this.dot_id(automaton) + Dot.node_label(name.toString(), "shape=circle, fontsize=4");
		}

		public IState make() {
			return new IState(name.toString());
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

		public IAutomata[] make() throws Automata_Exception {

			IAutomata[] list = new IAutomata[automata.size()];
			int i = 0;

			Iterator<Automaton> iter = automata.iterator();
			while (iter.hasNext()) {
				Automaton a = iter.next();

				list[i++] = a.make();
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

		public IAutomata make() throws Automata_Exception {
			List<IBehaviour> l = new LinkedList<IBehaviour>();

			Iterator<Behaviour> iter = behaviours.iterator();
			while (iter.hasNext()) {
				Behaviour b = iter.next();
				l.add(b.make());
			}

			return new IAutomata(name.toString(), l, entry.make());
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

		public IBehaviour make() throws Automata_Exception {

			List<ITransition> l = new LinkedList<ITransition>();
			Iterator<Transition> iter = transitions.iterator();
			while (iter.hasNext()) {
				Transition t = iter.next();
				l.add(t.make());
			}

			return new IBehaviour(l, source.make());
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

		public ITransition make() throws Automata_Exception {
			return new ITransition(action.make(), target.make(), condition.make());
		}
	}
}
