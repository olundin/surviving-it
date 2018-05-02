package survivingit.states;

import survivingit.gameobjects.Animal;
import survivingit.gameobjects.Direction;
import survivingit.gameobjects.GameObject;
import survivingit.util.Point;

/**
 * Escape state for animals.
 * When entered, sets direction away from object to flee from
 *
 * @see State
 */
public class EscapeState implements State<Animal> {

    private GameObject from; // Object to escape from

    /**
     * Create a new EscapeState from an object
     * @param from The object to escape from
     */
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

    /**
     * Update function that is called each gametick and updates the State
     * @param dt Time since last gametick
     * @param object Object to update with state
     * @return State to use for next update
     */
    @Override
    public State<Animal> update(double dt, Animal object) {
        return this;
    }

    /**
     * Function called when the State is entered by the entered object.
     * @param object object that the State is bound to.
     */
    @Override
    public void exit(Animal object) {

    }
}
