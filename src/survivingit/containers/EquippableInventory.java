package survivingit.containers;

import survivingit.items.ItemType;

public class EquippableInventory {

    private ItemContainer container;
    private int equipIndex;

    public ItemType getEquippedItemType() {
        return this.container.getItemTypeAt(equipIndex);
    }
}
