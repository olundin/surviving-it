package survivingit.containers;

import survivingit.items.Item;

/**
 * Created by AngusLothian on 2018-03-24.
 */
public class PlayerInventory {

    private EquippableInventory equippableInventory;
    private ItemContainer passiveStorage;

    public PlayerInventory(int passiveStorageSize, int equippableStorageSize) {
        if (passiveStorageSize < 0 || equippableStorageSize < 0) {
            throw new IllegalArgumentException("Negative passive or equippable storage size.");
        }
        this.passiveStorage = new ItemContainer(passiveStorageSize);
        this.equippableInventory = new EquippableInventory(equippableStorageSize);
    }

    public void addItemToFirstAvailable(Item item) {
        if (!this.hasSpace()) {
            throw new IllegalStateException("Attempt to add item to full inventory");
        } else if (equippableInventory.hasSpace()) {
            equippableInventory.addItemToFirstAvailable(item);
        } else if (passiveStorage.hasSpace()) {
            passiveStorage.addItemToFirstAvailable(item);
        }
    }

    public boolean hasSpace() {
        return this.equippableInventory.hasSpace() || this.passiveStorage.hasSpace();
    }
}
