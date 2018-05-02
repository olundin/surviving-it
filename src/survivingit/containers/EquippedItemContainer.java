package survivingit.containers;

import survivingit.gameobjects.Creature;
import survivingit.items.Effect;
import survivingit.items.EffectType;
import survivingit.items.Item;
import survivingit.items.UsableEffect;

/**
 * Item container that can be equipped
 *
 * @see ItemContainer
 */
public class EquippedItemContainer extends ItemContainer {

    private int equippedIndex;

    /**
     * Creates a new EquippedItemContainer object with the entered size.
     * @param size of the new EquippedItemContainer.
     */
    public EquippedItemContainer(final int size) {
        super(size);
        this.equippedIndex = 0;
    }

    /**
     * Returns the item at the equipped item index
     * @return the equipped item.
     */
    public Item getEquippedItem() {
        return getItemAt(equippedIndex);
    }

    /**
     * Returns the equipped item index.
     * @return the equipped item index.
     */
    public int getEquippedIndex() {
        return this.equippedIndex;
    }

    /**
     * Changes the equipped item index with the entered number of steps.
     * @param i number of steps to change item
     */
    public void changeEquippedItem(int i) {
        this.equippedIndex = ((this.equippedIndex + i) + this.size) % this.size;
    }

    /**
     * Uses the equipped item on the creature carrying it.
     * @param creature carrying the item
     */
    public void useEquippedItem(Creature creature) {
        Item equippedItem = this.getEquippedItem();
        if (equippedItem.hasEffectType(EffectType.USABLE)) {
            for (Effect effect : equippedItem.getEffectsOfEffectType(EffectType.USABLE)) {
                ((UsableEffect) effect).use(creature);
            }
        }
    }

    /**
     * Reaturns a boolean if the equipped item has a weapon(performAttack)component.
     * @return if the equipped item is a weapon.
     */
    public boolean isCarryingWeapon() {
        return this.getEquippedItem().hasEffectType(EffectType.ATTACK);
    }


}
