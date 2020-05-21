package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import java.util.Random;
import java.lang.Math;

public class CorpseItem extends Item{
	private int age = 0;
	private int reviveTurns = 0;

	
	public CorpseItem(String name, char displayChar, boolean portable) {
		super(name, displayChar, portable);
		// TODO Auto-generated constructor stub
		Random rand = new Random();
		setReviveTurns(Math.abs(rand.nextInt(5) + 1) + 5);
	}
	
	@Override
	public void tick(Location location) {
		super.tick(location);
		age++;
		if (age == reviveTurns) {
			location.addActor(new Zombie("Aaargh"));
			location.removeItem(this);
		}
	}

	public void setReviveTurns(int reviveTurns) {
		this.reviveTurns = reviveTurns;
	}
	
}
