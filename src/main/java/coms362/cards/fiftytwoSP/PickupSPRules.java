package coms362.cards.fiftytwoSP;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.fiftytwo.PickupRules;
import coms362.cards.fiftytwo.SetQuorumCmd;
import events.inbound.Event;
import events.inbound.InitGameEvent;
import events.inbound.SetQuorumEvent;
import model.Quorum;

public class PickupSPRules extends PickupRules implements Rules
{
	@Override
	public Move eval(Event nextE, Table table, Player player)
	{
		return nextE.dispatch(this, table, player);
	}
	
	@Override
	public Move apply(InitGameEvent e, Table table, Player player)
	{
		table.getPlayer((Integer) 1);
		
		return new PickupSPInitCmd(table.getPlayerMap());
	}
	
	@Override
	public Move apply(SetQuorumEvent e, Table table, Player player)
	{
		return new SetQuorumCmd(new Quorum(1, 1));
	}
}
