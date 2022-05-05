package coms362.cards;
/**
 * @author John Ferguson
 */
import static org.junit.Assert.*;

import org.junit.Test;
import coms362.cards.fiftytwoSP.*;
import events.inbound.*;
import coms362.cards.fiftytwo.*;
import model.Quorum;
import model.TableBase;
import events.inbound.SetQuorumEvent;

public class TestSPRules {
	

	@Test
	public void test() {
		PickupSPRules tes = new PickupSPRules();
		TableBase one = new TableBase(null);
		SetQuorumCmd use = (SetQuorumCmd) tes.apply(new SetQuorumEvent(null), one, null);
		use.apply(one);
		assertEquals(one.getQuorum().exceeds(0), false);
		assertEquals(one.getQuorum().exceeds(2), true);
	}

}
