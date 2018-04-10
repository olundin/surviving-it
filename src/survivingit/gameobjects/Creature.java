package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.Sprite;


public abstract class Creature extends GameVisibleObject implements Updateable {

    protected int currentHealth;
    protected int maxHealth;
    protected double moveSpeed; // Tiles per second
    protected Direction direction;

    private double lastX;
    private double lastY;

    private CreatureSprite sprites;

    public Creature(final double x, final double y, final CreatureSprite sprites, final int maxHealth, final double moveSpeed) {
	    super(x, y, sprites.getSprite());
	    this.currentHealth = maxHealth;
	    this.maxHealth = maxHealth;
	    this.moveSpeed = moveSpeed;
	    this.direction = Direction.NONE;

	    this.lastX = x;
	    this.lastY = y;

	    this.sprites = sprites;
    }

    public void update(double dt) {
        if(this.direction != Direction.NONE) {
            this.move(this.direction.x * this.moveSpeed * dt, this.direction.y * this.moveSpeed * dt);
        }

        // Update sprites
        sprites.update(dt, this.direction, this.moveSpeed, this.isMoving());
        this.sprite = sprites.getSprite();

        // Keep track of movement
        this.lastX = this.x;
        this.lastY = this.y;
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

    public boolean isMoving() {
        return Math.abs(this.x - this.lastX) > 0.00001 || Math.abs(this.y - this.lastY) > 0.00001;
    }

    public void performAttack(int damage, int range) {
        //TODO:
    }


    public void heal(int healAmount) {
        // Don't set health to higher to than max.
        this.setCurrentHealth(currentHealth + Math.max(healAmount - maxHealth, 0));
    }
}