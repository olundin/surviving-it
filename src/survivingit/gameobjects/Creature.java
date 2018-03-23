package survivingit.gameobjects;

import survivingit.graphics.Sprite;

public abstract class Creature extends GameVisibleObject implements Updateable {

    protected int health;
    protected double moveSpeed; // Tiles per second
    protected Direction direction;

    public Creature(final double x, final double y, final Sprite sprite, final int health, final double moveSpeed) {
	    super(x, y, sprite);
	    this.health = health;
	    this.moveSpeed = moveSpeed;
	    this.direction = Direction.NONE;
    }

    public void update(double dt) {
        // Check if horizontal movement is okay
        if(this.scene.getTileAt(this.x + this.direction.x * this.moveSpeed * dt, this.y).isPassable()) {
            this.move(this.direction.x * this.moveSpeed * dt, 0);
        }
        if(this.scene.getTileAt(this.x, this.y + this.direction.y * this.moveSpeed * dt).isPassable()) {
            this.move(0, this.direction.y * this.moveSpeed * dt);
        }
    }

    public int getHealth() {
	    return health;
    }

    public double getMoveSpeed() {
	    return moveSpeed;
    }

    public Direction getDirection() {
        return this.direction;
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
}
