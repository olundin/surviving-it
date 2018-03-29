package survivingit.items;

import survivingit.graphics.Sprite;

public abstract class Weapon extends Tool {

    private final int damage;

    public Weapon(final int damage, final String description, final Sprite sprite) {
        super(description, sprite);
        this.damage = damage;
    }



}
