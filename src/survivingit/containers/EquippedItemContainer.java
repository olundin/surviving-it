package survivingit.containers;

import survivingit.gameobjects.Creature;
import survivingit.items.Item;

public class EquippedItemContainer extends ItemContainer {

    private int equippedIndex;

    public EquippedItemContainer(final int size) {
        super(size);
        this.equippedIndex = 0;
    }

    public Item getEquippedItem() {
        return getItemAt(equippedIndex);
    }

    public int getEquippedIndex() {
        return this.equippedIndex;
    }

    public void changeEquippedItem(int i) {
        this.equippedIndex = ((this.equippedIndex + i) + this.size) % this.size;
    }

    public void useEquippedItem(Creature creature) {
        Item equippedItem = this.getEquippedItem();
    }
}
