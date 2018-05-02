package survivingit.scene;

import survivingit.gameobjects.GameObject;
import survivingit.graphics.WorldRenderer;
import survivingit.util.Point;

import java.util.List;

/**
 * The camera is responsible for "seeing" the scene
 * It has a world position and size, and a screen position and size.
 *
 * @see Scene
 */
public class Camera {

    private double x;
    private double y;

    private double width;
    private double height;

    private int screenX;
    private int screenY;
    private int screenWidth;
    private int screenHeight;

    private static final double ZOOM_MIN = 1.0;
    private static final double ZOOM_MAX = 512.0;

    private static final double EDGE_PADDING = 4; // Padding to be added to edges of viewport when finding visible GameObjects

    private Point target = null;

    /**
     * Creates a new camera with the given properties
     * @param x World x
     * @param y World y
     * @param width World width
     * @param height World height
     * @param screenX Screen x
     * @param screenY Screen y
     * @param screenWidth Screen width
     * @param screenHeight Screen height
     */
    public Camera(double x, double y, double width, double height, int screenX, int screenY, int screenWidth, int screenHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.screenX = screenX;
        this.screenY = screenY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Sets the cameras target. The target will be followed
     * @param target The point to follow
     */
    public void setTarget(Point target) {
        this.target = target;
    }

    /**
     * Zoom in or out
     * @param delta The zoom value
     */
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

    /**
     * Returns a list of the gameobjects visible to the camera
     * @param scene The scene to get objects from
     * @return A list of the visible game objects
     */
    public List<GameObject> getVisibleObjects(Scene scene) {
        return scene.getObjectsInArea(
                this.x - EDGE_PADDING,
                this.y - EDGE_PADDING,
                this.x + this.width + EDGE_PADDING,
                this.y + this.height + EDGE_PADDING
        );
    }

    /**
     * Render the tiles of the given scene visible to the camera
     * @param scene The scene to look at
     * @param renderer The renderer to draw with
     */
    public void renderVisibleTiles(Scene scene, WorldRenderer renderer) {
        for (int tileY = (int)Math.floor(this.y - EDGE_PADDING); tileY < this.y + this.height + EDGE_PADDING; tileY++) {
            for (int tileX = (int)Math.floor(this.x - EDGE_PADDING); tileX < this.x + this.width + EDGE_PADDING; tileX++) {
                Tile tile = scene.getTileAt(tileX, tileY);
                if (tile != null) {
                    // Draw sprite at position relative to camera
                    renderer.drawTile(tileX, tileY, tile, this);
                }
            }
        }
    }

    /**
     * Update the camera.
     */
    public void update() {
        // Make sure target is being followed if it exists
        if (hasTarget()) {
            this.setCenterPos(target.getX(), target.getY());
        }
    }

    private boolean hasTarget() {
        return target != null;
    }

    /**
     * Gets camera world width
     * @return The camera's world width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets camera world height
     * @return The camera's world heights
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Sets the center position of the camera
     */
    public void setCenterPos(double x, double y) {
        this.x = x - this.width/2;
        this.y = y - this.height/2;
    }

    /**
     * Converts screen x to world x
     * @param x screen x
     * @return world x
     */
    public double screenToWorldX(int x) {
        return (x - this.screenX) / this.pixelsPerUnitX() + this.x;
    }

    /**
     * Converts screen y to world y
     * @param y screen y
     * @return world y
     */
    public double screenToWorldY(int y) {
        return (y - this.screenY) / this.pixelsPerUnitY() + this.y;
    }

    /**
     * Converts world x to screen x
     * @param x world x
     * @return screen x
     */
    public int worldToScreenX(double x) {
        return (int)((x - this.x) * this.pixelsPerUnitX()) + this.screenX;
    }

    /**
     * Converts world y to screen y
     * @param y world y
     * @return screen y
     */
    public int worldToScreenY(double y) {
        return (int)((y - this.y) * this.pixelsPerUnitY()) + this.screenY;
    }

    /**
     * Returns horizontal screen pixels per world x units
     * @return pixels per x unit
     */
    public double pixelsPerUnitX() {
        return this.screenWidth / this.width;
    }

    /**
     * Returns vertical screen pixels per world y units
     * @return pixels per y unit
     */
    public double pixelsPerUnitY() {
        return this.screenHeight / this.height;
    }
}
