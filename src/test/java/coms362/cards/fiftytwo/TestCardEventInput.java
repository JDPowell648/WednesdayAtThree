package coms362.cards.fiftytwo;

import static org.junit.Assert.*;

import org.junit.Test;

import coms362.cards.abstractcomp.Player;
import coms362.cards.app.ExitTestException;
import coms362.cards.app.PlayController;
import coms362.cards.app.ViewFacade;
import coms362.cards.fiftytwo.P52GameFactory;
import coms362.cards.fiftytwo.PickupRules;
import coms362.cards.streams.InBoundQueue;
import events.inbound.CardEvent;
import events.inbound.EndPlay;
import model.Card;
import model.Location;
import model.Pile;
import model.TableBase;

/**
 * Tests that a game of pickup 52 successfully iterates through all cards on the table then exits.
 * 
 * @author Dylan Black
 */

public class TestCardEventInput
{
	@Test
	public void test()
	{
		// This specific pile name is REQUIRED due to the implementation of PickupRules.
		// Why do piles have names, anyway?
		Pile pile = new Pile("discardPile", new Location(0, 0));
		
		InBoundQueue inQ = new InBoundQueue();
		for (int i = 0; i < 52; ++i)
		{
			// Insert 52 cards onto the table's pile, and assign an event for removing each one.
			Card card = new Card();
			
			pile.addCard(card);
			inQ.add(new CardEvent(Integer.toString(card.getId()), "0"));
		}
		inQ.add(new EndPlay()); // An EndPlay to terminate the test.
		
		P52GameFactory gf = new P52GameFactory();
		Player player = gf.createPlayer(1, "0");
		
		// Make a table and provide the pile that was just populated.
		TableBase table = new TableBase(gf);
		table.addPile(pile);
		table.addPlayer(player);
		
		// Run the game.
		// The game should reach its conclusion by throwing an ExitTestException, 
		// but no other exception. Any other exception would indicate a real error.
		PlayController pc = new PlayController(inQ, new PickupRules());
		try
		{
			pc.play(table, player, new ViewFacade(gf));
		}
		catch (Exception e)
		{
			assertTrue(e instanceof ExitTestException);
			return;
		}
		
		// Only reachable if no exception is thrown.
		fail();
	}
}