package survivingit.containers;

import survivingit.items.Item;
import survivingit.items.ItemType;

/**
 * Created by AngusLothian on 2018-03-23.
 */
public class ItemContainer {

    protected ItemSlot[] itemSlots;
    protected final int size;
    protected int usedSlots;

    public ItemContainer(final int size) {
        this.itemSlots = new ItemSlot[size];
        this.size = size;
        this.usedSlots = 0;
        initialiseItemSlots();
    }

    private void initialiseItemSlots() {
        for (int i = 0; i < size; i++) {
            itemSlots[i] = new ItemSlot();
        }
    }

    public void addItemToFirstAvailable(Item item) {
        if (this.isFull()) {
            throw new IllegalStateException("Attempted add item to full inventory");
        }
        ItemSlot emptySlot = this.getFirstEmptySlot();
        emptySlot.setItem(item);
        usedSlots++;
    }

    public void addItemToIndex(Item item, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        if (!itemSlots[index].isEmpty()) {
            throw new IllegalStateException("Attempted to add item to none empty slot");
        }
        itemSlots[index].setItem(item);
        usedSlots++;
    }

    public Item popItemAt(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Index ouut of bounds");
        }
        if (itemSlots[index].isEmpty()) {
            throw new IllegalStateException("Attempt to remove item from empty itemslot");
        }
        Item item = itemSlots[index].getItem();
        itemSlots[index].clearItem();
        return item;
    }

    public void removeItemAt(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        if (itemSlots[index].isEmpty()) {
            throw new IllegalStateException("Attempt to remove item from empty itemslot");
        }
        itemSlots[index].clearItem();
    }

    public Item getItemAt(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return this.itemSlots[index].getItem();
    }

    public int getSize() {
        return this.size;
    }

    public  boolean isSlotEmpty(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return this.itemSlots[index].isEmpty();
    }

    public boolean hasSpace() {
        return this.usedSlots < this.size;
    }

    private ItemSlot getFirstEmptySlot() {
        for (ItemSlot itemSlot : itemSlots) {
            if (itemSlot.isEmpty()) {
                return itemSlot;
            }
        }
        return null;
    }

    private boolean isFull() {
        return this.usedSlots == this.size;
    }

}

