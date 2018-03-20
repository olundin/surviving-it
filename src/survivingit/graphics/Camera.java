package survivingit.graphics;

import survivingit.Game;
import survivingit.gameobjects.GameObject;
import survivingit.gameobjects.GameVisibleObject;
import survivingit.scene.Scene;
import survivingit.scene.Tile;

import java.util.List;

public class Camera {

    private static final double STANDARD_WIDTH = Game.WIDTH / Renderer.STANDARD_TILE_SIZE; // Camera tile width when scale is 1:1
    private static final double STANDARD_HEIGHT = Game.HEIGHT / Renderer.STANDARD_TILE_SIZE; // Camera tile height when scale is 1:1

    private double scale; // Scale of camera in current:standard of the camera dimensions

    private double centerX;
    private double centerY;

    private static final double EDGE_PADDING = 2; // Padding to be added to edges of viewport when finding visible GameObjects

    private GameObject target;

    public Camera(final double scale, final double centerX, final double centerY) {
        this.scale = scale;
	this.centerX = centerX;
	this.centerY = centerY;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void setPosition(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void render(Renderer renderer, Scene scene) {
        double currentWidth = calcWidth();
        double currentHeight = calcHeight();

        // Camera area
        // Top left
        double startX = this.centerX - currentWidth/2 - EDGE_PADDING;
        double startY = this.centerY - currentHeight/2 - EDGE_PADDING;
        // Bottom right
        double width = currentWidth + EDGE_PADDING;
        double height = currentHeight + EDGE_PADDING;

        // Render Tile
        for (int y = (int)Math.floor(startY); y < startY + height; y++) {
            for (int x = (int)Math.floor(startX); x < startX + width; x++) {
                Tile tile = scene.getTileAt(x, y);
                if (tile != null) {
                    renderer.drawSprite(tile.getSprite(), x - this.centerX + currentWidth/2, y - this.centerY + currentHeight/2, scale);
                }
            }
        }

        // Get visible GameObjects
        List<GameObject> objectsInArea = scene.getObjectsInArea(startX,
                                                                startY,
                                                                width,
                                                                height);

        // Render GameObjects
        for (GameObject gameObject : objectsInArea) {
            if (gameObject instanceof GameVisibleObject) {
                renderer.drawSprite(((GameVisibleObject)gameObject).getSprite(), gameObject.getX() - this.centerX + currentWidth/2,
                                           gameObject.getY() - this.centerY + currentHeight/2, scale);
            }
        }
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double calcWidth() {
        return STANDARD_WIDTH * scale;
    }

    public double calcHeight() {
        return STANDARD_HEIGHT * scale;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }
}
