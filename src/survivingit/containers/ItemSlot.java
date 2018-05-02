package survivingit.containers;

import survivingit.items.Item;
import survivingit.items.ItemFactory;
import survivingit.items.ItemType;

/**
 * Item slot class.
 * Can fit one item.
 */
public class ItemSlot {

    private final ItemSlotType itemSlotType;
    private Item item;

    /**
     * Creates a new empty ItemSlot object of itemSlotType ANY.
     */
    public ItemSlot() {
        this.itemSlotType = ItemSlotType.ANY;
        this.clearItem();
    }

    /**
     * Creates a new empty ItemSlot object of the entered itemSlotType.
     * @param itemSlotType of tbe new ItemSlot object.
     */
    public ItemSlot(final ItemSlotType itemSlotType) {
        this.itemSlotType = itemSlotType;
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
     * Returns the itemSlotType of the itemSlot.
     * @return the itemSlotType of the itemSlot.
     */
    public ItemSlotType getItemSlotType() {
        return itemSlotType;
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
