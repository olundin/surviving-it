package survivingit.gameobjects;

import survivingit.graphics.Renderer;
import survivingit.scene.Tile;

import java.util.List;

public class Camera extends GameObject {

    private double width;
    private double height;

    private static final double ZOOM_MIN = 1.0;
    private static final double ZOOM_MAX = 64.0;

    private static final double EDGE_PADDING = 2; // Padding to be added to edges of viewport when finding visible GameObjects

    private Renderer renderer;

    private GameObject target;

    public Camera(final double x, final double y, final double width, final double height, final Renderer renderer) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.renderer = renderer;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void zoom(double delta) {
        if(width - delta < ZOOM_MIN || width - delta > ZOOM_MAX) {
            return;
        }
        double relation = this.height / this.width;
        this.width -= delta;
        this.height -= (delta * relation);
        this.x += delta / 2;
        this.y += (delta * relation) / 2;
    }

    public void render(Renderer renderer) {
        // Make sure target is being followed if it exists
        if (hasTarget()) {
            this.setCenterPos(target.getX(), target.getY());
        }

        // Render visible Tiles
        for (int tileY = (int)Math.floor(this.y - EDGE_PADDING); tileY < this.y + this.height + EDGE_PADDING; tileY++) {
            for (int tileX = (int)Math.floor(this.x - EDGE_PADDING); tileX < this.x + this.width + EDGE_PADDING; tileX++) {
                Tile tile = this.scene.getTileAt(tileX, tileY);
                if (tile != null) {
                    // Draw sprite at position relative to camera
                    renderer.drawSprite(tileX, tileY, tile.getSprite(), this.x, this.y, this.width, this.height, false);
                }
            }
        }

        // Render visible GameObjects
        List<GameObject> objectsInArea = this.scene.getObjectsInArea(
                this.x - EDGE_PADDING,
                this.y - EDGE_PADDING,
                this.x + this.width + EDGE_PADDING,
                this.y + this.height + EDGE_PADDING
        );

        for (GameObject gameObject : objectsInArea) {

            if (gameObject instanceof GameVisibleObject) {
                // Draw sprite at position relative to camera
                renderer.drawVisibleObject((GameVisibleObject)gameObject, this.x, this.y, this.width, this.height);
            }
        }
    }

    private boolean hasTarget() {
        return target != null;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setCenterPos(double x, double y) {
        this.x = x - this.width/2;
        this.y = y - this.height/2;
    }

    public double screenToWorldX(double screenX) {
        double ppu = this.renderer.getWidth() / this.width; // Screen pixels per unit
        return this.x + screenX / ppu;
    }

    public double screenToWorldY(double screenY) {
        double ppu = this.renderer.getHeight() / this.height; // Screen pixels per unit
        return this.y + screenY / ppu;
    }
}
