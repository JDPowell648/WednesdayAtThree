package coms362.cards;

import static org.junit.Assert.*;

import org.junit.Test;

import coms362.cards.abstractcomp.View;
import coms362.cards.app.PlayController;
import coms362.cards.fiftytwo.LoggingView;
import coms362.cards.fiftytwo.PickupPlayer;
import coms362.cards.fiftytwo.PickupRules;
import coms362.cards.streams.InBoundQueue;
import events.inbound.DealEvent;
import events.inbound.CardEvent;
import events.inbound.EndPlay;
import model.Card;
import model.Location;
import model.Pile;
import model.TableBase;

//@author John Ferguson

public class TestInBoundQueue {

	@Test
	public void test() {
		InBoundQueue inQ = new InBoundQueue();
		inQ.add(new DealEvent());
		inQ.add(new CardEvent("test", "socket"));
		inQ.add(new CardEvent("test1", "socket"));
		inQ.add(new EndPlay());
		try {
			assertEquals(new DealEvent().getClass(), inQ.take().getClass());
			assertEquals(new CardEvent("test", "socket").getClass(), inQ.take().getClass());
			assertEquals(new CardEvent("test1", "socket").getClass(), inQ.take().getClass());
			assertEquals(new EndPlay().getClass(), inQ.take().getClass());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
