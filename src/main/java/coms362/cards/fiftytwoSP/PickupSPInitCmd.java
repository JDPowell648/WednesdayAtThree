package coms362.cards.fiftytwoSP;

import java.util.Map;

import coms362.cards.abstractcomp.Player;
import coms362.cards.app.ViewFacade;
import coms362.cards.fiftytwo.DealButton;
import coms362.cards.fiftytwo.PickupInitCmd;
import events.remote.CreateButtonRemote;
import events.remote.CreatePile;
import events.remote.SetBottomPlayerTextRemote;
import events.remote.SetGameTitleRemote;
import events.remote.SetupTable;
import model.Location;
import model.Pile;

public class PickupSPInitCmd extends PickupInitCmd
{
	public PickupSPInitCmd(Map<Integer, Player> players)
	{
		super(players);
		title = "52 Card Solitaire Pickup";
	}
	
	public void apply(ViewFacade view) 
	{
		view.send(new SetupTable());
		view.send(new SetGameTitleRemote(title));

		Player p = players.get(1);
		view.send(new SetBottomPlayerTextRemote("Player", p));

		view.send(new CreatePile(new Pile("discardPile", new Location(500,359))));
		DealButton dealButton = new DealButton("DEAL", new Location(0, 0));
		view.register(dealButton); //so we can find it later. 
		view.send(new CreateButtonRemote(dealButton));
		//view.send(new CreateButtonRemote(Integer.toString(getNextId()), "reset", "RestartGame", "Reset", new Location(500,0)));
		//view.send(new CreateButtonRemote(Integer.toString(getNextId()), "clear", "ClearTable", "Clear Table", new Location(500,0)));
	}
}
