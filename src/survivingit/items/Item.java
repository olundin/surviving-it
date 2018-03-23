package survivingit.items;

import survivingit.graphics.Sprite;

public abstract class Item {

    protected final String description;
    protected final Sprite sprite;
    protected final int stackSize;

    public Item(final String description, final Sprite sprite, final int stackSize) {
        this.description = description;
        this.sprite = sprite;
        this.stackSize = stackSize;
    }

    public String getDescription() {
        return description;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getStackSize() {
        return stackSize;
    }
}
