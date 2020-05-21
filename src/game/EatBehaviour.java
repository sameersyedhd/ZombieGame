package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class EatBehaviour implements Behaviour  {
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location locationOfActor = map.locationOf(actor);
		List<Item> items = locationOfActor.getItems();
		
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).toString().equals("food")) {
				return new EatAction((RipeCrop) items.get(i));
				}			
			}
		return null;	
	}

}
