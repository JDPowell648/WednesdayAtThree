package coms362.cards;

import static org.junit.Assert.*;

import org.junit.Test;

import coms362.cards.streams.Marshalls;
import model.Location;
import model.Pile;
import events.remote.CreatePile;

//@author John Ferguson

public class TestCreatePile {

	@Test
	public void test() {
		CreatePile deck = new CreatePile(new Pile("deck", new Location(2, 2)));
		assertEquals("deck = new cards.Deck({faceUp:false, x:2, y:2});",deck.marshall());
		assertEquals("CreatePile p=deck", deck.stringify());
	}

}
