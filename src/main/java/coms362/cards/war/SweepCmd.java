package coms362.cards.war;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;

public class SweepCmd implements Move
{
	String cardId;
	Player player;

	public SweepCmd(String cardId, Player player)
	{
		this.cardId = cardId;
		this.player = player;
	}

	@Override
	public void apply(Table table)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(ViewFacade views)
	{
		// TODO Auto-generated method stub
		
	}
}