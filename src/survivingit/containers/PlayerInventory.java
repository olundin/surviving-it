package survivingit.containers;

import survivingit.gameobjects.Creature;
import survivingit.items.Item;

/**
 * Created by AngusLothian on 2018-03-24.
 */
public class PlayerInventory {

    private EquippedItemContainer equippedItemContainer;
    private ItemContainer passiveStorage;

    /**
     * Creates a new PlayerInventory object with the entered passiveStorageSize and the entered equippableStorageSize.
     * Throws an IllegalArgumentException if either the entered passiveStorageSize or equippableStorageSize is negative.
     * @param passiveStorageSize int value of the size of the passiveStorage of the new PlayerInventory object.
     * @param equippableStorageSize in value of the size of the equippableStorage of the new PlayerInventory object.
     */
    public PlayerInventory(int passiveStorageSize, int equippableStorageSize) {
        if (passiveStorageSize < 0 || equippableStorageSize < 0) {
            throw new IllegalArgumentException("Negative passive or equippable storage size.");
        }
        this.passiveStorage = new ItemContainer(passiveStorageSize);
        this.equippedItemContainer = new EquippedItemContainer(equippableStorageSize);
    }

    /**
     * Adds the entered item to the first available spot in the equippableStorage if there is one, otherwise it adds
     * the item to the first available spot in the passiveStorage.
     * Throws an IllegalStateException if there is no space available to add the item to.
     * @param item to add to the PlayerInventory.
     */
    public void addItemToFirstAvailable(Item item) {
        if (!this.hasSpace()) {
            throw new IllegalStateException("Attempt to add item to full inventory");
        } else if (equippedItemContainer.hasSpace()) {
            equippedItemContainer.addItemToFirstAvailable(item);
        } else if (passiveStorage.hasSpace()) {
            passiveStorage.addItemToFirstAvailable(item);
        }
    }

    /**
     * Returns the equippedItemContainer of the PlayerInventory.
     * @return the equippedItemContainer of the PlayerInventory.
     */
    public EquippedItemContainer getEquippedItemContainer() {
        return this.equippedItemContainer;
    }

    /**
     * Returns the passiveStorage of the PlayerInventory.
     * @return the passiveStorage of the PlayerInventory.
     */
    public ItemContainer getPassiveStorage() {
        return this.passiveStorage;
    }

    /**
     * Returns a boolean if the playerInventory has space in either the passiveStorageContainer or the
     * equippedItemContainer.
     * @return if the playerInventory has space in either the passiveStorageContainer or the equippedItemContainer.
     */
    public boolean hasSpace() {
        return this.equippedItemContainer.hasSpace() || this.passiveStorage.hasSpace();
    }

    /**
     * Changes the equipped item index of the playerInvtory's equippedItemContainer.
     * @param i number of indexes to change the equipped item index.
     */
    public void changeEquippedItem(int i) {
        this.equippedItemContainer.changeEquippedItem(i);
    }

    /**
     * Uses the equipped item of the equippedItemContainer on the entered creature.
     * @param creature to use the equipped item on.
     */
    public void useEquippedItem(Creature creature) {
        this.equippedItemContainer.useEquippedItem(creature);
    }

    /**
     * Reaturns a boolean if the equipped item has a weapon(attack)component.
     * @return if the equipped item is a weapon.
     */
    public boolean isCarryingWeapon() {
        return this.equippedItemContainer.isCarryingWeapon();
    }
}
