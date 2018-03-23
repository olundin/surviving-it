package survivingit.physics;

import survivingit.scene.Scene;

public class Collider {

    private double x;
    private double y;

    private double width;
    private double height;

    public Collider(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean collidesWith(Collider other) {
        return  this.x > other.getX() &&
                this.x <= other.getX() + other.getWidth() &&
                this.y > other.getY() &&
                this.y < other.getY() + other.getWidth();
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
