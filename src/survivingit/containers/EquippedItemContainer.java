package survivingit.containers;

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

}
