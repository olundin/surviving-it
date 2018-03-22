package survivingit.gameobjects;

import survivingit.graphics.Renderer;
import survivingit.scene.Scene;
import survivingit.scene.Tile;

import java.util.List;

public class Camera extends GameObject {

    private double width;
    private double height;

    private static final double EDGE_PADDING = 2; // Padding to be added to edges of viewport when finding visible GameObjects

    private GameObject target;

    public Camera(final double x, final double y, final double width, final double height) {
        super(x, y);
        //this.setCenterPos(x, y);
        this.width = width;
        this.height = height;
    }

    public void setTarget(GameObject target) {
        this.target = target;
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
        if (hasTarget()) {
            this.setCenterPos(target.getX(), target.getY());
        }

        // Render visible Tiles
        for (int tileY = (int)Math.floor(this.y - EDGE_PADDING); tileY < this.y + this.height + EDGE_PADDING; tileY++) {
            for (int tileX = (int)Math.floor(this.x - EDGE_PADDING); tileX < this.x + this.width + EDGE_PADDING; tileX++) {
                Tile tile = scene.getTileAt(tileX, tileY);
                if (tile != null) {
                    // Draw sprite at position relative to camera
                    renderer.drawSprite(tileX - this.x, tileY - this.y, tile.getSprite(), this.width, this.height);
                }
            }
        }

        // Render visible GameObjects
        List<GameObject> objectsInArea = scene.getObjectsInArea(
                this.x - EDGE_PADDING,
                this.y - EDGE_PADDING,
                this.x + this.width + EDGE_PADDING,
                this.y + this.height + EDGE_PADDING
        );

        for (GameObject gameObject : objectsInArea) {

            if (gameObject instanceof GameVisibleObject) {
                // Draw sprite at position relative to camera
                renderer.drawSprite(gameObject.getX() - this.x, gameObject.getY() - this.y,
                        ((GameVisibleObject)gameObject).getSprite(), this.width, this.height);
            }
        }

        //System.out.println(this.x + ", " + this.y + ", " + this.width + ", " + this.height);
        //System.out.println(getCenterX());
    }

    private boolean hasTarget() {
        return target != null;
    }

    public void setCenterPos(double x, double y) {
        this.x = x - this.width/2;
        this.y = y - this.height/2;
    }

    public double getCenterX() {
        return this.x + this.width / 2;
    }
}
