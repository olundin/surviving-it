package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;

import java.util.Random;

public class Fox extends Animal {

    public Fox(final double x, final double y) {
        super(x,
              y,
              new CreatureSprite(SpriteSheet.FOX, 0, 0, 43, 40),
              20,
              2.0,
              1,
              1,
              10.0,
              1.0);

        this.setCollider(new Collider(-0.2, -0.35, 0.4, 0.35, false, this));
    }

}
