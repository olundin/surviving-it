package survivingit.items;

import survivingit.graphics.Sprite;

public abstract class Weapon extends Tool implements UsableItem {

    public Weapon(String description, Sprite sprite) {
        super(description, sprite);
    }



}
