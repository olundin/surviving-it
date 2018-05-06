package survivingit.gameobjects;

import survivingit.graphics.Sprite;
import survivingit.messaging.Message;
import survivingit.scene.Scene;

/**
 * Class for pine trees, inherits from Visible object.
 *
 * Contains no special behaviour at the moment, is more of a decoration in the game.
 *
 * @see VisibleObject
 */
public class Pine extends VisibleObject {

    private static final double COL_X = -0.3;
    private static final double COL_Y = -1.0;
    private static final double COL_WIDTH = 0.6;
    private static final double COL_HEIGHT = 1.0;

    /**
     * Creates a new Pine object with the entered x and y position.
     * @param x double val of the x position of the new Pine object.
     * @param y double val of the y postion of the new Pine object.
     */
    public Pine(double x, double y, Scene scene) {
        super(x, y, scene, Sprite.PINE);
        this.setCollider(new Collider(COL_X, COL_Y, COL_WIDTH, COL_HEIGHT, false, this));
    }

    @Override
    public void update(double dt) {}

    /**
     * Receives and reacts to the entered message.
     *
     * At the moment however the Pine does nothing when receiving a message.
     * @param msg Message for the pine tree to react to.
     */
    @Override
    public void receiveMessage(Message msg) { }

}
