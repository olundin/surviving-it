package survivingit.containers;

import survivingit.items.ItemType;

public class EquippableInventory {

    private ItemContainer container;
    private int equipIndex;

    public EquippableInventory(int size) {
        this.container = new ItemContainer(size);
        this.equipIndex = 0;
    }

    public ItemType getEquippedItemType() {
        return this.container.getItemTypeAt(equipIndex);
    }
}
