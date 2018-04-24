package survivingit.items;

import survivingit.gameobjects.Creature;

public abstract class AttackEffect extends Effect {

    public static final EffectType EFFECT_TYPE = EffectType.ATTACK;

    public AttackEffect(Item source) {
        super(source, EFFECT_TYPE);
    }

    public abstract void attack(Creature attacker);

}
