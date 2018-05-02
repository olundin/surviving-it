package survivingit.items;

import survivingit.gameobjects.Creature;

/**
 * An item effect for changing the movespeed
 */
public class MoveSpeedChangeEffect extends UsableEffect {

    private double speedDelta;

    public MoveSpeedChangeEffect(double speedDelta, Item source) {
        super(source);
        this.speedDelta = speedDelta;
    }

    @Override
    public void use(Creature user) {
        user.changeMoveSpeed(speedDelta);
        System.out.println("speeeedboost");
    }
}
