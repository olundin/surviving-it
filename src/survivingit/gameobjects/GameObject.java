package survivingit.gameobjects;

import survivingit.scene.Scene;

public abstract class GameObject implements Updateable {

    protected double x;
    protected double y;

    protected Scene scene;

    public GameObject() {
        this.x = 0;
        this.y = 0;
    }

    public GameObject(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setPos(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public void move(final double dx, final double dy) {
        this.x += dx;
        this.y += dy;
    }

    public void update(double dt) {}; // TODO: Remove

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
