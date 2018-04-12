package survivingit.containers;

import survivingit.items.Item;

/**
 * Created by AngusLothian on 2018-03-24.
 */
public class PlayerInventory {

    private EquippedInventory equippedInventory;
    private ItemContainer passiveStorage;

    public PlayerInventory(int passiveStorageSize, int equippableStorageSize) {
        if (passiveStorageSize < 0 || equippableStorageSize < 0) {
            throw new IllegalArgumentException("Negative passive or equippable storage size.");
        }
        this.passiveStorage = new ItemContainer(passiveStorageSize);
        this.equippedInventory = new EquippedInventory(equippableStorageSize);
    }

    public void addItemToFirstAvailable(Item item) {
        if (!this.hasSpace()) {
            throw new IllegalStateException("Attempt to add item to full inventory");
        } else if (equippedInventory.hasSpace()) {
            equippedInventory.addItemToFirstAvailable(item);
        } else if (passiveStorage.hasSpace()) {
            passiveStorage.addItemToFirstAvailable(item);
        }
    }

    public EquippedInventory getEquippedInventory() {
        return this.equippedInventory;
    }

    public ItemContainer getPassiveStorage() {
        return this.passiveStorage;
    }

    public boolean hasSpace() {
        return this.equippedInventory.hasSpace() || this.passiveStorage.hasSpace();
    }
}
