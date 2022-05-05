package coms362.cards.app;

import java.util.HashMap;

import coms362.cards.abstractcomp.GameFactory;
import coms362.cards.fiftytwo.P52GameFactory;
import coms362.cards.fiftytwoSP.P52SPGameFactory;
import coms362.cards.war.WarGameFactory;


public class GameFactoryFactory {
	
	private static HashMap<String, GameFactory> gameMap = null;
	
	public GameFactoryFactory()
	{
		// Perform lazy initialization of the gameMap.
		if (gameMap == null)
		{
			gameMap = new HashMap<>();
			gameMap.put("PU52MP", new P52GameFactory());
			gameMap.put("PU52", new P52SPGameFactory());
			gameMap.put("WAR", new WarGameFactory());
		}
	}
	
	public GameFactory getGameFactory(String selector){
		return gameMap.get(selector);
	}

	public boolean isValidSelection(String gameId) {
		return gameMap.containsKey(gameId);
	}
}
