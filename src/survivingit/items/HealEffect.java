package survivingit.items;

import survivingit.gameobjects.Creature;

public class HealEffect extends UsableEffect {

    private int healAmount;

    /**
     * Creates a new HealEffect object with the entered healAmount and entered source Item.
     * @param healAmount of the new HealEffect object.
     * @param source of the new HealEffect object.
     */
    public HealEffect(int healAmount, Item source) {
        super(source);
        if (healAmount < 0 ) {
            throw new IllegalArgumentException("Negative heal amount not allowed");
	    }
	    this.healAmount = healAmount;
    }

    /**
     *
     * @param user
     */
    @Override
    public void use(Creature user) {
        user.heal(this.healAmount);
    }

}
