package coms362.cards;

import static org.junit.Assert.*;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.fiftytwo.DropEventCmd;
import coms362.cards.fiftytwo.PickupPlayer;
import coms362.cards.fiftytwoSP.PickupSPRules;
import events.inbound.CardEvent;
import events.inbound.DealEvent;
import events.inbound.InitGameEvent;
import org.junit.Test;
import coms362.cards.war.*;
import model.*;
/**
 * @author Joshua Powell
 * not sure if this is the best way to test if the rules are functioning correctly
 */
public class Iteration3PowellTest {

    @Test
    public void test() {
        TableBase t = new TableBase(null);
        WarRules rules = new WarRules();
        WarInitCmd init = new WarInitCmd(null);
        PickupPlayer player = new PickupPlayer(1);
        init.apply(t);

        Move move = rules.eval(new CardEvent("1", "0"), t, player);
        assertTrue(move == null);

        move = rules.eval(new DealEvent(), t, player);
        assertTrue(move instanceof WarDealCmd);

        move = rules.eval(new InitGameEvent(), t, player);
        assertTrue(move instanceof WarInitCmd);
    }

}
