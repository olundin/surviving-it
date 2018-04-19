package survivingit.containers;

import survivingit.items.ItemType;

@Deprecated
public class ItemStack {

    private final ItemType itemType;
    private final int stackLimit;
    private int numberOfItems;

    public ItemStack(final ItemType itemType, final int stackLimit) {
        this.itemType = itemType;
        this.stackLimit = stackLimit;
        this.numberOfItems = 1;
    }

    public ItemStack(final ItemType itemType, final int stackLimit, final int numberOfItems) {
        this.itemType = itemType;
        this.stackLimit = stackLimit;
        this.numberOfItems = numberOfItems;
    }

    /**
     * Adds the entered number of items to the itemStack and returns the number of items that couldn't fit in the stack.
     * @param amount of items to be added to the stack.
     */
    public void addAmount(int amount) {
        if (amount < 0 || this.getSizeLeft() < amount) {
            throw new IllegalArgumentException("Illegal amount entered");
        }
        this.numberOfItems += amount;
    }

    public void removeAmount(int amount) {
        if (amount < 0 || this.getNumberOfItems() < amount) {
            throw new IllegalArgumentException("Illegal amount entered");
        }
        this.numberOfItems -= amount;
    }

    public ItemStack addItemStack(ItemStack itemStackToAdd) {
        if (this.itemType != itemStackToAdd.getItemType()) {
            return itemStackToAdd;
        } else if (itemStackToAdd.getSizeLeft() < this.getSizeLeft()){
            this.addAmount(itemStackToAdd.getSizeLeft());
            return new ItemStack(ItemType.NONE, 0);
        } else {
            int amountToAdd = this.getSizeLeft();
            this.addAmount(amountToAdd);
            itemStackToAdd.removeAmount(amountToAdd);
            return itemStackToAdd;
        }
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
        return this.itemType == ItemType.NONE;
    }

    private int getSizeLeft() {
        return this.stackLimit - this.numberOfItems;
    }
}
