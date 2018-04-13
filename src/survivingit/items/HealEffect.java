package survivingit.items;

import survivingit.gameobjects.Creature;

public class HealEffect extends AbstractEffect implements Usable {

    private static EffectType EFFECT_TYPE = EffectType.HEAL;

    private int healAmount;

    public HealEffect(int healAmount, Item source) {
        super(source, EFFECT_TYPE);
        if (healAmount < 0 ) {
            throw new IllegalArgumentException("Negative heal amount not allowed");
	    }
	    this.healAmount = healAmount;
    }

    public void use(Creature creature) {
        creature.heal(this.healAmount);
    }

}
