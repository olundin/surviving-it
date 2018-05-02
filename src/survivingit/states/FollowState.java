package survivingit.states;

import survivingit.gameobjects.Animal;
import survivingit.gameobjects.GameObject;
import survivingit.util.Point;

import java.util.Stack;

/**
 * Follow state for animals.
 * Makes the animal follow given target around.
 */
public class FollowState implements State<Animal> {

    private GameObject target;
    private double originalSpeed;
    private static final double SPEED_FACTOR = 2.0;

    /**
     * Creates a new FollowState with given target
     * @param target The GameObject to follow
     */
    public FollowState(GameObject target) {
        this.target = target;
    }

    /**
     * Function called when the State is entered by the entered object.
     * @param object object that the State is bound to.
     */
    @Override
    public void enter(Animal object) {
        // Temporarily increase movespeed
        originalSpeed = object.getMoveSpeed();
        object.setMoveSpeed(originalSpeed*SPEED_FACTOR);
    }

    /**
     * Update function that is called each gametick and updates the State
     * @param dt Time since last gametick
     * @param object Object to update with state
     * @return State to use for next update
     */
    @Override
    public State<Animal> update(double dt, Animal object) {

        if(target == null) {
            // Target no longer exists, go to idle state
            return new IdleState();
        }

        if(object.getCurrentHealth() <= object.getMaxHealth() / 10) {
            // Flee if object health too low
            return new EscapeState(target);
        }

        // Target exists, move towards it
        Point pos = new Point(object.getX(), object.getY());
        Point dst = new Point(target.getX(), target.getY());

        if(!Point.areWithin(pos, dst, object.getViewDistance())) {
            // Quit following if target is too far away
            return new IdleState();
        }

        // Update path if necessary
        Stack<Point> path = object.getPath();
        if(path.isEmpty() || !Point.areWithin(path.firstElement(), dst, 1)) {
            object.setPath(object.getScene().findPath(pos, dst));
        }
        object.followPath();

        // Check if performAttack should be performed
        if(Point.areWithin(pos, dst, object.getRange())) {
            // Target reached
            return new AttackState(target);
        }

        return this;
    }

    /**
     * Function called when the State is entered by the entered object.
     * @param object object that the State is bound to.
     */
    @Override
    public void exit(Animal object) {
        object.setMoveSpeed(originalSpeed);
    }
}
