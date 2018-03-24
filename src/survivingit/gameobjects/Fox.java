package survivingit.gameobjects;

import survivingit.graphics.Sprite;
import survivingit.physics.Collider;

public class Fox extends Creature {

    public Fox(final double x, final double y) {
        super(x, y, Sprite.FOX, 20, 3.0);
        this.setCollider(new Collider(0.5, 0.75, 0.5, 0.75, false, this));
    }

}
