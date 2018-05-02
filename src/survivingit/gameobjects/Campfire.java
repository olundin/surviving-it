package survivingit.gameobjects;

import survivingit.graphics.AnimatedSprite;
import survivingit.graphics.Sprite;
import survivingit.graphics.SpriteSheet;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
import survivingit.physics.Collider;

/**
 * Class for a camp fire object that can be on fire which then heals gameObjects surrounding it.
 *
 *
 */
public class Campfire extends VisibleObject {

    private static final int HEAL_AMOUNT = 1;

    private boolean lit;
    private AnimatedSprite litSprite;
    private Sprite unlitSprite;

    private double timeSinceLastHeal;
    private double litTime;
    private double maxLitTime;

    private static final double HEAL_DELAY = 2.5;
    private static final double HEAL_RANGE = 2.5;

    public Campfire(double x, double y) {
        super(x, y, Sprite.CAMPFIRE);
        this.setCollider(new Collider(-0.4, -0.4, 0.8, 0.4, false, this));

        this.lit = false;
        this.litSprite = new AnimatedSprite(Sprite.sheetToArray(SpriteSheet.CAMP_FIRE,
                                                 0,
                                                 0,
                                                 32,
                                                 32,
                                                 5,
                                                 1),
                                                 0.1);
        this.unlitSprite = Sprite.CAMPFIRE_UNLIT;
        this.timeSinceLastHeal = 0.0;
        this.litTime = 0.0;
    }

    @Override
    public void update(double dt) {
        if (lit) {
            // Increase litTime
            litTime += dt;
            // Check if fire should be extinguished
            if (litTime >= maxLitTime) {
                this.lit = false;
                litTime = 0.0;
            }

            // Perform healing to objects nearby
            timeSinceLastHeal += dt;
            if (timeSinceLastHeal >= HEAL_DELAY) {
                // A heal should be performed
                sendMessageToCreaturesInArea(new Message(MessageType.HEAL, HEAL_AMOUNT), HEAL_RANGE, HEAL_RANGE);
                timeSinceLastHeal = 0.0;
            }

            litSprite.update(dt);
            this.setSprite(litSprite.getSprite());
        } else {
            this.setSprite(unlitSprite);
        }


    }

    @Override
    public void receiveMessage(Message msg) {
        MessageType type = msg.getType();
        int data = msg.getData();
        switch(type) {
            case IGNITE:
                this.lit = true;
                this.maxLitTime = data;
                break;
            case ATTACK:
                break;
            case ITEM:
                break;
            default:
                break;
        }
    }

}
