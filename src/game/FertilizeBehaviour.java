package game;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class FertilizeBehaviour implements Behaviour{
	
	Random rand = new Random();

	@Override
	public Action getAction(Actor actor, GameMap map) {
		int rand = Math.random() > 0.5 ? 1: 2;
		Location locationOfActor = map.locationOf(actor);
		List<Item> items = locationOfActor.getItems();
		
		if (rand == 2) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).toString().equals("crop")) {
				return new FertilizeAction((UnripeCrop) items.get(i));
				}			
			}
		}
		return null;	
	}
		
}
