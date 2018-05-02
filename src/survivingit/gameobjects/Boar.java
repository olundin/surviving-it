package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;
import survivingit.scene.Scene;

/**
 * Class for the Boar animal, inherits all behaviour from Animal.
 *
 * @see Animal
 */
public class Boar extends Animal{

    private static final int MAX_HEALTH = 20;
    private static final double MOVE_SPEED = 2.5;
    private static final int DAMAGE = 1;
    private static final int ALPHA_LEVEL = 2;
    private static final double VIEW_DISTANCE = 10.0;
    private static final double RANGE = 1.0;

    private static final double COL_X = -0.2;
    private static final double COL_Y = -0.35;
    private static final double COL_WIDTH = 0.4;
    private static final double COL_HEIGHT = 0.35;

    /**
     * Creates a new Boar object with the entered x and y position.
     * @param x double val of the x position of the new Boar object.
     * @param y double val of the y position of the new Boar object.
     */
    public Boar(final double x, final double y, final Scene scene) {
        super(x,
              y,
              scene,
              new CreatureSprite(SpriteSheet.BOAR, 0, 0, 48, 48),
              MAX_HEALTH,
              MOVE_SPEED,
              DAMAGE,
              ALPHA_LEVEL,
              VIEW_DISTANCE,
              RANGE);

         this.setCollider(new Collider(COL_X, COL_Y, COL_WIDTH, COL_HEIGHT, false, this));
    }
}
