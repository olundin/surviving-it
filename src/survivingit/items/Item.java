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

    /**
     * Creates a new Item object with the entered itemType, entered name, entered description and entered sprite.
     * @param itemType of the new Item object.
     * @param name of the new Item object.
     * @param description of the new Item object.
     * @param sprite of the new Item object.
     */
    public Item(final ItemType itemType, final String name, final String description, final Sprite sprite) {
        this.itemType = itemType;
        this.name = name;
        this.description = description;
        this.sprite = sprite;
        this.effectsMap = new HashMap<>();
    }

    /**
     * Adds the entered effect to the item.
     * @param effect to be added to the item.
     */
    public void addEffect(Effect effect) {
        EffectType effectType = effect.getEffectType();
        if (!hasEffectType(effectType)) {
            this.effectsMap.put(effectType, new ArrayList<Effect>());
        }
        this.effectsMap.get(effect.getEffectType()).add(effect);
    }

    /**
     * Returns a boolean if the item has any effects of the entered effectType.
     * @param effectType to check if the item has.
     * @return if the item has any effects of the entered effectType.
     */
    public boolean hasEffectType(EffectType effectType) {
        return this.effectsMap.containsKey(effectType);
    }

    /**
     * Returns the List of effects that the item has of the entered effectType.
     * @param effectType of the effects that are requested.
     * @return the List of effetcs that the item has of the entered effectType.
     */
    public List<Effect> getEffectsOfEffectType(EffectType effectType) {
        return this.effectsMap.get(effectType);
    }

    /**
     * Returns the sprite of the item.
     * @return the sprite of the item.
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Returns the name of the item.
     * @return the name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the itemType of the item.
     * @return the itemType of the item.
     */
    public ItemType getItemType() {
        return this.itemType;
    }

    /**
     * Returns the description of the item.
     * @return the description of the item.
     */
    public String getDescription() {
        return this.description;
    }

}
