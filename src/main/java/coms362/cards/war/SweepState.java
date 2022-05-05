package coms362.cards.war;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import events.inbound.CardEvent;

/**
 * @author Dylan Black
 */

public class SweepState implements State
{
	private static NoDrawState noDrawState = new NoDrawState();
	
	@Override
	public void apply(CardEvent event, Table table, Player player, Move returnMove, State nextState)
	{
		// TODO: How to get here after second draw?
		returnMove = new SweepCmd(event.getId(), player);
		nextState = noDrawState;
	}
}
