package coms362.cards.war;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import events.inbound.CardEvent;

/**
 * @author Dylan Black
 */

public interface State
{
	public void apply(CardEvent event, Table table, Player player, Move returnMove, State nextState);
}
