package coms362.cards.war;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import events.inbound.CardEvent;

/**
 * @author Dylan Black
 */

public class NoDrawState implements State
{
	private static OneDrawState oneDrawState = new OneDrawState();

	@Override
	public void apply(CardEvent event, Table table, Player player, Move returnMove, State nextState)
	{
		// If no draws have yet occurred, then the initial draw, regardless of player, will always succeed.
		returnMove = new WarFirstShowCmd(event.getId(), player);
		nextState = oneDrawState;
	}
	
}
