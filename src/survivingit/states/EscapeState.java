package survivingit.states;

import survivingit.gameobjects.Animal;
import survivingit.gameobjects.Direction;
import survivingit.gameobjects.GameObject;
import survivingit.util.Point;

public class EscapeState implements State<Animal> {

    private GameObject from; // Object to escape from

    public EscapeState(GameObject from) {
        this.from = from;
    }

    @Override
    public void enter(Animal object) {
        // Move away from object that is being escaped from
        object.setDirection(Direction.fromAngle(Point.getAngle(
                new Point(from.getX(), from.getY()),
                new Point(object.getX(), object.getY()))));
    }

    @Override
    public State<Animal> update(double dt, Animal object) {
        return this;
    }

    @Override
    public void exit(Animal object) {

    }
}
