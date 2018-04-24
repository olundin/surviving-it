package survivingit.items;

import survivingit.gameobjects.Creature;

public class MeleeAttackEffect extends AttackEffect {

    private int damage;
    private int range;

    public MeleeAttackEffect(final int damage, final int range, final Item source) {
        super(source);
        this.damage = damage;
        this.range = range;
    }

    public void attack(Creature attacker) {
        attacker.performAttack(damage, range);
        System.out.println("");
    }

}
