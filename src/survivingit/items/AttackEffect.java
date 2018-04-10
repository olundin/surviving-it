package survivingit.items;

import survivingit.gameobjects.Creature;

public class AttackEffect extends AbstractEffect implements Usable {

    private int damage;
    private int range;

    public AttackEffect(final int damage, final int range, final Item source) {
        super(source);
        this.damage = damage;
        this.range = range;
    }

    public void use(Creature creature) {
        creature.performAttack(damage, range);
    }

}
