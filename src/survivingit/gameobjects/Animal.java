package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
import survivingit.scene.Scene;
import survivingit.states.IdleState;
import survivingit.states.StateMachine;
import survivingit.util.Point;

import java.util.List;
import java.util.Stack;

/**
 * Abstract super class for all animals, inherits from Creature.
 *
 * Contains data and behaviour that is common for all animals such ass path to follow, target finding based on the
 * animal's viewing distance and a StateMachine for animal behaviour.
 *
 * @see Creature
 */
public abstract class Animal extends Creature {

    private double viewDistance; // Distance animal can see other gameobjects in
    private Stack<Point> path;

    private StateMachine<Animal> behaviour;

    /**
     * Creates a new Animal object with the entered deltaX, deltaY, sprites, max health, movement speed, damage, alpha level,
     * viewdistance and range.
     *
     * @param x double of the animal's inital deltaX coordinate.
     * @param y double of the animal's inital deltaX coordinate.
     * @param sprites Creature sprite of the animal's sprites
     * @param maxHealth int  of the animal's max health.
     * @param moveSpeed double of the animal's initial movement speed.
     * @param damage int of the animal's performAttack damage.
     * @param alphaLevel int of the animal's alpha level.
     * @param viewDistance double of the animal's view distance in the form of a square where each side is the
     *                     viewdistance and the center of the square is the animal's position.
     * @param range double of the animal's performAttack range.
     */
    protected Animal(final double x, final double y, final Scene scene, final CreatureSprite sprites, final int maxHealth, final double moveSpeed,
                  final int damage, final int alphaLevel, final double viewDistance, final double range) {
        super(x, y, scene, sprites, maxHealth, moveSpeed, damage, alphaLevel, range);
        this.path = new Stack<>();
        this.viewDistance = viewDistance;
        this.behaviour = new StateMachine<>(this, new IdleState());
    }

    /**
     * Update method of the animal that updates the behaviour by the entered dt time amount.
     * @param dt double of the amount of time to update the animal with.
     */
    @Override
    public void update(double dt) {
        super.update(dt);
        behaviour.update(dt);
    }

    /**
     * Makes the animal follow it's path and updates it.
     *
     * Takes the next Point in the path and moves the animal in the closest direction towards it, if the animal is within
     * a certain range of the next Point it pops that point, if the path is emtpy the animal does nothing.
     */
    public void followPath() {
        if (path.isEmpty()) {
            return;
        }
        Point next = path.peek();
        Point current = new Point(this.x, this.y);
        if (!Point.areWithin(next, current, range)) {
            // Set direction to best direction to find target
            this.direction = Direction.fromAngle(Point.getAngle(new Point(this.x, this.y), next));
        } else {
            // Target reached
            path.pop();
        }
    }

    /**
     * Returns a Stack of Points which is the animals's path.
     * @return a Stack of Points that is the animal's path.
     */
    public Stack<Point> getPath() {
        return this.path;
    }

    /**
     * Sets the animals path to the entered Stack of Points.
     * @param path Stack of Points of path to be set for the animal.
     */
    public void setPath(Stack<Point> path){
        this.path = path;
    }

    /**
     * Finds and returns the animal's target GameObject in the area around it.
     * Finds the first Creature with lower alphaLevel than this animal in a square of this animal's viewDistance as sides.
     * @return the animal's target GameObject in the area around it.
     */
    public GameObject findTarget() {
        List<Creature> creaturesInRange = creaturesInArea(viewDistance, viewDistance);
        for(Creature creature: creaturesInRange) {
            if (creature.equals(this)) {
                continue; // Ignore itself
            }
            // Possible target. Check alpha level
            if (creature.getAlphaLevel() <= this.alphaLevel) {
                return creature;
            }
        }
        return null;
    }

    /**
     * Returns the animal's view distance.
     * @return double of the animal's view distance.
     */
    public double getViewDistance() {
        return this.viewDistance;
    }

    /**
     * Receives and reacts to the entered Message.
     *
     * The animal receives the message and acts differently based on what type of message it is.
     * If the entered message is a Damage message then the animal takes damage equal to the message data.
     * @param message Message object to react to.
     */
    @Override
    public void receiveMessage(Message message) {
        MessageType type = message.getType();
        int data = message.getData();
        switch(type) {
            case DAMAGE:
                this.takeDamage(data);
                break;
            default:
                break;
        }
    }

}
