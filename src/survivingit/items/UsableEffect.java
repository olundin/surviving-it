package survivingit.items;

import survivingit.gameobjects.Creature;

/**
 * Created by AngusLothian on 2018-04-14.
 */
public abstract class UsableEffect extends Effect {

    private static final EffectType EFFECT_TYPE = EffectType.USABLE;

    /**
     * Creates a new UsableEffect object with the entered source Item.
     */
    protected UsableEffect() {
        super(EFFECT_TYPE);
    }

    /**
     * Uses UsableEffect on/from the entered user Creature.
     * @param user Creature.
     */
    public abstract void use(Creature user);
}
