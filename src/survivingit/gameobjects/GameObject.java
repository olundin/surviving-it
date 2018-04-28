package survivingit.gameobjects;

import survivingit.Game;
import survivingit.messaging.Messagable;
import survivingit.messaging.Message;
import survivingit.physics.Collider;
import survivingit.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract superclass for all GameObjects.
 *
 * Contains functionality and data about movement, positioning, collision which is essential for all game objects.
 * GameObject also has a field for the scene where it exists, which is utilised in subclasses where it is necessary to
 * be aware of their surroundings.
 */
public abstract class GameObject implements Messagable {

    protected double x;
    protected double y;

    protected Scene scene;

    protected Collider collider;

    /**
     * Creates a new GameObject object with the initial position of 0, 0.
     */
    public GameObject() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Creates a new GameObject with the entered x and y coord.
     * @param x double of the gameojects's initial x position.
     * @param y double of the gameobjects's inital y position.
     */
    public GameObject(final double x, final double y) {
        this.x = x;
        this.y = y;
        this.collider = new Collider(0, 0, 0.0, 0.0, true, this);
    }

    /**
     * Returns a double of the the gameObject's x position.
     * @return double of the gameObject's x position.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns a double of the the gameObject's x position.
     * @return double of the gameObject's x position.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the gameObject's position to the entered x and y coordinate.
     * @param x double of the x position to be set.
     * @param y double of the y position to be set.
     */
    public void setPos(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Attempts to move the gameObject with the entered dx and dy changes in x and y coordinate.
     *
     * If any of the dx and dy changes that are attempted causes a collisions with the gameObject's collider thgen the
     * change is reverted.
     * @param dx double of the change in x coordinate to be attempted.
     * @param dy double of the change in y coordinate to be attempted.
     */
    public void move(final double dx, final double dy) {
        // Try horizontal movement
        this.x += dx;
        if (this.collider.hasCollision(this.scene)) {
            // Revert movement if collision was detected
            this.x -= dx;
        }
        // Try horizontal movement
        this.y += dy;
        if (this.collider.hasCollision(this.scene)) {
            // Revert movement if collision was detected
            this.y -= dy;
        }
    }

    /**
     * Updates the gameObject with entered dt time value.
     *
     * At the moment this method doesn't inherently do something but is necessary due to the use of the update-pattern
     * in how gameObjects relate to the game. This method is usually overwritten in sub classes.
     * @param dt double value of the amount of time that has passed.
     */
    public void update(double dt) {} // TODO: Remove

    protected List<GameObject> gameObjectsInArea(final double width, final double height) {
        List<GameObject> gameObjects =  this.scene.getObjectsInArea(x - width/2, y - height/2,
                x + width/2, y + height/2);
        if (gameObjects.contains(this)) {
            gameObjects.remove(this);
        }
        return gameObjects;
    }

    protected List<Creature> creaturesInArea(final double width, final double height) {
        List<Creature> creatures = new ArrayList<>();
        for (GameObject gameObject : gameObjectsInArea(width, height)) {
            if (gameObject instanceof Creature) {
                creatures.add((Creature) gameObject);
            }
        }
        return creatures;
    }

    protected void sendMessageToGameObjectsInArea(final Message message, final double width, final double height) {
        for (GameObject gameObject : gameObjectsInArea(width, height)) {
            gameObject.receiveMessage(message);
        }
    }

    protected void sendMesageToCreaturesInArea(final Message message, final double width, final double height) {
        for (Creature creature : creaturesInArea(width, height)) {
            creature.receiveMessage(message);
        }
    }

    /**
     * Sets the gameObject's Scene to the entered Scene.
     * @param scene Scene to be set for the gameObject.
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Returns the Scene of the gameObject.
     * @return the Scene of the gameObject.
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Returns the Collider of the gameObject.
     * @return the Collider of the gameObject.
     */
    public Collider getCollider() {
        return this.collider;
    }

    /**
     * Sets the gameObjects Collider to the entered Collider
     * @param col Collider to be set for the gameObject.
     */
    public void setCollider(Collider col) {
        this.collider = col;
    }
}
