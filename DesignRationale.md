# Design Rationale:

### Zombie Attacks

---

**Bite Attack Action & Healing:**
I have added a new ZombieAttackAction, which extends the AttackAction class. This will be created by a ZombieAttackBehaviour, which extends the AttackBehaviour class. This will allow me to add the possibility of healing as well as variable accuracy to attacks. In order to meet the Don't Repeat Yourself criteria we will only need to override the execute method

Before this was going to be achieved by creating 2 new healing and non-healing classes inherited from IntrnsicWeapon, but I realized that this would violate the Liskov Substitution Principle, so have decided that above is the best way to achieve it. 

**Picking Up Weapons:**
In Locations we will have a containsWeapon method which will go through the items arrayList and, if there is an item of type WeaponItem it will return the WeaponItem if it exists or Null otherwise
If the weapon does exist we will then call the PickUpItemAction, passing the returned WeaponItem in as a parameter and the weapon will be added to the Zombie's inventory
After this the Zombie will automatically use the weapon as already dictated in the code
This change does not require the need for new classes, but adds a dependency as Zombie will create an instance of PickUpItemAction
	
**Saying Brains:**
I have created a new Behaviour called ZombieNoiseBehaviour, which will result in the Zombie saying “BRAAAAAAAINS”.This represents their whole turn and cannot do anything else. This will have a 10% chance of happening. The possibility of it happening will happen at the start of playTurn in the Zombie Class. It will be decided by a random number between 1 and 10, if it is 1 then we do the ZombieNoiseBehaviour

### Beating up the Zombies

---

New behaviour called DamageBehaviour deals with determining the outcome of an attack on the Zombie.
When attacked, there is a 25% chance of the zombie losing one of its limbs. 
This will be decided by a random number between 1 and 4, if it's 1 then the zombie will lose a limb. if its 2-4, it will not lose a limb. Use random number to determine which limb (arm or leg) to remove from the zombie (1 = arm, 2 = leg). If zombie has already lost all arms, then remove a leg and vice versa.

**Losing limbs:**

* **Arm -** losing arms affect the zombies AttackBehaviour.
Losing one arm, halves its probability of punching and increase its chances of dropping any weapon to 50%. 
Losing both arms results in dropping weapon.

* **Leg -** losing legs affects the zombies WanderBehaviour.
Losing one leg, results in halving its movement speed (moves every second turn).
Losing both legs stops the zombie from moving at all but doesn't affect its attacking behaviour.

where should the limbs drop? next to zombie or at an adjacent location?

### Crafting Weapons

---

A player can use dropped zombie limbs as weapons. A zombie arm/leg can be transformed into a club/mace and therefore the weapon will inherit the advantages associated with it (which is inflicting more damage).


### Rising From The Dead

---

This requirement needs a human to turn into a zombie 5-10 turns after a human has died.

**ResurrectionBehavior:**
This class was created in order to meet the above requirement. When a Human dies this class will trigger a counter that waits randomly between 5-10 turns and then create a new zombie object (rather than changing the human object) on the same coordinates as where that human died. Due to inheritience this behavior will work for both Farmers and Humans.


### Farmers And Food

---

This requirement is for a new type of human being a farmer. Farmers can grow crops which turn into food when rippened. This can be eaten to restore HP to damaged humans.

**Farmer:**
This is a subclass of Human. As a farmer shares the same functionalities of a Human in order to maintain the D.R.Y rule farmer inherits all the methods and variables that humans have and in addition implements its own specific functions.

**Crop:**
This is a subclass of ground as it is an object that implements its this generic type that represents the map of the game. It is different from dirt as it has a	different symbol and it has a timer to ripen each turn. 

**Food:**
Food is a subclass of item as it can be picked up and stored in the inventory of a Player. The EatBehavior can be triggered to destroy the food from the players inventory.

**FarmBehavior:**
This is an abstract class that contains the abstract methods relating to food in the system. The purpose of the abstract class is to maintain the open close principle which states that a class should be open for extension but closed for modification. This allows the system to add behaviours relating to food with minimal changes to the Human class. 

The following three classes implement the behaviours of the FarmBehavior class. These classes contain methods that treat food/crops differently depending on the type of Human that is in proximity to it such as Player, Human NPC or Farmer. They were designed separately to allow extension of the system and the single responsibility principle which states that a class should have only one responsibility. 

* **EatBehavior:**
This method triggered through the HarvestBehavior or if the player uses the item while it is in their inventory. It will simply restore health points to the Human and destroy the food object returning the ground to dirt or delete it from the inventory.
 
* **HarvestBehaviour:**
This class' method triggers when there is ripe crop near the Human and it takes the actor as a parameter. It has different behaviour depending on if it is a Human Player or NPC. NPC will instantly trigger the EatBehavior and destroy ripe 	crop that is nearby them while Players will destroy the crop and create a food item in their inventory.

* **CropBehavior:**
This behaviour is only triggered on dirt or crop that is near a farmer. There is a 	1/3 chance the dirt will turn into a crop with 20 days to ripen. If the object 	next to the farmer is a crop there is a random chance that it will fertilise the crop.
