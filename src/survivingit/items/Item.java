package survivingit.items;

import survivingit.graphics.Sprite;

public abstract class Item {

    protected final String description;
    protected final Sprite sprite;

    public Item(String description, Sprite sprite) {
        this.description = description;
        this.sprite = sprite;
    }

    public String getDescription() {
        return description;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
