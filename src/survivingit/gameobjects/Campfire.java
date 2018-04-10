package survivingit.gameobjects;

import survivingit.graphics.AnimatedSprite;
import survivingit.graphics.Sprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;

public class Campfire extends GameVisibleObject {

    private AnimatedSprite animatedSprite;

    public Campfire(double x, double y) {
        super(x, y, Sprite.CAMPFIRE);
        this.setCollider(new Collider(-0.4, -0.4, 0.8, 0.4, false, this));

        this.animatedSprite = new AnimatedSprite(SpriteSheet.CAMP_FIRE,
                                                 0,
                                                 0,
                                                 32,
                                                 32,
                                                 5,
                                                 1,
                                                 0.1);
    }

    public void update(double dt) {
        animatedSprite.update(dt);
        this.setSprite(animatedSprite.getSprite());
    }

}