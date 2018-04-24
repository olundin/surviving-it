package survivingit.items;

import survivingit.gameobjects.Creature;

public class HealEffect extends UsableEffect {

    private int healAmount;

    public HealEffect(int healAmount, Item source) {
        super(source);
        if (healAmount < 0 ) {
            throw new IllegalArgumentException("Negative heal amount not allowed");
	    }
	    this.healAmount = healAmount;
    }

    public void use(Creature user) {
        user.heal(this.healAmount);
    }

}
