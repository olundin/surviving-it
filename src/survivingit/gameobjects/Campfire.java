package survivingit.gameobjects;

import survivingit.graphics.AnimatedSprite;
import survivingit.graphics.Sprite;
import survivingit.graphics.SpriteSheet;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
import survivingit.physics.Collider;

public class Campfire extends VisibleObject {

    private boolean ignited;
    private AnimatedSprite litSprite;
    private Sprite unlitSprite;

    public Campfire(double x, double y) {
        super(x, y, Sprite.CAMPFIRE);
        this.setCollider(new Collider(-0.4, -0.4, 0.8, 0.4, false, this));

        this.ignited = false;
        this.litSprite = new AnimatedSprite(SpriteSheet.CAMP_FIRE,
                                                 0,
                                                 0,
                                                 32,
                                                 32,
                                                 5,
                                                 1,
                                                 0.1);
        this.unlitSprite = Sprite.CAMPFIRE_UNLIT;
    }

    public void update(double dt) {
        if(ignited) {
            litSprite.update(dt);
            this.setSprite(litSprite.getSprite());
        } else {
            this.setSprite(unlitSprite);
        }
    }

    public void receiveMessage(Message msg) {
        MessageType type = msg.getType();
        int data = msg.getData();
        switch(type) {
            case ATTACK:
                this.ignited = !this.ignited;
                break;
            case ITEM:
                break;
            default:
                break;
        }
    }

}
