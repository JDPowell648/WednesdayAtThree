package coms362.cards.war;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.abstractcomp.View;
import coms362.cards.app.ViewFacade;
import coms362.cards.fiftytwo.DealButton;
import coms362.cards.fiftytwo.HideButtonRemote;
import events.remote.CreateRemote;
import events.remote.UpdateRemote;
import model.Card;
import model.Pile;

/**
 * @author John Ferguson
 */

public class WarDealCmd implements Move {
	private Table table;
	private Player player;
			
	public WarDealCmd(Table table, Player player) {
		this.table = table;
		this.player = player;
	}

	public void apply(Table table) {
		// TODO Auto-generated method stub
		
	}
	
	public void apply(ViewFacade views) {
		  try {
	        	String remoteId = views.getRemoteId(DealButton.kSelector);
	        	views.send(new HideButtonRemote(remoteId));
	        	Pile local = table.getPile("Player1Draw");
	        	if (local == null) {
	        		return;
	        	}
	            for (Card c : local.cards.values()) {
	            	String outVal="";
	            	views.send(new CreateRemote(c));
	            	views.send(new UpdateRemote(c));
	                System.out.println(outVal);	            
		        }
	            local = table.getPile("Player2Draw");
	        	if (local == null) {
	        		return;
	        	}
	            for (Card c : local.cards.values()) {
	            	String outVal="";
	            	views.send(new CreateRemote(c));
	            	views.send(new UpdateRemote(c));
	                System.out.println(outVal);	            
		        }
	        }catch (Exception e) {
		            e.printStackTrace();
	        }
	}
}
