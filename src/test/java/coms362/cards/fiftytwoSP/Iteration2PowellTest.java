package coms362.cards.fiftytwoSP;

import static org.junit.Assert.*;

import coms362.cards.abstractcomp.*;
import coms362.cards.fiftytwo.*;
import coms362.cards.streams.InBoundQueue;
import org.junit.Test;

import coms362.cards.socket.SocketEvent;
import events.inbound.*;
import model.Card;
import model.Location;
import model.Pile;
import model.Quorum;
import model.TableBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Iteration2PowellTest
{

    boolean anyIssue = false;

    @Test
    public void test()
    {
        InBoundQueue inQ = new InBoundQueue();
        //pre-load the input stream with the input for this test
        inQ.add(new DealEvent());
        inQ.add(new EndPlay()); //artifice to stop the test

        List<View> views = new ArrayList<View>();
        //we keep a reference to the concrete type for later
        LoggingView p1View = new LoggingView();
        views.add(p1View);

        Map<Integer, Player> players = new HashMap<Integer,Player>();
        Player player =  new PickupPlayer(1) ;
        players.put( (Integer) 1, player);

        // initialize the local model for Pu52 match
        Table table = new TableBase(new P52SPGameFactory());
        Move move = new PickupSPInitCmd(players);
        move.apply(table);
        Rules rules = new PickupSPRules();
        Card[] cardArray = new Card[52];

        for(int i = 1; i < 53; i++){
            cardArray[i-1] = table.getPile("discardPile").getCard(Integer.toString(i));
        }

        for (Card currentCard : cardArray) {
            Move move2 = new PickupMove(currentCard, player);
            move2.apply(table);
            if(table.getPile("discardPile").getCard(Integer.toString(currentCard.getId())) == currentCard){
                anyIssue = true;
            }
        }

        System.out.println(player.getScore());

        assertTrue("Testing to see if cards are properly moved from discardPile to Tidy." +
                "\n If true, a card was found in discard after moved, or was not found in Tidy after moved.", anyIssue);

        assertEquals("Testing to make sure if the player's score is properly incremented after each valid move",52, player.getScore());
    }
}
