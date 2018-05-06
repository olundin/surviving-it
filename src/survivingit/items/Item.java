package survivingit.items;

import survivingit.graphics.Sprite;

import java.util.*;

/**
 * Class for items.
 * Items can be held by the player's inventory and have different effects.
 */
public class Item {

    private final ItemType itemType;

    // --Commented out by Inspection (2018-05-06 14:30):private final String description;
    private final Sprite sprite;
    // --Commented out by Inspection (2018-05-06 14:30):private final String name;
    private Map<EffectType, List<Effect>> effectsMap;

    /**
     * Creates a new Item object with the entered itemType, entered name, entered description and entered sprite.
     * @param itemType of the new Item object.
     * @param sprite of the new Item object.
     */
    public Item(final ItemType itemType, final Sprite sprite) {
        this.itemType = itemType;
        this.sprite = sprite;
        this.effectsMap = new EnumMap<>(EffectType.class);
    }

    /**
     * Adds the entered effect to the item.
     * @param effect to be added to the item.
     */
    public void addEffect(Effect effect) {
        EffectType effectType = effect.getEffectType();
        if (!hasEffectType(effectType)) {
            this.effectsMap.put(effectType, new ArrayList<>());
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
     * Returns the itemType of the item.
     * @return the itemType of the item.
     */
    public ItemType getItemType() {
        return this.itemType;
    }

}
