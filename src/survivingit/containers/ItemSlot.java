package survivingit.containers;

import survivingit.items.ItemType;

public class ItemSlot {

    private final ItemSlotType itemSlotType;
    private ItemStack itemStack;

    public ItemSlot() {
        this.itemSlotType = ItemSlotType.ANY;
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

<<<<<<< HEAD
=======
    public ItemStack addItemStack(ItemStack itemStack) {
        return this.itemStack.addItemStack(itemStack);
    }
>>>>>>> 20ea7516ec0cf62b80f760a5d58596d09ba630c6
}
