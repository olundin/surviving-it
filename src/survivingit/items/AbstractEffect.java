package survivingit.items;

public abstract class AbstractEffect {

    private EffectType effectType;

    private final Item source;

    public AbstractEffect(Item source, EffectType effectType) {
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
