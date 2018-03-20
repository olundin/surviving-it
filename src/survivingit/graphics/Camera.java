package survivingit.graphics;

import survivingit.Game;
import survivingit.gameobjects.GameObject;
import survivingit.gameobjects.GameVisibleObject;
import survivingit.gameobjects.Player;
import survivingit.scene.Scene;
import survivingit.scene.Tile;

import java.util.List;

public class Camera {

    private double width;
    private double height;

    private double x;
    private double y;

    private static final double EDGE_PADDING = 2; // Padding to be added to edges of viewport when finding visible GameObjects

    private GameObject target;

    public Camera(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }


    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public void zoom(double delta) {
        double relation = this.height / this.width;
        this.width -= delta;
        this.height -= (delta * relation);
        this.x += delta / 2;
        this.y += (delta * relation) / 2;
    }

    public void render(Renderer renderer, Scene scene) {
        // Make sure target is being followed if it exists
        if(target != null) {
            this.x = target.getX() - this.width / 2;
            this.y = target.getY() - this.height / 2;
        }

        // Render visible Tiles
        for (int tileY = (int)Math.floor(this.y); tileY < this.y + this.height; tileY++) {
            for (int tileX = (int)Math.floor(this.x); tileX < this.x + this.width; tileX++) {
                Tile tile = scene.getTileAt(tileX, tileY);
                if (tile != null) {
                    // Draw sprite at position relative to camera
                    renderer.drawSprite(tileX - this.x,
                                        tileY - this.y,
                                        tile.getSprite(), this.width, this.height);
                }
            }
        }

        // Render visible GameObjects
        List<GameObject> objectsInArea = scene.getObjectsInArea(this.x, this.y, this.width, this.height);

        for (GameObject gameObject : objectsInArea) {
            if (gameObject instanceof GameVisibleObject) {
                // Draw sprite at position relative to camera
                renderer.drawSprite(gameObject.getX() - this.x,
                                    gameObject.getY() - this.y,
                                    ((GameVisibleObject)gameObject).getSprite(), this.width, this.height);
            }

            /*
            // Make sure to target player if it is found. TODO: Remove this
            if (gameObject instanceof Player && this.target == null) {
                this.target = gameObject;
            }
            */
        }
    }
}
