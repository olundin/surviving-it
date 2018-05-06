package survivingit.gameobjects;

import survivingit.scene.Scene;
import survivingit.scene.Tile;

import java.util.List;

/**
 * Collider class used for stopping and detecting collision.
 *
 * All Colliders has the shapes of a rectangle.
 */
public class Collider {

    private double x; // deltaX relative to owner
    private double y; // deltaY relative to owner

    private double width;
    private double height;

    private GameObject owner;

    private boolean passable; // Determines if collider can be trespassed or not

    /**
     * Creates a new collider.
     * @param x Relative x to owner
     * @param y Relative y to owner
     * @param width Width of collider
     * @param height Height of collider
     * @param passable Is the collider passable?
     * @param owner The owner of the collider
     */
    public Collider(double x, double y, double width, double height, boolean passable, GameObject owner) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.passable = passable;
        this.owner = owner;
    }

    /**
     * Returns whether the collider collides with other (non passable)
     * @param other The other collider
     * @return Whether the colliders collide
     */
    public boolean collidesWith(Collider other) {
        return !this.passable && !other.passable && overlaps(other);
    }

    /**
     * Returns whether the collider overlaps another collider.
     * @param other The other collider
     * @return Whether the colliders overlap
     */
    public boolean overlaps(Collider other) {
        return  this.getWorldX() <= other.getWorldX() + other.width &&
                other.getWorldX() <= this.getWorldX() + this.width &&
                this.getWorldY() <= other.getWorldY() + other.height &&
                other.getWorldY() <= this.getWorldY() + this.height;
    }

    /**
     * Returns whether the collider has a collision in scene
     * @param scene The scene to check
     * @return Whether a collision is occurring
     */
    public boolean hasCollision(Scene scene) {
        return this.hasTileCollision(scene) || this.hasObjectCollision(scene);
    }

    /**
     * Returns whether the collider collides with any tiles in the scene
     * @param scene The scene to check
     * @return Whether a collision is occurring
     */
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

    /**
     * Returns whether the collider collides with any gameobjects in the scene
     * @param scene The scene to check
     * @return Whether a collision is occurring
     */
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

    /**
     * Returns the world x of the collider
     * @return The world x of the collider
     */
    public double getWorldX() {
        return owner.getX() + this.x;
    }

    /**
     * Returns the world y of the collider
     * @return The world y of the collider
     */
    public double getWorldY() {
        return owner.getY() + this.y;
    }

    /**
     * Returns the width of the collider
     * @return The Width of the collider
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the collider
     * @return The height of the collider
     */
    public double getHeight() {
        return height;
    }

}
