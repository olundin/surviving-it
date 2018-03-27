package survivingit.containers;

import survivingit.items.Item;

/**
 * Created by AngusLothian on 2018-03-23.
 */
public abstract class Container {

    private ItemSlot[] itemSlots;
    private final int size;
    private int usedSlots;

    public Container(final int size) {
        this.itemSlots = new ItemSlot[size];
        this.size = size;
        this.usedSlots = 0;
    }

    public ItemSlot getItemAt(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal index entered");
        }
        return this.itemSlots[index];
    }

    public ItemStack addItemStackToFirstAvailableSpot(ItemStack itemStack) {
        ItemSlot existingSlot = this.getFirstExistingSlot(itemStack);
        if (existingSlot != null) {
            return existingSlot.addItemStack(itemStack);
        } else if (!this.isFull()) {
            return getFirstEmptySlot().addItemStack(itemStack);
        } else {
            throw new IllegalStateException("Container already full");
        }
    }

    private ItemSlot getFirstExistingSlot(ItemStack itemStack) {
        for (ItemSlot itemSlot : itemSlots) {
            if (!itemSlot.isEmpty() && itemSlot.getItemType() == itemStack.getItemType()) {
                return itemSlot;
            }
        }
        return null;
    }

    private ItemSlot getFirstEmptySlot() {
        for (ItemSlot itemSlot : itemSlots) {
            if (itemSlot.isEmpty()) {
                return itemSlot;
            }
        }
        return null;
    }

    private boolean isFull() {
        return this.usedSlots == this.size;
    }
}
