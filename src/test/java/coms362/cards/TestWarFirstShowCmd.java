package coms362.cards;

import static org.junit.Assert.*;

import org.junit.Test;
import coms362.cards.war.*;
import model.*;
/**
 * @author John Lawless
 */
public class TestWarFirstShowCmd {

	@Test
	public void test() {
		TableBase t = new TableBase(null);
		WarInitCmd test = new WarInitCmd(null);
		Card c = new Card();
		test.apply(t);
		Player p = t.getCurrentPlayer();
		WarFirstShowCmd cmd = new WarFirstShowCmd(Integer.toString(c.getId()).toString(),p);
		cmd.apply(t);
		assertTrue(t.getPile("Player1Play").cards.size() >0);
		
	}

}
