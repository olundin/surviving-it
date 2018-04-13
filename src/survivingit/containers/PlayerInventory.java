package survivingit.containers;

import survivingit.gameobjects.Creature;
import survivingit.items.Item;

/**
 * Created by AngusLothian on 2018-03-24.
 */
public class PlayerInventory {

    private EquippedItemContainer equippedItemContainer;
    private ItemContainer passiveStorage;

    public PlayerInventory(int passiveStorageSize, int equippableStorageSize) {
        if (passiveStorageSize < 0 || equippableStorageSize < 0) {
            throw new IllegalArgumentException("Negative passive or equippable storage size.");
        }
        this.passiveStorage = new ItemContainer(passiveStorageSize);
        this.equippedItemContainer = new EquippedItemContainer(equippableStorageSize);
    }

    public void addItemToFirstAvailable(Item item) {
        if (!this.hasSpace()) {
            throw new IllegalStateException("Attempt to add item to full inventory");
        } else if (equippedItemContainer.hasSpace()) {
            equippedItemContainer.addItemToFirstAvailable(item);
        } else if (passiveStorage.hasSpace()) {
            passiveStorage.addItemToFirstAvailable(item);
        }
    }

    public EquippedItemContainer getEquippedItemContainer() {
        return this.equippedItemContainer;
    }

    public ItemContainer getPassiveStorage() {
        return this.passiveStorage;
    }

    public boolean hasSpace() {
        return this.equippedItemContainer.hasSpace() || this.passiveStorage.hasSpace();
    }

    public void changeEquippedItem(int i) {
        this.equippedItemContainer.changeEquippedItem(i);
    }

    public void useEquippedItem(Creature creature) {
        this.equippedItemContainer.useEquippedItem(creature);
    }
}
