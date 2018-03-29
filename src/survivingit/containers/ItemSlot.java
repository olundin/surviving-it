package survivingit.containers;

import survivingit.items.Item;
import survivingit.items.ItemType;

public class ItemSlot {

    private final ItemSlotType itemSlotType;
    private ItemStack itemStack;

    public ItemSlot() {
        this.itemSlotType = ItemSlotType.ANY;
        this.itemStack = new ItemStack(ItemType.NONE, 0);
    }

    public ItemSlot(final ItemSlotType itemSlotType) {
        this.itemSlotType = itemSlotType;
        this.itemStack = new ItemStack(ItemType.NONE, 0);
    }

    public ItemType getItemType() {
        return this.itemStack.getItemType();
    }

    public ItemSlotType getItemSlotType() {
        return itemSlotType;
    }

    public void setItemStack(ItemStack itemStack) {
        if (this.itemStack.getItemType() != ItemType.NONE) {
            throw new IllegalStateException("Cannot override ItemType that isn't NONE");
        }
        this.itemStack = itemStack;
    }

    public boolean isEmpty() {
        return this.itemStack.isEmpty();
    }

    public ItemStack addItemStack(ItemStack itemStack) {
        return this.itemStack.addItemStack(itemStack);
    }
}
