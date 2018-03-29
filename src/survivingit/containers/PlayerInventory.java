package survivingit.containers;

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



}
