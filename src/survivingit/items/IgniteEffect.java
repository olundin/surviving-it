package survivingit.items;

import survivingit.gameobjects.Creature;

/**
 * Created by AngusLothian on 2018-04-27.
 */
public class IgniteEffect extends UsableEffect {

    private int uses;
    private int fireTime;
    private double ignitionWidth;
    private double ignitionHeight;

    public IgniteEffect(final int uses, final int fireTime, final double ignitionWidth, final double ignitionHeight,
                        final Item source) {
        super(source);
        this.uses = uses;
        this.fireTime = fireTime;
        this.ignitionWidth = ignitionWidth;
        this.ignitionHeight = ignitionHeight;
    }

    @Override
    public void use(Creature user) {
        if (uses > 0) {
            user.igniteFirePlaces(this.fireTime, this.ignitionWidth, this.ignitionHeight);
            uses--;
        } else {
            System.out.println("Out of uses!");
        }
    }
}
