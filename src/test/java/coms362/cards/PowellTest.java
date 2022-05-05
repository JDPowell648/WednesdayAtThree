package coms362.cards;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

import coms362.cards.abstractcomp.*;
import coms362.cards.fiftytwo.*;
import model.Card;
import org.junit.Test;

import coms362.cards.app.PlayController;
import coms362.cards.streams.InBoundQueue;
import events.inbound.DealEvent;
import events.inbound.EndPlay;
import model.TableBase;

/**
 * Tests the randomness of 56000 cards' positions
 *
 * @author Joshua Powell
 */


public class PowellTest {

    @Test
    public void test() {

        int sameXCount = 0;
        int sameYCount = 0;
        int samePosCount = 0;
        int currentCardIndex = 1;

        for(int j = 0; j < 1000; j++){
            //set up game and match resources to provision play loop
            InBoundQueue inQ = new InBoundQueue();
            //pre-load the input stream with the input for this test
            inQ.add(new DealEvent());
            inQ.add(new EndPlay()); //artifice to stop the test

            List<View> views = new ArrayList<View>();
            //we keep a reference to the concrete type for later
            LoggingView  p1View = new LoggingView();
            views.add(p1View);

            Map<Integer, Player> players = new HashMap<Integer,Player>();
            Player player =  new PickupPlayer(1) ;
            players.put( (Integer) 1, player);
            players.put( (Integer) 2, new PickupPlayer(2) );

            // initialize the local model for Pu52 match
            Table table = new TableBase(new P52GameFactory());
            Move move = new PickupInitCmd(players);
            move.apply(table);
            Rules rules = new PickupRules();
            Card[] cardArray = new Card[52];

            for(int i = 0; i < 52; i++){
                cardArray[i] = table.getPile("discardPile").getCard(Integer.toString(currentCardIndex));
                currentCardIndex++;
            }

            for(int i = 1; i < 52; i++){
                if (cardArray[i].getX() == cardArray[i-1].getX() && cardArray[i].getY() == cardArray[i-1].getY()){
                    samePosCount++;
                }
                else if(cardArray[i].getX() == cardArray[i-1].getX()){
                    sameXCount++;
                }
                else if(cardArray[i].getY() == cardArray[i-1].getY()){
                    sameYCount++;
                }
            }
        }

        double samePosChance = 0.000025d;
        double sameXYChance = 0.005d;

        String messagePos = "\nCards with the same position: " + samePosCount + "\nExpected: About 2 or less\n";
        String messageX = "Cards with only the same X position: " + sameXCount + "\nExpected: About 260 or less\n";
        String messageY = "Cards with only the same Y position: " + sameYCount + "\nExpected: About 260 or less\n";
        System.out.println(messagePos + "\n" + messageX + "\n" + messageY);
        assertEquals(messagePos,true, samePosChance >= (samePosCount/52000d));
        assertEquals(messageX,true, sameXYChance >= (sameXCount/52000d));
        assertEquals(messageY, true, sameXYChance >= (sameYCount/52000d));
    }

}