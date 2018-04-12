package survivingit.states;

import survivingit.gameobjects.Animal;
import survivingit.gameobjects.Direction;
import survivingit.gameobjects.GameObject;

import java.util.Random;

public class IdleState implements State<Animal> {

    protected static Random random = new Random();
    private double timer;

    private boolean walking;

    public IdleState() {
        this.timer = 0.0;
    }

    public void enter(Animal object) {

    }

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
                if(random.nextDouble()*5 <= timer) {
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

    public void exit(Animal object) {

    }

}
