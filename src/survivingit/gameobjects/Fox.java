package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.Sprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;

import java.util.Random;

public class Fox extends Creature {

    private double timer; // Used when randomizing direction;
    private static Random random = new Random();

    public Fox(final double x, final double y) {
        super(x,
              y,
              new CreatureSprite(SpriteSheet.FOXES, random.nextInt(8) * 129, 0, 43, 40),
              20,
              2.5);

        this.setCollider(new Collider(-0.2, 0, 0.4, 0.5, false, this));
        this.timer = 0.0;
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        // Randomize a direction. Possibility of doing so increases over time
        timer += dt;
        if(random.nextInt((int)timer + 1) >= 5) {
            this.direction = Direction.randomDirection();
            this.timer = 0.0;
        }
    }
}
