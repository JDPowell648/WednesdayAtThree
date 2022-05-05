package coms362.cards;

import org.junit.Test;
import static org.junit.Assert.*;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.fiftytwoSP.P52SPGameFactory;
import coms362.cards.fiftytwoSP.PickupSPInitCmd;
import events.inbound.InitGameEvent;

/*
 * @author Jack Creighton
 */

public class TestSPInitCmd {

	@Test
	public void test() {
		P52SPGameFactory factory = new P52SPGameFactory();
		Rules rules = factory.createRules();
		Table table = factory.createTable();
		
		Move initCmd = rules.eval(new InitGameEvent(), table, null);
		
		assertTrue(initCmd instanceof PickupSPInitCmd);
	}
}
