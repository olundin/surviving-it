package survivingit.gameobjects;

import survivingit.graphics.Sprite;


public abstract class Creature extends GameVisibleObject implements Updateable {

    protected int health;
    protected int maxHealth;
    protected double moveSpeed; // Tiles per second
    protected Direction direction;

    public Creature(final double x, final double y, final Sprite sprite, final int health, final double moveSpeed) {
	    super(x, y, sprite);
	    this.health = health;
	    this.maxHealth = health;
	    this.moveSpeed = moveSpeed;
	    this.direction = Direction.NONE;
    }

    public int getHealth() {
	    return health;
    }

    public double getMoveSpeed() {
	    return moveSpeed;
    }

    public void setHealth(final int health) {
	this.health = health;
    }

    public void setMoveSpeed(final float moveSpeed) {
	this.moveSpeed = moveSpeed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Heals the creature with healAmount up to the creatures maxHealth.
     * @param healAmount to heal the creature with
     */
    public void heal(int healAmount) {
	this.health += healAmount - Math.max(health + healAmount - maxHealth, 0);
    }

    public void performAttack(int damage, int range) {
        //TODO: Implement this
        System.out.println("Creature attack: " + damage + ", " + range);
    }
}
