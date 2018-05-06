package survivingit.items;

/**
 * Effect class
 */
public abstract class Effect {

    private EffectType effectType;

    /**
     * Creates a new Effect object with the entered Item source and effectType.
     * @param effectType Effect type
     */
    protected Effect(EffectType effectType) {
       this.effectType = effectType;
    }

    /**
     * Returns the effectType of the effect.
     * @return the effect type
     */
    public EffectType getEffectType() {
        return this.effectType;
    }

}
