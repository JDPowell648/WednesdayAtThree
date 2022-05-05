package coms362.cards.fiftytwoSP;

import static org.junit.Assert.*;

import org.junit.Test;

import coms362.cards.abstractcomp.Move;
import coms362.cards.fiftytwo.CreatePlayerCmd;
import coms362.cards.fiftytwo.DealCommand;
import coms362.cards.fiftytwo.DropEventCmd;
import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.fiftytwo.PickupMove;
import coms362.cards.fiftytwo.PickupPlayer;
import coms362.cards.fiftytwo.SetQuorumCmd;
import coms362.cards.socket.SocketEvent;
import events.inbound.*;
import model.Card;
import model.Location;
import model.Pile;
import model.Quorum;
import model.TableBase;

public class TestPickupSPRulesResponse
{
	
	@Test
	public void test()
	{
		PickupPlayer player = new PickupPlayer(1);
		Pile pile = new Pile("discardPile", new Location(0, 0));
		PickupSPRules rules = new PickupSPRules();
		TableBase table = new TableBase(new P52SPGameFactory());
		
		// Test responses of CardEvent evaluation.
		table.addPile(pile);
		Move move = rules.eval(new CardEvent("1", "0"), table, player);
		assertTrue(move instanceof DropEventCmd);
		pile.addCard(new Card());
		move = rules.eval(new CardEvent("1", "1"), table, player);
		assertTrue(move instanceof PickupMove);
		
		// Test response of DealEvent evaluation.
		move = rules.eval(new DealEvent(), table, player);
		assertTrue(move instanceof DealCommand);
		
		// Test response of InitGameEvent evaluation.
		move = rules.eval(new InitGameEvent(), table, player);
		assertTrue(move instanceof PickupSPInitCmd);
		
		// Test response of NewPartyEvent evaluation.
		NewPartyEvent party = new NewPartyEvent(PartyRole.player, "0", "0");
		move = rules.eval(party, table, player);
		assertTrue(move instanceof CreatePlayerCmd);
		party = new NewPartyEvent(PartyRole.host, "0", "0");
		move = rules.eval(party, table, player);
		assertTrue(move instanceof DropEventCmd);
		
		// Test response of SetQuorumEvent evaluation.
		move = rules.eval(new SetQuorumEvent(new Quorum(100, 1000)), table, player);
		assertTrue(move instanceof SetQuorumCmd);
		move.apply(table);
		Quorum quorum = table.getQuorum();
		assertTrue(quorum.meets(1));
		assertTrue(quorum.exceeds(2));
		
		// Test response of ConnectEvent evaluation.
		
		move = rules.eval(new ConnectEvent(new SocketEvent("", 0)), table, player);
		assertTrue(move instanceof DropEventCmd);
	}
}
