package survivingit.states;

import survivingit.gameobjects.Animal;
import survivingit.gameobjects.GameObject;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
import survivingit.util.Point;

import java.util.Stack;

public class AttackState implements State<Animal> {

    public GameObject target; // Setting this is optional. When set, the attack will be performed to this object only

    public AttackState(GameObject target) {
        this.target = target;
    }

    public void enter(Animal object) { }

    public State<Animal> update(double dt, Animal object) {
        // The attack has a designated target
        target.receiveMessage(new Message(MessageType.ATTACK, object.getDamage()));

        if(target == null) {
            // Target killed
            return new IdleState();
        } else {
            // Target still alive
            return new FollowState(target);
        }
    }

    public void exit(Animal object) { }
}
