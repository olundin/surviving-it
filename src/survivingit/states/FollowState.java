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
            // Make sure path points towards target
            Stack<Point> path = object.getPath();
            if(path.isEmpty() || !Point.areClose(path.firstElement(), dst)) {
                System.out.println("changing path");
                object.setPath(object.getScene().findPath(pos, dst));
            }
            object.followPath();
        }
        return this;
    }

    public void exit(Animal object) {

    }
}
