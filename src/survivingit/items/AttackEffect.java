package survivingit.items;

import survivingit.gameobjects.Creature;

/**
 * Effect for attacks
 */
public abstract class AttackEffect extends Effect {

    /**
     * The effectType of all objects of the AttackEffect class.
     */
    public static final EffectType EFFECT_TYPE = EffectType.ATTACK;

    /**
     * Creates a new AttackEffect object with the entered Item source.
     * @param source of the AttackEffect.
     */
    protected AttackEffect(Item source) {
        super(source, EFFECT_TYPE);
    }

    /**
     * Performs an attack from the entered attacker Creature.
     * @param attacker Creature who's attack method is used.
     */
    public abstract void attack(Creature attacker, double angle);

}
