package coms362.cards;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coms362.cards.app.GameController;
import coms362.cards.app.GameFactoryFactory;
import coms362.cards.streams.InBoundQueue;
import coms362.cards.streams.RemoteTableGateway;
import coms362.events.InvalidGameSelection;
import model.Game;


/**
 * 
 * Tests that invalidGameSelection prints the proper message
 * Was going to test the 52 single player, but that was fully tested by my teammates
 * 
 * @author John Lawless
 *
 */
public class TestInvalidGameSelection {
	
	
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	@Test
	public void test()
	{
		InBoundQueue iq = new InBoundQueue();
		GameController g1 = new GameController(iq, RemoteTableGateway.getInstance(), new GameFactoryFactory());
		InvalidGameSelection is = new InvalidGameSelection("Test Message");

		g1.apply(is, new Game());
		
		assertTrue(outContent.toString().contains("InvalidGameSelection Event"));
	}

}