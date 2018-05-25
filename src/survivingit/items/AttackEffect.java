package survivingit.items;

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
     */
    protected AttackEffect() {
        super(EFFECT_TYPE);
    }

}
