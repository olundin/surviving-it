package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;

import java.util.Random;

public class Fox extends Animal {

    private static Random random = new Random();

    public Fox(final double x, final double y) {
        super(x,
              y,
              new CreatureSprite(SpriteSheet.FOXES, random.nextInt(8) * 129, 0, 43, 40),
              20,
              2.5,
              1,
              5.0);

        this.setCollider(new Collider(-0.2, -0.5, 0.4, 0.5, false, this));
    }

}
