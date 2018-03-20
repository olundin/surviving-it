package survivingit.gameobjects;

public abstract class GameObject {

    private double x;
    private double y;

    public GameObject() {
        this.x = 0;
        this.y = 0;
    }

    public GameObject(final double x, final double y) {
	this.x = x;
	this.y = y;
    }

    public double getX() {
	return x;
    }

    public double getY() {
	return y;
    }

    public void setX(final float x) {
	this.x = x;
    }

    public void setY(final float y) {
	this.y = y;
    }
}
