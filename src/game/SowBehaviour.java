package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;


public class SowBehaviour implements Behaviour {
	Random rand = new Random();

	@Override
	public Action getAction(Actor actor, GameMap map) {
		int randomNum = Math.abs(rand.nextInt() % 3) + 1; //Random number between 1 -10
		ArrayList<Location> validLocations = validGroundAroundActor(actor, map);
		if (validLocations.size() > 0) {
			if (randomNum == 3) {
				return new SowAction(validLocations.get(0));
			}
		}
		return null;
		
	}
	
	private ArrayList<Location> validGroundAroundActor(Actor actor, GameMap map) {
		ArrayList<Location> validLocations = new ArrayList<>();
		
		Location locationOfActor = map.locationOf(actor);
		int x0 = locationOfActor.x();
		int y0 = locationOfActor.y();
		
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				Location newLocation = map.at(x0 + x, y0 + y);
				if (newLocation.canActorEnter(actor)) {
					if (newLocation.getItems().size() == 0) {
						if (x != 0 && y != 0) {
							validLocations.add(newLocation);
						}
					}
				}
			}
		}
		
		return validLocations;
		
	}

}
