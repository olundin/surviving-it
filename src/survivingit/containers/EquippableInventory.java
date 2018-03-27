package survivingit.containers;

import survivingit.items.Item;

public class EquippableInventory {

    private Container container;
    private int equipIndex;

    public Item getEquippedItem() {
        return this.container.getItemAt(equipIndex);
    }
}
