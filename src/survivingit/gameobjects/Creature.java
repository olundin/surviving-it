package survivingit.gameobjects;

import survivingit.graphics.Sprite;

public abstract class Creature extends GameVisibleObject implements Updateable {

    protected int currentHealth;
    protected int maxHealth;
    protected double moveSpeed; // Tiles per second
    protected Direction direction;

    public Creature(final double x, final double y, final Sprite sprite, final int maxHealth, final double moveSpeed) {
	    super(x, y, sprite);
	    this.currentHealth = maxHealth;
	    this.maxHealth = maxHealth;
	    this.moveSpeed = moveSpeed;
	    this.direction = Direction.NONE;
    }

    public void update(double dt) {
        if(this.direction != Direction.NONE) {
            this.move(this.direction.x * this.moveSpeed * dt, this.direction.y * this.moveSpeed * dt);
        }
    }

    public int getCurrentHealth() {
	    return currentHealth;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public double getMoveSpeed() {
	    return moveSpeed;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setCurrentHealth(final int currentHealth) {
	    this.currentHealth = currentHealth;
    }

    public void setMaxHealth(final int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void takeDamage(int amount) {
        this.setCurrentHealth(currentHealth - amount);
        if(currentHealth < 0) {
            // Player dead
            this.setCurrentHealth(0);
        }
    }

    public void setMoveSpeed(final float moveSpeed) {
	    this.moveSpeed = moveSpeed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
