package survivingit.containers;

import survivingit.items.Item;

/**
 * Created by AngusLothian on 2018-03-24.
 */
public class PlayerActiveInventory {

    private ItemContainer itemContainer;
    private int equippedIndex;

    public PlayerActiveInventory(final int size) {
        this.itemContainer = new ItemContainer(size);
        this.equippedIndex = 0;
    }

    public Item getEquippedItem() {
        return itemContainer.getItemAt(equippedIndex);
    }

    public int getSize() {
        return this.itemContainer.getSize();
    }

}
