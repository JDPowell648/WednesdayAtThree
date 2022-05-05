package coms362.cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Card;

public class TestCardIdandFaceUp {
	
	@Test
	public void test() {
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		for(int i = 0; i < 10; i++) {
			Card testCard = new Card();
			testCard.setFaceUp(false);
			cards.add(testCard);
		}
		
		
		for(int i = 0; i < 10; i++) {
			assertFalse(cards.get(i).isFaceUp());
		}
	}
}
