package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class SowAction extends Action {
	Location locationToSow;
	
	public SowAction(Location locationToSow) {
		this.locationToSow = locationToSow;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		locationToSow.addItem(new UnripeCrop());
		
		return actor + " sows a crop, lets see what grows!!!";
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " sows a crop, lets see what grows!!!";
	}

}
