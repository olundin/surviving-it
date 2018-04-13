package survivingit.items;

import survivingit.gameobjects.Creature;

public class AttackEffect extends AbstractEffect implements Usable {

    private static EffectType EFFECT_TYPE = EffectType.ATTACK;

    private int damage;
    private int range;

    public AttackEffect(final int damage, final int range, final Item source) {
        super(source, EFFECT_TYPE);
        this.damage = damage;
        this.range = range;
    }

    public void use(Creature creature) {
        creature.performAttack(damage, range);
    }

}
