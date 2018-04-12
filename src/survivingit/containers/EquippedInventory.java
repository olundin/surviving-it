package survivingit.containers;

import survivingit.items.Item;

public class EquippedInventory {

    private ItemContainer container;
    private int equipIndex;

    public EquippedInventory(int size) {
        this.container = new ItemContainer(size);
        this.equipIndex = 0;
    }

    public boolean hasSpace() {
        return this.container.hasSpace();
    }

    public void addItemToFirstAvailable(Item item) {
        this.container.addItemToFirstAvailable(item);
    }

    public ItemContainer getContainer () {
        return this.container;
    }
}
