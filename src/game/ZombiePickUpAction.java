package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class ZombiePickUpAction extends Action {
	/** The item for the Zombie to pick up */
	Item itemToPickUp;
	
	/**
	 * Constructor
	 * @param itemToPickUp the item for the Zombie to pick up
	 */
	public ZombiePickUpAction(Item itemToPickUp) {
		this.itemToPickUp = itemToPickUp;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		actor.addItemToInventory(itemToPickUp);
		Location actorLocation = map.locationOf(actor);
		actorLocation.removeItem(itemToPickUp);
		
		return actor + " picks up the " + itemToPickUp.toString();
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " picks up the " + itemToPickUp.toString();
	}

}
