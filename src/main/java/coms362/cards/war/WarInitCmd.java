package coms362.cards.war;

import java.util.Map;
import java.util.Random;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.fiftytwo.DealButton;
import events.inbound.DealEvent;
import events.remote.CreateButtonRemote;
import events.remote.CreatePile;
import events.remote.SetBottomPlayerTextRemote;
import events.remote.SetGameTitleRemote;
import events.remote.SetupTable;
import model.Button;
import model.Card;
import model.Location;
import model.Pile;

/**
 * @author John Ferguson
 */

public class WarInitCmd implements Move {
	
	protected Map<Integer, Player> players;
	protected String title = "War";
	protected Pile Player1Draw;
	protected Pile Player2Draw;
	protected Pile Player1Discard;
	protected Pile Player2Discard;
	protected Pile Player1Play;
	protected Pile Player2Play;
	
	public WarInitCmd(Map<Integer, Player> players) {
		this.players = players;
		Player1Draw = new Pile("Player1Draw", new Location(1000, 600));
		Player2Draw = new Pile("Player2Draw", new Location(100, 600));
		Player1Discard = new Pile("Player1Discard", new Location(1000, 300));
		Player2Discard = new Pile("Player2Discard", new Location(100, 300));
		Player1Play = new Pile("Player1Play", new Location(900, 450));
		Player2Play = new Pile("Player2Play", new Location(200, 450));
	}
	
	public WarInitCmd(Map<Integer, Player> players, String title) {
		this(players);
		this.title = title;
	}

	@Override
	public void apply(Table table) {
		Random random = table.getRandom();
		try {
			for (String suit : Card.suits) {
                for (int i = 1; i <= 13; i++) {
                    Card card = new Card();
                    card.setSuit(suit);
                    card.setNumber(i);                    
                    if((random.nextInt()%2 == 0 && Player1Draw.cards.size() < 26) || Player2Draw.cards.size() == 26) {
                    	Player1Draw.addCard(card);
                    }else {
                    	Player2Draw.addCard(card);
                    }
                    
                }
            }			
			table.addPile(Player1Discard);
			table.addPile(Player1Draw);
			table.addPile(Player1Play);
			table.addPile(Player2Discard);
			table.addPile(Player2Draw);
			table.addPile(Player2Play);
		} catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void apply(ViewFacade view) {
		view.send(new SetupTable());
		view.send(new SetGameTitleRemote(title));
		
		for (Player p : players.values()){
			String role = (p.getPlayerNum() == 1) ? "Dealer" : "Player "+p.getPlayerNum();
			view.send(new SetBottomPlayerTextRemote(role, p));
		}
		
		view.send(new CreatePile(Player1Draw));
		view.send(new CreatePile(Player2Draw));
		view.send(new CreatePile(Player1Discard));
		view.send(new CreatePile(Player2Discard));
		String id = ""; 
		DealButton dealButton = new DealButton("DEAL", new Location(0, 0));
		view.register(dealButton); //so we can find it later. 
		view.send(new CreateButtonRemote(dealButton));
		//view.send(new CreateButtonRemote(Integer.toString(getNextId()), "reset", "RestartGame", "Reset", new Location(500,0)));
		//view.send(new CreateButtonRemote(Integer.toString(getNextId()), "clear", "ClearTable", "Clear Table", new Location(500,0)));
	
	}
	
}