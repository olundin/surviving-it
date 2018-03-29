package survivingit.items;

import survivingit.graphics.Sprite;

public abstract class Tool extends Item implements UsableItem {

    protected static final int stackSize = 1;

    public Tool(String description, Sprite sprite) {
        super(description, sprite, stackSize);
    }


}
