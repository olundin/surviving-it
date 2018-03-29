package survivingit.containers;

import survivingit.items.Item;
import survivingit.items.ItemType;

/**
 * Created by AngusLothian on 2018-03-23.
 */
public class ItemContainer {

    private ItemSlot[] itemSlots;
    private final int size;
    private int usedSlots;

    public ItemContainer(final int size) {
        this.itemSlots = new ItemSlot[size];
        this.size = size;
        this.usedSlots = 0;
    }

    public ItemStack addItemStackToFirstAvailableSpot(ItemStack itemStack) {
            ItemSlot existingSlot = this.getFirstSharedSlot(itemStack);
            if (existingSlot != null) {
                return existingSlot.addItemStack(itemStack);
            } else if (!this.isFull()) {
                return getFirstEmptySlot().addItemStack(itemStack);
            } else {
                throw new IllegalStateException("Container already full");
            }
        }


    public ItemType getItemTypeAt(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return this.itemSlots[index].getItemType();
    }

    public int getSize() {
        return this.size;
    }

    private ItemSlot getFirstEmptySlot() {
        for (ItemSlot itemSlot : itemSlots) {
            if (itemSlot.isEmpty()) {
                return itemSlot;
            }
        }
        return null;
    }

    private ItemSlot getFirstSharedSlot(ItemStack itemStack) {
        for (ItemSlot itemSlot : itemSlots) {
            if (!itemSlot.isEmpty() && itemSlot.getItemType() == itemStack.getItemType()) {
                return itemSlot;
            }
        }
        return null;
    }

    private boolean isFull() {
        return this.usedSlots == this.size;
    }
}
