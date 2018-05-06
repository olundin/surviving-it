package survivingit.items;

import survivingit.gameobjects.Creature;

/**
 * Item effect for melee attacks.
 *
 * @see AttackEffect
 */
public class MeleeAttackEffect extends AttackEffect {

    private int damage;
    private int range;

    /**
     * Creates a new MeleeAttackEffect object with the entered damage, entered range and entered source.
     * @param damage of the new MeleeAttackEffect object.
     * @param range of the new MeleeAttackEffect object.
     */
    public MeleeAttackEffect(final int damage, final int range) {
        super();
        this.damage = damage;
        this.range = range;
    }

    /**
     * Performs a melee performAttack from the entered attacker Creature with the additional MeleeAttackEffects danage
     * and range.
     *
     * @param attacker Creature who's performAttack method is used.
     */
    public void attack(Creature attacker) {
        attacker.performAttack(attacker.getDamage() + damage, attacker.getRange() + this.range);
    }

}
