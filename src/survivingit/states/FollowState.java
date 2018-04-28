package survivingit.states;

import survivingit.gameobjects.Animal;
import survivingit.gameobjects.Direction;
import survivingit.gameobjects.GameObject;
import survivingit.util.Point;

import java.util.Stack;

public class FollowState implements State<Animal> {

    private GameObject target;
    private double originalSpeed;
    private static final double SPEED_FACTOR = 2.0;


    public FollowState(GameObject target) {
        this.target = target;
    }

    public void enter(Animal object) {
        // Temporarily increase movespeed
        originalSpeed = object.getMoveSpeed();
        object.setMoveSpeed(originalSpeed*SPEED_FACTOR);
    }

    public State<Animal> update(double dt, Animal object) {

        if(target == null) {
            // Target no longer exists, go to idle state
            return new IdleState();
        }

        if(object.getCurrentHealth() <= 3.0) {
            // Flee if object health too low
            return new EscapeState(target);
        }

        // Target exists, move towards it
        Point pos = new Point(object.getX(), object.getY());
        Point dst = new Point(target.getX(), target.getY());

        if(!Point.areWithin(pos, dst, object.getViewDistance() * 1.5)) {
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

    public void exit(Animal object) {
        object.setMoveSpeed(originalSpeed);
    }
}
