package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class UnripeCrop extends Item {
	int timeToRipen = 20;
	
	
	public UnripeCrop() {
		super("crop",'*', false);
	}
	
	public void fertelise() {
		this.timeToRipen -= 10;
	}
	
	public int getTimeToRipen() {
		return this.timeToRipen;
	}
	
	@Override
	public void tick(Location currentLocation) {
		this.timeToRipen -= 1;
		
		if (isRipe()) {
			currentLocation.addItem(new RipeCrop());
			currentLocation.removeItem(this);
		}
	}
	
	public boolean isRipe() {
		return this.timeToRipen <= 0;
	}
}
