package survivingit.items;

import survivingit.gameobjects.Creature;

public class HealEffect extends AbstractEffect implements Usable {

    private int healAmount;

    public HealEffect(int healAmount, Item source) {
        super(source);
        if (healAmount < 0 ) {
            throw new IllegalArgumentException("Negative healamount not allowed");
	}
	this.healAmount = healAmount;
    }

    public void use(Creature creature) {
        creature.heal(this.healAmount);
    }

}
