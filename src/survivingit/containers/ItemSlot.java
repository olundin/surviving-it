package survivingit.containers;

public class ItemSlot {

    private final ItemSlotType itemSlotType;
    private ItemStack itemStack;

    public ItemSlot() {
        this.itemSlotType = ItemSlotType.ALL;
    }

    public ItemSlot(final ItemSlotType itemSlotType) {
        this.itemSlotType = itemSlotType;
    }

    public ItemSlotType getItemSlotType() {
        return itemSlotType;
    }
}
