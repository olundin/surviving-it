package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;
import survivingit.scene.Scene;

/**
 * The Yeti animal class, inherits all behaviour from Animal.
 *
 * @see Animal
 */
public class Yeti extends Animal {

    private static final int MAX_HEALTH = 20;
    private static final double MOVE_SPEED = 10;
    private static final int DAMAGE = 25;
    private static final int ALPHA_LEVEL = Integer.MAX_VALUE;
    private static final double VIEW_DISTANCE = 25.0;
    private static final double RANGE = 20;

    private static final double COL_X = -0.2;
    private static final double COL_Y = -0.35;
    private static final double COL_WIDTH = 0.4;
    private static final double COL_HEIGHT = 0.35;

    /**
     * Creates a new Yeti object with the entered position.
     * @param x double val of the x position of the new Yeti object.
     * @param y double val of the y position of the new Yeti object.
     */
    public Yeti(final double x, final double y, final Scene scene) {
        super( x,
               y,
               scene,
               new CreatureSprite(SpriteSheet.MEME_MAN, 0, 0, 85, 115),
               MAX_HEALTH,
               MOVE_SPEED,
               DAMAGE,
               ALPHA_LEVEL,
               VIEW_DISTANCE,
               RANGE);

        this.setCollider(new Collider(COL_X, COL_Y, COL_WIDTH, COL_HEIGHT, true, this));
    }

}
