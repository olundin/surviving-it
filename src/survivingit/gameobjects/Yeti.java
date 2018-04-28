package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;

public class Yeti extends Animal {

    public Yeti(final double x, final double y) {
        super(
                x,
                y,
                new CreatureSprite(SpriteSheet.MEME_MAN, 0, 0, 85, 115),
                1000,
                10,
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                50.0,
                1.0
        );

        this.setCollider(new Collider(-0.2, -0.35, 0.4, 0.35, true, this));
    }

}
