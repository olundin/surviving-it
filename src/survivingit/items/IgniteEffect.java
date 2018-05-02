package survivingit.items;

import survivingit.gameobjects.Creature;

/**
 * Created by AngusLothian on 2018-04-27.
 */
public class IgnitionEffect extends UsableEffect {

    private int uses;

    public IgnitionEffect(final int uses, final Item source) {
        super(source);
        this.uses = uses;
    }

    @Override
    public void use(Creature user) {
        if (uses > 0) {
            user.igniteFirePlaces();
            uses--;
        }
        System.out.println(uses);
    }
}
