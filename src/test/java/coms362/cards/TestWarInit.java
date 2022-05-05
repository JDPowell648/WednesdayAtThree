package coms362.cards;

import static org.junit.Assert.*;

import org.junit.Test;
import coms362.cards.war.*;
import model.*;
/**
 * @author John Ferguson
 */
public class TestWarInit {

	@Test
	public void test() {
		TableBase t = new TableBase(null);
		WarInitCmd test = new WarInitCmd(null);
		test.apply(t);
		assertEquals(t.getPile("Player1Draw").getName(), "Player1Draw");
		assertEquals(t.getPile("Player2Draw").getName(), "Player2Draw");
		assertEquals(t.getPile("Player1Play").getName(), "Player1Play");
		assertEquals(t.getPile("Player2Play").getName(), "Player2Play");
		assertEquals(t.getPile("Player1Discard").getName(), "Player1Discard");
		assertEquals(t.getPile("Player2Discard").getName(), "Player2Discard");
		
	}

}
