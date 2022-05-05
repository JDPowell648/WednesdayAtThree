package coms362.cards.war;


import java.util.Map;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.RulesDispatchBase;
import coms362.cards.abstractcomp.Table;
import coms362.cards.fiftytwo.PickupRules;
import events.inbound.CardEvent;
import events.inbound.ConnectEvent;
import events.inbound.DealEvent;
import events.inbound.Event;
import events.inbound.EventUnmarshallers;
import events.inbound.GameRestartEvent;
import events.inbound.InitGameEvent;
import events.inbound.NewPartyEvent;
import events.inbound.SetQuorumEvent;
import events.inbound.TimerEvent;
import model.Card;


/**
 * @author John Lawless
 * created class structure, constructors, and second two methods
 */
public class WarRules extends PickupRules
implements Rules, RulesDispatch {
	
    private CardEventFSM cardFSM;
    
    public WarRules()
    {
    	cardFSM = new CardEventFSM();
    }
    
    /**
     * @author Dylan Black
     */
    @Override
    public Move apply(CardEvent e, Table table, Player player){
		
		String cardId = e.getId();
		Map<Integer, Player> players = table.getPlayerMap();
		
		// Ensure that the card that the current player is attempting to draw is from their draw pile.
		if ((players.get(1) == player && table.getPile("Player1Draw").getCard(cardId) != null)
			|| (players.get(2) == player && table.getPile("Player2Draw").getCard(cardId) != null))
			return cardFSM.apply(e, table, player);
				
		return null;
	}
	
	@Override
	public Move apply(DealEvent e, Table table, Player player){
		return new WarDealCmd(table, player);
	}
	
	@Override
	public Move apply(InitGameEvent e, Table table, Player player){	
		return new WarInitCmd(table.getPlayerMap());
	}
	

}