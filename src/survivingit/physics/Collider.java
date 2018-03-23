package survivingit.physics;

import survivingit.gameobjects.GameObject;
import survivingit.scene.Scene;

import java.util.List;

public class Collider {

    private double x; // x relative to owner
    private double y; // y relative to owner

    private double width;
    private double height;

    private GameObject owner;

    private boolean passable; // Determines if collider can be trespassed or not

    public Collider(double x, double y, double width, double height, GameObject owner) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.owner = owner;
    }

    public boolean collidesWith(Collider other) {
        return  this.x > other.getX() &&
                this.x <= other.getX() + other.getWidth() &&
                this.y > other.getY() &&
                this.y < other.getY() + other.getWidth();
    }

    public boolean hasCollision(Scene scene) {
        List<GameObject> collidingObjects = scene.getObjectsInArea(
                owner.getX() + this.x, owner.getY() + this.y,
                owner.getX() + this.x + this.width,
                owner.getY() + this.y + this.height
        );
        for(GameObject gameObject : collidingObjects) {
            if(!gameObject.equals(owner)) {
                Collider other = gameObject.getCollider();
                if(this.collidesWith(other) && !other.isPassable()) {
                    return true;
                }
            }
        }
        return false;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWorldX() {
        return owner.getX() + this.x;
    }

    public double getWorldY() {
        return owner.getY() + this.y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isPassable() {
        return this.passable;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
