package survivingit.containers;

import survivingit.items.ItemType;

/**
 * Created by AngusLothian on 2018-03-24.
 */
public class PlayerInventory {

    private ItemContainer passiveStorage;
    private EquippableInventory equippableInventory;

    public PlayerInventory(int passiveStorageSize, int activeStorageSize) {
        this.passiveStorage = new ItemContainer(passiveStorageSize);
        this.equippableInventory = new EquippableInventory(activeStorageSize);
    }

    public void addItemType(ItemType itemType) {
        if (equippableInventory.canFitItemType(itemType)) {
            equippableInventory.addItemType(itemType);
        } else if (passiveStorage.canFitItemType(itemType)) {
            passiveStorage.addItemType(itemType);
        } else {
            System.out.println("Item doesn't fit");
        }
    }
}
