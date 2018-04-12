package survivingit.physics;

import survivingit.gameobjects.GameObject;
import survivingit.scene.Scene;
import survivingit.scene.Tile;

import java.util.List;

public class Collider {

    private double x; // x relative to owner
    private double y; // y relative to owner

    private double width;
    private double height;

    private GameObject owner;

    private boolean passable; // Determines if collider can be trespassed or not

    public Collider(double x, double y, double width, double height, boolean passable, GameObject owner) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.passable = passable;
        this.owner = owner;
    }

    public boolean collidesWith(Collider other) {
        return !this.passable && overlaps(other);
    }

    public boolean overlaps(Collider other) {
        return  this.getWorldX() <= other.getWorldX() + other.width &&
                other.getWorldX() <= this.getWorldX() + this.width &&
                this.getWorldY() <= other.getWorldY() + other.height &&
                other.getWorldY() <= this.getWorldY() + this.height;
    }

    public boolean hasCollision(Scene scene) {
        return this.hasTileCollision(scene) || this.hasObjectCollision(scene);
    }

    public boolean hasTileCollision(Scene scene) {
        if(this.passable) return false;
        List<Tile> collidingTiles = scene.getTilesInArea(
                this.getWorldX(), this.getWorldY(),
                this.getWorldX() + this.width, this.getWorldY() + this.height
        );
        for(Tile tile : collidingTiles) {
            if(!tile.isPassable()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasObjectCollision(Scene scene) {
       List<GameObject> collidingObjects = scene.getObjectsInArea(
               this.getWorldX(), this.getWorldY(),
               this.getWorldX() + this.width,
               this.getWorldY() + this.height
       );
       for(GameObject gameObject : collidingObjects) {
           if(!gameObject.equals(owner)) {
               Collider other = gameObject.getCollider();
               if(this.collidesWith(other) && !other.passable) {
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
