package survivingit.containers;

import survivingit.items.Item;
import survivingit.items.ItemFactory;
import survivingit.items.ItemType;

public class ItemSlot {

    private final ItemSlotType itemSlotType;
    private Item item;


    public ItemSlot() {
        this.itemSlotType = ItemSlotType.ANY;
        this.item = ItemFactory.NONE_ITEM;
    }

    public ItemSlot(final ItemSlotType itemSlotType) {
        this.itemSlotType = itemSlotType;
        this.item = ItemFactory.NONE_ITEM;
    }

    public ItemType getItemType() {
        return this.item.getItemType();
    }

    public Item getItem() {
        return this.item;
    }

    public ItemSlotType getItemSlotType() {
        return itemSlotType;
    }

    public void setItem(Item item) {
        if (this.item.getItemType() != ItemType.NONE) {
            throw new IllegalStateException("Adding item to None empty itemtype");
        }
        this.item = item;
    }

    public boolean isEmpty() {
        return this.getItemType() == ItemType.NONE;
    }
}
