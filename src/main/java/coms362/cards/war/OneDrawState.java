package coms362.cards.war;

import java.util.Map;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import events.inbound.CardEvent;

/**
 * @author Dylan Black
 */

public class OneDrawState implements State
{
	private static SweepState sweepState = new SweepState();

	@Override
	public void apply(CardEvent event, Table table, Player player, Move returnMove, State nextState)
	{
		Map<Integer, Player> players = table.getPlayerMap();
		
		// Only the player who has not drawn yet this round can draw.
		if ((players.get(1) == player && table.getPile("Player2Play").getSize() == 0)
			|| players.get(2) == player && table.getPile("Player1Play").getSize() == 0)
		{
			returnMove = new WarSecondShowCmd(event.getId(), player);
			nextState = sweepState;
		}
		else
			nextState = this; // State does not change.
	}

}
