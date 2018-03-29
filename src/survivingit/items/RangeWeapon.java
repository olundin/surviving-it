package survivingit.items;

import survivingit.gameobjects.Creature;
import survivingit.graphics.Sprite;

public class RangeWeapon extends Weapon {

    public RangeWeapon(int damage, final String description, final Sprite sprite) {
        super(damage, description, sprite);
    }

    public void use(Creature creature) {}

}
