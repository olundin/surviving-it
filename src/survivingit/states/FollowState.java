package survivingit.states;

import survivingit.gameobjects.Animal;
import survivingit.gameobjects.Direction;
import survivingit.gameobjects.GameObject;
import survivingit.util.Point;

import java.util.Stack;

public class FollowState implements State<Animal> {

    private GameObject target;

    public FollowState(GameObject target) {
        this.target = target;
    }

    public void enter(Animal object) {

    }

    public State<Animal> update(double dt, Animal object) {
        if(target == null) {
            // Target no longer exists, go to idle state
            return new IdleState();
        } else {
            // Target exists, move towards it
            Point pos = new Point(object.getX(), object.getY());
            Point dst = new Point(target.getX(), target.getY());
            // Update path if necessary
            Stack<Point> path = object.getPath();
            if(path.isEmpty() || !Point.areWithin(path.firstElement(), dst, 1)) {
                object.setPath(object.getScene().findPath(pos, dst));
            }
            object.followPath();

            // Check if attack should be performed
            if(object.getPath().isEmpty()) {
                // Target reached
                return new AttackState(target);
            }
        }
        return this;
    }

    public void exit(Animal object) {

    }
}
