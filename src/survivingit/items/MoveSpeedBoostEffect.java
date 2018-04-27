package survivingit.items;

import survivingit.gameobjects.Creature;

public class MoveSpeedBoostEffect extends UsableEffect {

    private double speedBoost;

    public MoveSpeedBoostEffect(double speedBoost, Item source) {
        super(source);
        this.speedBoost = speedBoost;
    }

    @Override
    public void use(Creature user) {
        System.out.println("speeeedboost");
    }
}
