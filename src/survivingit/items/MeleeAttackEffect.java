package survivingit.items;

import survivingit.gameobjects.Creature;

/**
 * Item effect for melee attacks.
 */
public class MeleeAttackEffect extends AttackEffect {

    private int damage;
    private int range;

    /**
     * Creates a new MeleeAttackEffect object with the entered damage, entered range and entered source.
     * @param damage of the new MeleeAttackEffect object.
     * @param range of the new MeleeAttackEffect object.
     * @param source of the new MeleeAttackEffect object.
     */
    public MeleeAttackEffect(final int damage, final int range, final Item source) {
        super(source);
        this.damage = damage;
        this.range = range;
    }

    /**
     * Performs a melee performAttack from the entered attacker Creature.
     * @param attacker Creature who's performAttack method is used.
     */
    @Override
    public void attack(Creature attacker, double angle) {
        attacker.performAttack(attacker.getDamage() + damage, Math.max(attacker.getRange(), this.range), angle);
    }

}
