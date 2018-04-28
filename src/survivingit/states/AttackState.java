package survivingit.states;

import survivingit.gameobjects.Animal;
import survivingit.gameobjects.Direction;
import survivingit.gameobjects.GameObject;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
import survivingit.util.Point;

public class AttackState implements State<Animal> {

    public GameObject target; // Setting this is optional. When set, the performAttack will be performed to this object only
    private boolean targetAttacked;

    public AttackState(GameObject target) {
        this.target = target;
        targetAttacked = false;
    }

    @Override
    public void enter(Animal object) {
    }

    @Override
    public State<Animal> update(double dt, Animal object) {
        if(target == null) {
            // Target probably dead
            return new IdleState();
        }


        Point objectPos = new Point(object.getX(), object.getY());
        Point targetPos = new Point(target.getX(), target.getY());

        if (targetAttacked) {
            // Target has already been attacked. Back off a bit.
            object.setDirection(Direction.fromAngle(Point.getAngle(targetPos, objectPos)));
            if(!Point.areWithin(objectPos, targetPos, object.getRange())) {
                // Distance between target and object is enough. Go back to following
                return new FollowState(target);
            }
        } else if (Point.areWithin(objectPos, targetPos, object.getRange()*3.0)) {
            // Target close enough set direction to towards it
            object.setDirection(Direction.fromAngle(Point.getAngle(objectPos, targetPos)));

            if (Point.areWithin(objectPos, targetPos, object.getRange())) {
                // Target almost reached. Perform performAttack. Then back off
                target.receiveMessage(new Message(MessageType.ATTACK, object.getDamage()));
                targetAttacked = true;
            }
        } else {
            // Target is too far away, go back to following it
            return new FollowState(target);
        }

        // No state change required
        return this;
    }

    @Override
    public void exit(Animal object) {

    }
}
