package survivingit.items;

import survivingit.gameobjects.Creature;

/**
 * Item effect for healong
 */
public class HealEffect extends UsableEffect {

    private int healAmount;
    private int uses;

    /**
     * Creates a new HealEffect object with the entered healAmount, uses and source Item.
     * @param healAmount int value of the heal amount of the new HealEffect object.
     * @param uses int value of the number of uses of the new HealEffect object
     */
    public HealEffect(final int healAmount, final int uses) {
        super();
        if (healAmount < 0) {
            throw new IllegalArgumentException("Negative heal amount not allowed");
	    } else if (uses < 0) {
            throw new IllegalArgumentException("Negative use amount not allowed");
        }
        this.healAmount = healAmount;
        this.uses = uses;
    }

    /**
     * Uses the HealEffect on the user.
     * @param user Creature that used the item.
     */
    @Override
    public void use(Creature user) {
        if (this.uses > 0 ) {
            user.heal(this.healAmount);
            uses--;
        } else {
            System.out.println("Out of uses!");
        }
    }

}
