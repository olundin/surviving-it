package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;
import survivingit.scene.Scene;

/**
 * The Yeti animal class, inherits from Animal.
 */
public class Yeti extends Animal {

    /**
     * Creates a new Yeti object with the entered position.
     * @param x double val of the x position of the new Yeti object.
     * @param y double val of the y position of the new Yeti object.
     */
    public Yeti(final double x, final double y, final Scene scene) {
        super(
                x,
                y,
                scene,
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
