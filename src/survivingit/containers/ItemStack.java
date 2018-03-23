package survivingit.containers;

import survivingit.items.ItemType;

public class ItemStack {

    private ItemType itemType;
    private final int stackLimit;
    private int numberOfItems;

    public ItemStack(final ItemType itemType, final int stackLimit) {
        this.itemType = itemType;
        this.stackLimit = stackLimit;
        this.numberOfItems = 1;
    }

    /**
     * Adds the entered number of items to the itemStack and returns the number of items that couldn't fit in the stack.
     * @param amount of items to be added to the stack.
     * @return the amount of items that couldn't fit in the stack.
     */
    public int addItems(int amount) {
        int excessAmount = Math.max(this.numberOfItems + amount - this.stackLimit, 0);
        this.numberOfItems += amount - excessAmount;
        return excessAmount;
    }

    public ItemType getItemType() {
        return this.itemType;
    }

    public int getNumberOfItems() {
        return this.numberOfItems;
    }

    public boolean isFull() {
        return this.numberOfItems == this.stackLimit;
    }

    public boolean isEmpty() {
        return this.numberOfItems == 0;
    }
}
