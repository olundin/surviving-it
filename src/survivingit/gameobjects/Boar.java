package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.physics.Collider;

/**
 * Class for the Boar animal, inherits from Animal.
 */
public class Boar extends Animal{

    /**
     * Creates a new Boar object with the entered x and y position.
     * @param x double val of the x position of the new Boar object.
     * @param y double val of the y position of the new Boar object.
     */
    public Boar(final double x, final double y) {
        super(
                x,
                y,
                new CreatureSprite(SpriteSheet.BOAR, 0, 0, 48, 48),
                20,
                2.5,
                1,
                2,
                10.0,
                1.0
        );

        this.setCollider(new Collider(-0.2, -0.35, 0.4, 0.35, false, this));
    }

}
