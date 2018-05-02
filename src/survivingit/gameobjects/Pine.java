package survivingit.gameobjects;

import survivingit.graphics.Sprite;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
import survivingit.physics.Collider;

/**
 * Class for pine trees, inherits from Visible object.
 */
public class Pine extends VisibleObject {

    /**
     * Creates a new Pine object with the entered x and y position.
     * @param x double val of the x position of the new Pine object.
     * @param y double val of the y postion of the new Pine object.
     */
    public Pine(double x, double y) {
        super(x, y, Sprite.PINE);
        this.setCollider(new Collider(-0.3, -1.0, 0.6, 1.0, false, this));
    }

    /**
     * Receives and reacts to the entered message.
     *
     * At the moment however the Pine does nothing when receiving a message.
     * @param msg Message for the pine tree to react to.
     */
    @Override
    public void receiveMessage(Message msg) {
        MessageType type = msg.getType();
        int data = msg.getData();
        switch(type) {
            default:
                break;
        }
    }

}
