package survivingit.containers;

import survivingit.items.Item;

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

    public void addItemStack(ItemStack itemStack) {
        ItemSlot firstSharedSlot = this.getFirstSharedSlot(itemStack);
        if (this.isFull()) {
            throw new IllegalStateException("ItemContainer already full.");
        }
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
            if (!itemSlot.isEmpty() && itemSlot.get)
        }
    }

    private boolean isFull() {
        return this.usedSlots == this.size;
    }

    public Item getItemAt(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Index out of bounds.");
        }

        

    }
}
