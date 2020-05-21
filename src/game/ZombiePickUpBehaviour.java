package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

public class ZombiePickUpBehaviour implements Behaviour {

	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (this.alreadyHasWeapon(actor)) {
			return null;
		}
		Location actorLocation = map.locationOf(actor);
		
		List<Item> itemsAtLocation = actorLocation.getItems();
		
		for (int x = 0; x < itemsAtLocation.size(); x++) {
			if (itemsAtLocation.get(x) instanceof Weapon) {
				return new ZombiePickUpAction(itemsAtLocation.get(x));
			}
		}
		
		return null;
	}
	
	/**
	 * Iterates through the actors inventory and returns true if they already have a weapon and false otherwise
	 * @param actor the actor whose inventory is being searched
	 * @return boolean
	 */
	private boolean alreadyHasWeapon(Actor actor) {
		boolean hasWeapon = false;
		
		List<Item> inventory = actor.getInventory();
		
		for (int x = 0;  x < inventory.size(); x++) {
			if (inventory.get(x) instanceof Weapon) {
				hasWeapon = true;
			}
		}
		
		return hasWeapon;
	}

}
