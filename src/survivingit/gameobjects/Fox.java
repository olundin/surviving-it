package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.Sprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;

import java.util.Random;

public class Fox extends Creature {

    private double timer; // Used when randomizing direction;
    private Random random;

    public Fox(final double x, final double y) {
        super(x,
              y,
              new CreatureSprite(SpriteSheet.FOXES, 0, 0, 48, 48),
              20,
              2.5);

        this.setCollider(new Collider(0.4, 0.6, 0.7, 0.9, false, this));
        this.timer = 0.0;
        this.random = new Random();
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
