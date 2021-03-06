package survivingit.states;

import survivingit.Game;
import survivingit.gameobjects.Animal;
import survivingit.gameobjects.Direction;
import survivingit.gameobjects.GameObject;

/**
 * Idle state for an animal.
 * Makes the animal walk around randomly until a possible target is encountered.
 *
 * @see State
 */
public class IdleState implements State<Animal> {

    private double timer;

    private boolean walking;

    /**
     * Creates a new idle state.
     */
    public IdleState() {
        this.timer = 0.0;
    }

    /**
     * State entry point.
     * @param object object that the State is bound to.
     */
    @Override
    public void enter(Animal object) {
        object.setDirection(Direction.NONE);
    }

    /**
     * Update function that is called each gametick and updates the State
     * @param dt Time since last game tick
     * @param object Object to update
     * @return Possible new state. Returns itself otherwise
     */
    @Override
    public State<Animal> update(double dt, Animal object) {
        // Try to find targets
        GameObject target = object.findTarget();
        if(target != null) {
            return new FollowState(target);
        }
        timer += dt;

        if(timer >= 1.0) {
            // Update direction every second
            if(walking) {
                object.setDirection(Direction.NONE);
                walking = false;
            } else {
                if(Game.RANDOM.nextDouble()*5 <= timer) {
                    object.setDirection(Direction.random());
                    walking = true;
                    timer = 0.0;
                }
            }
            timer = 0.0;
        }


        // Stay in this state
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
