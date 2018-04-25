package survivingit.gameobjects;

import survivingit.graphics.Sprite;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
import survivingit.physics.Collider;

public class Pine extends VisibleObject {

    public Pine(double x, double y) {
        super(x, y, Sprite.PINE);
        this.setCollider(new Collider(-0.3, -1.0, 0.6, 1.0, false, this));
    }

    public void receiveMessage(Message msg) {
        MessageType type = msg.getType();
        int data = msg.getData();
        switch(type) {
            default:
                break;
        }
    }

}
