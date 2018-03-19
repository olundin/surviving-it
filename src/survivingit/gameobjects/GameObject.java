package survivingit.gameobjects;

public abstract class GameObject {

    private float x;
    private float y;

    public GameObject() {
        this.x = 0;
        this.y = 0;
    }

    public GameObject(final float x, final float y) {
	this.x = x;
	this.y = y;
    }

    public float getX() {
	return x;
    }

    public float getY() {
	return y;
    }

    public void setX(final float x) {
	this.x = x;
    }

    public void setY(final float y) {
	this.y = y;
    }
}
