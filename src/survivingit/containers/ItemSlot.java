package survivingit.containers;

import survivingit.items.Item;
import survivingit.items.ItemFactory;
import survivingit.items.ItemType;

/**
 * Item slot class that can hold a single Item.
 *
 * An ItemSlot object can be of a specific ItemSlotType and can then only store items that fit that ItemSlotType, or of
 * a general ItemSlotType (ANY) which can hold any type of Item.
 *
 * @see Item
 */
public class ItemSlot {

    private Item item = null;

    /**
     * Creates a new empty ItemSlot object of itemSlotType ANY.
     */
    public ItemSlot() {
        this.clearItem();
    }

    /**
     * Clears the current itemSlot.
     */
    public void clearItem() {
        this.item = ItemFactory.NONE_ITEM;
    }

    /**
     * Returns the item of the itemSlot.
     * @return the item of the itemSlot.
     */
    public Item getItem() {
        return this.item;
    }

    /**
     * Sets the item of the itemSlot.
     * Throws an IllegalStateException if the itemSlot isn't empty.
     * @param item to be set for the itemSlot.
     */
    public void setItem(Item item) {
        if (this.item.getItemType() != ItemType.NONE) {
            throw new IllegalStateException("Adding item to None empty itemtype");
        }
        this.item = item;
    }

    /**
     * Returns a boolean if the itemSlot is empty.
     * @return if the itemSlot is empty.
     */
    public boolean isEmpty() {
        return this.item.getItemType() == ItemType.NONE;
    }
}
