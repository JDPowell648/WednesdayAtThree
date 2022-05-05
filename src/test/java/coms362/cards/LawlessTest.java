package coms362.cards;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.fiftytwo.PickupInitCmd;
import coms362.cards.fiftytwo.PickupMove;
import coms362.cards.fiftytwo.PickupPlayer;
import coms362.cards.fiftytwo.P52GameFactory;
import model.Card;
import model.TableBase;
import model.PlayerFactory;

/**
 * 
 * Tests that PickupMove picks up the card correctly
 * 
 * @author John Lawless
 *
 */

public class LawlessTest {


	@Test
	public void test() {

		String pileName = "discardPile";
		boolean res = true;
		
		TableBase table = new TableBase(new P52GameFactory());
		//table.addPlayer(p1);
		//table.addPlayer(p2);
		table.apply(new PickupInitCmd(table.getPlayerMap()));
		
		Random rand = new Random();

		Card c1 = table.getPile(pileName).getCard(Integer.toString(rand.nextInt(52)));

		Card c2 = new Card();
		c2.setSuit(c1.getSuit());
		c2.setNumber(c1.getNumber());

		//table.apply(new PickupMove(c1, p1));
		
		for(int i = 0; i < 51; i++){
			Card c3;
			if (table.getPile(pileName).getCard(Integer.toString(i)) != null)
				c3 = table.getPile(pileName).getCard(Integer.toString(i));
			else
				continue;
			if(c3.getSuit() == c2.getSuit() && c3.getNumber() == c2.getNumber())
			{
				res = false;
			}
		}
		
		assertTrue(res);

	}

}
