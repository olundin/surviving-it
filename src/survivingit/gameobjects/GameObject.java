package survivingit.gameobjects;

import survivingit.messaging.Messagable;
import survivingit.messaging.Message;
import survivingit.physics.Collider;
import survivingit.scene.Scene;

public abstract class GameObject implements Updateable, Messagable {

    protected double x;
    protected double y;

    protected Scene scene;

    protected Collider collider;

    public GameObject() {
        this.x = 0;
        this.y = 0;
    }

    public GameObject(final double x, final double y) {
        this.x = x;
        this.y = y;
        this.collider = new Collider(0, 0, 0.0, 0.0, true, this);
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
        // Try horizontal movement
        this.x += dx;
        if(this.collider.hasCollision(this.scene)) {
            // Revert movement if collision was detected
            this.x -= dx;
        }
        // Try horizontal movement
        this.y += dy;
        if(this.collider.hasCollision(this.scene)) {
            // Revert movement if collision was detected
            this.y -= dy;
        }
    }

    public void update(double dt) {} // TODO: Remove

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return this.scene;
    }

    public Collider getCollider() {
        return this.collider;
    }

    public void setCollider(Collider col) {
        this.collider = col;
    }
}
