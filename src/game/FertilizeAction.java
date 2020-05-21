package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class FertilizeAction extends Action {
	UnripeCrop cropToFertilize;
	
	public FertilizeAction(UnripeCrop cropToFertilize) {
		this.cropToFertilize = cropToFertilize;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		cropToFertilize.fertelise();
		
		return actor + " fertilizes a crop, it grows faster!";
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " fertilizes a crop, it grows faster!";
	}

}