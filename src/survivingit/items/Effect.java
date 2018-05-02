package survivingit.items;

/**
 * Effect class
 */
public abstract class Effect {

    private EffectType effectType;

    private final Item source;

    /**
     * Creates a new Effect object with the entered Item source and effectType.
     * @param source Effect source
     * @param effectType Effect type
     */
    protected Effect(Item source, EffectType effectType) {
       this.source = source;
       this.effectType = effectType;
    }

    /**
     * Returns the source item of the effect.
     * @return the source item of the effect.
     */
    public Item getSource() {
       return this.source;
    }

    /**
     * Returns the effectType of the effect.
     * @return the effect type
     */
    public EffectType getEffectType() {
        return this.effectType;
    }

}
