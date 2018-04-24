package survivingit.items;

import survivingit.gameobjects.Creature;

/**
 * Created by AngusLothian on 2018-04-14.
 */
public abstract class UsableEffect extends Effect {

    public static final EffectType EFFECT_TYPE = EffectType.USABLE;

    public UsableEffect(Item source) {
        super(source, EFFECT_TYPE);
    }

    public abstract void use(Creature user);
}
