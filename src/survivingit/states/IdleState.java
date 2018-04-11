package survivingit.states;

import survivingit.gameobjects.Animal;
import survivingit.gameobjects.Direction;

import java.util.Random;

public class IdleState implements State<Animal> {

    protected static Random random;
    public double timer;

    public IdleState() {
        this.random = new Random();
        this.timer = 0.0;
    }

    public void enter(Animal animal) {

    }

    public State<Animal> update(double dt, Animal animal) {
        timer += dt;
        if(dt <= 0) System.out.println("fuck");
        if(random.nextInt((int)Math.ceil(timer)) >= 5) {
            animal.setDirection(Direction.random());
            this.timer = 0.0;
        }
        return this;
    }

    public void exit(Animal animal) {

    }

}
