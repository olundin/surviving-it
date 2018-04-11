package survivingit.gameobjects;

import survivingit.graphics.WorldRenderer;
import survivingit.scene.Tile;

import java.util.Collections;
import java.util.List;

public class Camera extends GameObject {

    private double width;
    private double height;

    private int screenX;
    private int screenY;
    private int screenWidth;
    private int screenHeight;

    private static final double ZOOM_MIN = 1.0;
    private static final double ZOOM_MAX = 128.0;

    private static final double EDGE_PADDING = 2; // Padding to be added to edges of viewport when finding visible GameObjects

    private GameObject target;
    private GameObjectComparator gameObjectComparator; // Sorts objects by y value for correct rendering

    public Camera(double x, double y, double width, double height, int screenX, int screenY, int screenWidth, int screenHeight) {
        super(x, y);
        this.width = width;
        this.height = height;

        this.screenX = screenX;
        this.screenY = screenY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.gameObjectComparator = new GameObjectComparator();
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

    public void render(WorldRenderer renderer) {
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
                    renderer.drawTile(tileX, tileY, tile, this);
                }
            }
        }

        // Get objects visible to camera
        List<GameObject> objectsInArea = this.scene.getObjectsInArea(
                this.x - EDGE_PADDING,
                this.y - EDGE_PADDING,
                this.x + this.width + EDGE_PADDING,
                this.y + this.height + EDGE_PADDING
        );

        // Sort gameObjects by y position. Makes them render correctly
        //objectsInArea.sort(this.gameObjectComparator);
        Collections.sort(objectsInArea, gameObjectComparator);

        for (GameObject gameObject : objectsInArea) {

            if (gameObject instanceof VisibleObject) {
                // Draw sprite at position relative to camera
                renderer.drawObject((VisibleObject)gameObject, this);
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

    public double screenToWorldX(int x) {
        return (x - this.screenX) / this.pixelsPerUnitX() + this.x;
    }

    public double screenToWorldY(int y) {
        return (y - this.screenY) / this.pixelsPerUnitY() + this.y;
    }

    public int worldToScreenX(double x) {
        return (int)((x - this.x) * this.pixelsPerUnitX()) + this.screenX;
    }

    public int worldToScreenY(double y) {
        return (int)((y - this.y) * this.pixelsPerUnitY()) + this.screenY;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public double pixelsPerUnitX() {
        return this.screenWidth / this.width;
    }

    public double pixelsPerUnitY() {
        return this.screenHeight / this.height;
    }
}
