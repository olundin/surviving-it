package survivingit.items;

import survivingit.graphics.Sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {

    private final ItemType itemType;

    private final String description;
    private final Sprite sprite;
    private final String name;
    private Map<EffectType, List<Effect>> effectsMap;

    public Item(final ItemType itemType, final String name, final String description, final Sprite sprite) {
        this.itemType = itemType;
        this.name = name;
        this.description = description;
        this.sprite = sprite;
        this.effectsMap = new HashMap<>();
    }

    public void addEffect(Effect effect) {
        EffectType effectType = effect.getEffectType();
        if (!hasEffectType(effectType)) {
            this.effectsMap.put(effectType, new ArrayList<>());
        }
        this.effectsMap.get(effect.getEffectType()).add(effect);
    }

    public boolean hasEffectType(EffectType effectType) {
        return this.effectsMap.containsKey(effectType);
    }

    public List<Effect> getEffectsOfEffectType(EffectType effectType) {
        return this.effectsMap.get(effectType);
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

    public String getDescription() {
        return this.description;
    }
}
