package survivingit.containers;

import survivingit.items.ItemType;

public class ItemSlot {

    private final ItemSlotType itemSlotType;
    private ItemStack itemStack;

    public ItemSlot() {
        this.itemSlotType = ItemSlotType.ALL;
    }

    public ItemSlot(final ItemSlotType itemSlotType) {
        this.itemSlotType = itemSlotType;
    }

    public ItemType getItemType() {
        return this.itemStack.getItemType();
    }

    public ItemSlotType getItemSlotType() {
        return itemSlotType;
    }

    public boolean isEmpty() {
        return this.itemStack.isEmpty();
    }

    public ItemStack addItemStack(ItemStack itemStack) {
        return this.itemStack.addItemStack(itemStack);
    }
}
