package survivingit.gameobjects;

import survivingit.graphics.Sprite;

public abstract class Creature extends GameVisibleObject {

    private int health;
    private float moveSpeed; // Tiles per second

    public Creature(final float x, final float y, final Sprite sprite, final int health, final float moveSpeed) {
	super(x, y, sprite);
	this.health = health;
	this.moveSpeed = moveSpeed;
    }

    public Creature(final Sprite sprite, final int health, final float moveSpeed) {
	super(sprite);
	this.health = health;
	this.moveSpeed = moveSpeed;
    }

    public int getHealth() {
	return health;
    }

    public float getMoveSpeed() {
	return moveSpeed;
    }

    public void setHealth(final int health) {
	this.health = health;
    }

    public void setMoveSpeed(final float moveSpeed) {
	this.moveSpeed = moveSpeed;
    }
}
