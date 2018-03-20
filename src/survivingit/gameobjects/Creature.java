package survivingit.gameobjects;

import survivingit.graphics.Sprite;

public abstract class Creature extends GameVisibleObject implements Updateable {

    private int health;
    private double moveSpeed; // Tiles per second

    public Creature(final double x, final double y, final Sprite sprite, final int health, final double moveSpeed) {
	super(x, y, sprite);
	this.health = health;
	this.moveSpeed = moveSpeed;
    }

    public Creature(final Sprite sprite, final int health, final double moveSpeed) {
	super(sprite);
	this.health = health;
	this.moveSpeed = moveSpeed;
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
}
