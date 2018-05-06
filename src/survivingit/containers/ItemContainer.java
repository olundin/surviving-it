package survivingit.containers;

import survivingit.items.Item;

/**
 * Base Item container that can hold items.
 *
 * Consists of a collection of ItemSlots which can store individual Item objects. Contains methods for adding and
 * removing items from the container.
 *
 * @see ItemSlot
 */
public class ItemContainer {

    protected ItemSlot[] itemSlots;
    protected final int size;
    protected int usedSlots;

    /**
     * Creates a new ItemContainer object with the entered size.
     * @param size pf the new ItemContainer object.
     */
    public ItemContainer(final int size) {
        this.itemSlots = new ItemSlot[size];
        this.size = size;
        this.usedSlots = 0;
        initialiseItemSlots();
    }

    // Initialises the itemSlots in the itemContainer to new empty itemSlot objects.
    private void initialiseItemSlots() {
        for (int i = 0; i < size; i++) {
            itemSlots[i] = new ItemSlot();
        }
    }

    /**
     * Adds the entered item to the first available spot in the ItemContainer.
     * If there is no available spots then an IllegalStateException is thrown as this should have been checked before.
     * @param item to add to the first available spot.
     */
    public void addItemToFirstAvailable(Item item) {
        if (this.isFull()) {
            throw new IllegalStateException("Attempted add item to full inventory");
        }

        ItemSlot emptySlot = this.getFirstEmptySlot();
        if(emptySlot != null) {
            emptySlot.setItem(item);
            usedSlots++;
        }
    }

    /**
     * Returns the item at the entered index in the ItemContainer.
     * Throws an IllegalArgumentException if the entered index is out of bounds.
     * @param index of the item to be returned.
     * @return the item at the entered index in the ItemContainer.
     */
    public Item getItemAt(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return this.itemSlots[index].getItem();
    }

    /**
     * Returns the size of the ItemContainer.
     * @return int value the size of the ItemContainer.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns a boolean if there is an empty itemSlot in the ItemContainer.
     * @return if there is an empty itemSlot in the ItemContainer.
     */
    public boolean hasSpace() {
        return this.usedSlots < this.size;
    }

    // Returns the first empty itemSlot, if there is none it returns null.
    private ItemSlot getFirstEmptySlot() {
        for (ItemSlot itemSlot : itemSlots) {
            if (itemSlot.isEmpty()) {
                return itemSlot;
            }
        }
        return null;
    }

    // Returns if the itemContainer is full.
    private boolean isFull() {
        return this.usedSlots == this.size;
    }

}

