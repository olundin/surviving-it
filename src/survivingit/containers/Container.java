package survivingit.containers;

import survivingit.items.Item;

/**
 * Created by AngusLothian on 2018-03-23.
 */
public abstract class Container {

    private ItemSlot[] itemSlots;
    private final int size;
    private int usedSlots;

    public Container(final int size) {
        this.itemSlots = new Item[size];
        this.size = size;
        this.usedSlots = 0;
    }

    public void addItem(Item item) {
        if (this.isFull()) {
            throw new IllegalStateException("Container already full.");
        }
        itemSlots[this.getFirstEmptyIndex()] =
    }

    private int getFirstEmptyIndex() {
        return 0;
    }

    private boolean isFull() {
        return this.usedSlots == this.size;
    }
}
