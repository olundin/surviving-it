package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;

/**
 * Class for the Penguin animal, inherits from Animal.
 */
public class Penguin extends Animal {

    private CreatureSprite normal;
    private CreatureSprite sliding;

    /**
     * Creates a new Penguin object with the entered x and y position.
     * @param x double val of the x position of the new Penguin object.
     * @param y double val of the y position of the new Penguin object.
     */
    public Penguin(final double x, final double y) {
        super(
                x,
                y,
                new CreatureSprite(SpriteSheet.PENGUIN, 0, 0, 43, 40),
                20,
                2.0,
                2,
                5,
                10.0,
                1.0
        );

        this.normal = new CreatureSprite(SpriteSheet.PENGUIN, 0, 0, 48, 48);
        this.sliding = new CreatureSprite(SpriteSheet.PENGUIN, 144, 0, 48, 48);
        this.setCollider(new Collider(-0.2, -0.35, 0.4, 0.35, false, this));
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
        if (this.moveSpeed >= 3.0) {
            this.sprites = sliding;
        } else {
            this.sprites = normal;
        }
    }

}
