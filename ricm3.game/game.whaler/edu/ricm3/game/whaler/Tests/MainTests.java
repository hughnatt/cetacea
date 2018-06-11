package edu.ricm3.game.whaler.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import edu.ricm3.game.parser.*;
import edu.ricm3.game.parser.Ast.AI_Definitions;
import edu.ricm3.game.whaler.*;
import edu.ricm3.game.whaler.Entities.*;
import edu.ricm3.game.whaler.Interpretor.*;

class MainTests {

	@Test
	void test() throws Exception {
		long start = System.currentTimeMillis();
		
		
		
		String input = "Mover(Init){ * (Init): True ? Move(E) :(Init) }";
		
		AutomataParser.from_string(input);
		
		IAutomata[] autos =  ((AI_Definitions) ast).make();
		
		
		Player p = new Player(new Location(10,10), null, null, m, Direction.EAST, autos[0]);
		
		p.step(System.currentTimeMillis() - start);
		
		assert(p.getx() == 11);
		assert(p.gety() == 10);
			
	}

}
