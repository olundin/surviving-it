package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.Sprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;

public class Fox extends Creature {

    private double timer; // Used when randomizing direction;

    public Fox(final double x, final double y) {
        super(x,
              y,
              new CreatureSprite(SpriteSheet.FOXES, 0, 0, 48, 48),
              20,
              2.5);

        this.setCollider(new Collider(0.4, 0.6, 0.7, 0.9, false, this));
        this.timer = 0.0;
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        timer += dt;
        if(timer >= 2.0) {
            // Change direction every 5 seconds
            this.direction = Direction.randomDirection();
            this.timer = 0.0;
        }
    }
}
