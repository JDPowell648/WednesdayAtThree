package coms362.cards.war;

import coms362.cards.abstractcomp.GameFactory;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.abstractcomp.View;
import coms362.cards.abstractcomp.ViewFactory;
import coms362.cards.streams.RemoteTableGateway;
import coms362.cards.fiftytwo.P52GameFactory;
import model.PlayerFactory;
import model.TableBase;

/**
 * @author John Lawless
 */
public class WarGameFactory extends P52GameFactory implements GameFactory, PlayerFactory, ViewFactory {

	@Override
	public Rules createRules() {
		return new WarRules();
	}

}
