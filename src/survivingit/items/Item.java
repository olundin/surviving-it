package survivingit.items;

import survivingit.gameobjects.Updateable;
import survivingit.graphics.Sprite;

import java.util.HashMap;
import java.util.Map;

public class Item {

    private final ItemType itemType;

    private final String description;
    private final Sprite sprite;
    private final String name;
    private Map<EffectType, AbstractEffect> effects;

    public Item(final ItemType itemType, final String name, final String description, final Sprite sprite) {
        this.itemType = itemType;
        this.name = name;
        this.description = description;
        this.sprite = sprite;
        this.effects = new HashMap<>();
    }

    public void addEffect(AbstractEffect effect) {
        this.effects.put(effect.getEffectType(), effect);
    }

    public boolean hasEffectType(EffectType effectType) {
        return this.effects.containsKey(effectType);
    }

    public Usable getUsableEffect() {
        for (EffectType effectType : this.effects.keySet()) {
            AbstractEffect effect = this.effects.get(effectType);
            if (effect instanceof Usable) {
                return (Usable) effect;
            }
        }
        return null;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }

    public ItemType getItemType() {
        return this.itemType;
    }
}
