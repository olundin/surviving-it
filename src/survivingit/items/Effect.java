package survivingit.items;

public abstract class Effect {

    private EffectType effectType;

    private final Item source;

    public Effect(Item source, EffectType effectType) {
       this.source = source;
       this.effectType = effectType;
    }

    public Item getSource() {
       return this.source;
    }

    public EffectType getEffectType() {
        return this.effectType;
    }

}
