package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * An action for Zombies to attack another actor
 * @author adrianprice
 *
 */
public class ZombieAttackAction extends AttackAction {
	/**
	 * Constructor
	 * @param target The actor to attack
	 */
	public ZombieAttackAction(Actor target) {
		super(target);
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();

		boolean misses = false;
		int random_num = Math.abs(rand.nextInt() % 10) + 1; //Random number between 1 -10
		
		if (weapon.verb().equals("punches")) {
			if (random_num < 6) { // 50% chance to fail
				misses = true;
			}
		} else if (weapon.verb().equals("bites")) {
			if (random_num < 8) { // 70% chance to fail
				misses = true;
			}
		} else {
			if (random_num < 6) { // 50% chance to fail
				misses = true;
			}
		}
		
		if (misses) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		
		if (weapon.verb().equals("bites")) {
			actor.heal(5);
			result += System.lineSeparator() +  actor + " heals for " + 5 + " damage.";
		}
		

		target.hurt(damage);
		if (!target.isConscious()) {
			Item corpse = new CorpseItem("dead", '%', false);
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}
}
