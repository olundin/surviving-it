package survivingit.items;

import survivingit.graphics.Sprite;

public abstract class Item {

    protected final String description;
    protected final Sprite sprite;
    protected final boolean stackable;

    public Item(String description, Sprite sprite, boolean stackable) {
        this.description = description;
        this.sprite = sprite;
        this.stackable = stackable;
    }

    public String getDescription() {
        return description;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isStackable() {
        return stackable;
    }
}
