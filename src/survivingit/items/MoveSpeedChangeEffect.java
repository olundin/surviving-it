package survivingit.items;

import survivingit.gameobjects.Creature;

/**
 * An item effect for changing the movespeed of the user.
 *
 * @see UsableEffect
 */
public class MoveSpeedChangeEffect extends UsableEffect {

    private double speedDelta;

    private int uses;

    /**
     * Creates a new MoveSpeedChangeEffect object with the entered speedDelta, uses and Item source.
     * @param speedDelta double value of the speed change that the new MoveSpeedChangeEffect object will have on use.
     * @param uses int value of the number of uses which the new MoveSpeedChangeEffect will have.
     */
    public MoveSpeedChangeEffect(double speedDelta, int uses) {
        super();
        this.speedDelta = speedDelta;
        this.uses = uses;
    }

    /**
     * Changes the movement speed of the user with the MoveSpeedChangeEffect object's speedDelta if it still has uses.
     * @param user Creature.
     */
    @Override
    public void use(Creature user) {
        if (uses > 0) {
            user.changeMoveSpeed(speedDelta);
            uses--;
        } else {
            System.out.println("out of uses");
        }
    }

}
