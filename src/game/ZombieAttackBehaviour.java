package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
/**
 * Ths class generates a ZombieAttackAction for a Zombie, if the Zombie is standing next to a human
 * @author adrianprice
 *
 */
public class ZombieAttackBehaviour extends AttackBehaviour {
	
	/**
	 * Constructor.
	 * 
	 * Sets the team (i.e. ZombieCapability) that the owner of this
	 * Behaviour is allowed to attack.
	 * @param attackableTeam Team descriptor for ZombieActors that can be attacked
	 */
	public ZombieAttackBehaviour(ZombieCapability attackableTeam) {
		super(attackableTeam);
	}
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there an attackable Actor next to me?
				List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
				Collections.shuffle(exits);
				
				for (Exit e: exits) {
					if (!(e.getDestination().containsAnActor()))
						continue;
					if (e.getDestination().getActor().hasCapability(attackableTeam)) {
						return new ZombieAttackAction(e.getDestination().getActor());
					}
				}
				return null;
	}
}
