package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
import survivingit.states.IdleState;
import survivingit.states.StateMachine;
import survivingit.util.Point;

import java.util.List;
import java.util.Stack;

public abstract class Animal extends Creature {

    private double viewDistance; // Distance animal can see other gameobjects in
    private Stack<Point> path;

    private StateMachine<Animal> behaviour;

    /**
     * Creates a new Animal object with the entered x, y, sprites, max health, movement speed, damage, alpha level,
     * viewdistance and range.
     *
     * @param x double of the animal's inital x coordinate.
     * @param y double of the animal's inital x coordinate.
     * @param sprites Creature sprite of the animal's sprites
     * @param maxHealth int  of the animal's max health.
     * @param moveSpeed double of the animal's initial movement speed.
     * @param damage int of the animal's attack damage.
     * @param alphaLevel int of the animal's alpha level.
     * @param viewDistance double of the animal's view distance in the form of a square where each side is the
     *                     viewdistance and the center of the square is the animal's position.
     * @param range double of the animal's attack range.
     */
    public Animal(final double x, final double y, final CreatureSprite sprites, final int maxHealth, final double moveSpeed, final int damage, final int alphaLevel, final double viewDistance, final double range) {
        super(x, y, sprites, maxHealth, moveSpeed, damage, alphaLevel, range);
        this.path = new Stack<>();
        this.viewDistance = viewDistance;
        this.behaviour = new StateMachine<>(this, new IdleState());
    }

    /**
     * Update method of the animal
     * @param dt
     */
    @Override
    public void update(double dt) {
        super.update(dt);
        behaviour.update(dt);
    }

    public void followPath() {
        if(path.isEmpty()) return;
        Point next = path.peek();
        Point current = new Point(this.x, this.y);
        if (!Point.areWithin(next, current, 0.1)) {
            // Set direction to best direction to find target
            this.direction = Direction.fromAngle(Point.getAngle(new Point(this.x, this.y), next));
        } else {
            // Target reached
            path.pop();
        }
    }

    public Stack<Point> getPath() {
        return this.path;
    }

    public void setPath(Stack<Point> path){
        this.path = path;
    }

    public GameObject findTarget() {
        List<GameObject> inRange = this.scene.getObjectsInArea(this.x - viewDistance/2,
                                                               this.y - viewDistance/2,
                                                               this.x + viewDistance/2,
                                                               this.y + viewDistance/2);
        for(GameObject object : inRange) {
            if(object instanceof Creature) {
                if(object.equals(this)) continue; // Ignore itself
                // Possible target. Check alpha level
                Creature creature = (Creature)object;
                if(creature.getAlphaLevel() <= this.alphaLevel) {
                    return object;
                }
            }
        }
        return null;
    }

    public double getViewDistance() {
        return this.viewDistance;
    }

    public double getRange() {
        return this.range;
    }

    @Override
    public void receiveMessage(Message msg) {
        MessageType type = msg.getType();
        int data = msg.getData();
        switch(type) {
            case ATTACK:
                this.takeDamage(data);
                break;
            default:
                break;
        }
    }

}
