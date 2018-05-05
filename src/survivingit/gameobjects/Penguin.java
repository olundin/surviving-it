package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.scene.Scene;

/**
 * Class for the Penguin animal, inherits almost all behaviour from Animal.
 *
 * Contains special sliding behaviour which changes the rendering of the Penguin if it reaches a high enough speed.
 *
 * @see Animal
 */
public class Penguin extends Animal {

    private static final int MAX_HEALTH = 20;
    private static final double MOVE_SPEED = 2;
    private static final double SLIDE_MOVE_SPEED = 3.0;
    private static final int DAMAGE = 2;
    private static final int ALPHA_LEVEL = 5;
    private static final double VIEW_DISTANCE = 10.0;
    private static final double RANGE = 1.0;

    private static final double COL_X = -0.2;
    private static final double COL_Y = -0.35;
    private static final double COL_WIDTH = 0.4;
    private static final double COL_HEIGHT = 0.35;

    private CreatureSprite normal;
    private CreatureSprite sliding;

    /**
     * Creates a new Penguin object with the entered x and y position.
     * @param x double val of the x position of the new Penguin object.
     * @param y double val of the y position of the new Penguin object.
     */
    public Penguin(final double x, final double y, final Scene scene) {
        super(x,
               y,
               scene,
               new CreatureSprite(SpriteSheet.PENGUIN, 0, 0, 48, 48),
               MAX_HEALTH,
               MOVE_SPEED,
               DAMAGE,
               ALPHA_LEVEL,
               VIEW_DISTANCE,
               RANGE);

        this.setCollider(new Collider(COL_X, COL_Y, COL_WIDTH, COL_HEIGHT, false, this));

        this.normal = new CreatureSprite(SpriteSheet.PENGUIN, 0, 0, 48, 48);
        this.sliding = new CreatureSprite(SpriteSheet.PENGUIN, 144, 0, 48, 48);
    }

    /**
     * Updates the penguin with the entered amount of passed time.
     *
     * Changes the sprites of the penguin depending on if it is moving fast or not.
     * @param dt double of the amount of time to update the animal with.
     */
    @Override
    public void update(double dt) {
        super.update(dt);
        // Set sprite to sliding if movespeed is over certain value
        if (this.moveSpeed >= SLIDE_MOVE_SPEED) {
            this.sprites = sliding;
        } else {
            this.sprites = normal;
        }
    }

}
