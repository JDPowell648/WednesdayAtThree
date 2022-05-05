package coms362.cards.war;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import events.inbound.CardEvent;

/**
 * @author Dylan Black
 */

public class CardEventFSM
{
	private State currentState;
	
	public Move apply(CardEvent event, Table table, Player player)
	{
		Move move = null;
		State nextState = null;
		
		currentState.apply(event, table, player, move, nextState);
		
		currentState = nextState;
		return move;
	}
	
}