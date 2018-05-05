package survivingit.gameobjects;

import survivingit.graphics.AnimatedSprite;
import survivingit.graphics.Sprite;
import survivingit.graphics.SpriteSheet;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
import survivingit.physics.Collider;
import survivingit.scene.Scene;

/**
 * Class for a camp fire object that can be on fire which then heals gameObjects surrounding it, inherits from
 * VisibleObject.
 *
 * @see VisibleObject
 */
public class Campfire extends VisibleObject {

    private static final int HEAL_AMOUNT = 1;

    private boolean lit;
    private AnimatedSprite litSprite;
    private Sprite unlitSprite;

    private double timeSinceLastHeal;
    private double litTime;
    private double maxLitTime;

    private static final double HEAL_DELAY = 1;
    private static final double HEAL_RANGE = 2.5;

    private static final double FRAME_LENGTH = 0.1;

    private static final double COL_X = -0.4;
    private static final double COL_Y = -0.4;
    private static final double COL_WIDTH = 0.8;
    private static final double COL_HEIGHT = 0.4;

    /**
     * Creates a new Campfire object with the entered x and y position.
     * @param x double val of the x position of the new Campfire object.
     * @param y double val of the y position of the new Campfire object.
     */
    public Campfire(double x, double y, Scene scene) {
        super(x, y, scene, Sprite.CAMPFIRE);
        this.setCollider(new Collider(COL_X, COL_Y, COL_WIDTH, COL_HEIGHT, false, this));

        this.lit = false;
        this.litSprite = new AnimatedSprite(SpriteSheet.CAMP_FIRE.toArray(
                                                 0,
                                                 0,
                                                 32,
                                                 32,
                                                 5,
                                                 1),
                                                 FRAME_LENGTH);
        this.unlitSprite = Sprite.CAMPFIRE_UNLIT;
        this.timeSinceLastHeal = 0.0;
        this.litTime = 0.0;
        this.maxLitTime = 0.0;
    }

    /**
     * Updates the campfire with the entered amount of passed time.
     *
     * If the campfire is on fire it increases it's time on fire and heals the creatures surrounding it.
     * @param dt double value of the amount of time that has passed.
     */
    @Override
    public void update(double dt) {
        if (dt < 0) {
            throw new IllegalArgumentException("Negative update time entered");
        }
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

    /**
     * Receives the entered message and reacts to it.
     *
     * The campfire receives the entered message and depending on the MessageType reacts to it. If the entered message
     * is of MessageType IGNITE the fireplace is lit on fire with the message data being the amount of time the
     * fireplace will burn.
     * @param msg Message which the campfire recieves and reacts to.
     */
    @Override
    public void receiveMessage(Message msg) {
        MessageType type = msg.getType();
        int data = msg.getData();
        switch(type) {
            case IGNITE:
                this.lightOnFire(data);
                break;
            default:
                break;
        }
    }

    private void lightOnFire(final int fireTime) {
        this.lit = true;
        this.litTime = 0.0;
        this.maxLitTime = Math.max(this.maxLitTime, fireTime);
    }

}
