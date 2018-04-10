package survivingit.containers;

import survivingit.items.Item;
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

    public boolean hasSpace() {
        return this.container.hasSpace();
    }

    public void addItemToFirstAvailable(Item item) {
        this.container.addItemToFirstAvailable(item);
    }
}
