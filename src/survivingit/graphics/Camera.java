package survivingit.graphics;

import survivingit.Game;
import survivingit.gameobjects.GameObject;
import survivingit.gameobjects.GameVisibleObject;
import survivingit.scene.Scene;

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
        List<GameObject> objectsInArea = scene.getObjectsInArea(this.centerX - currentWidth/2 - EDGE_PADDING,
                                                                this.centerY - currentHeight/2 - EDGE_PADDING,
                                                                currentWidth + EDGE_PADDING,
                                                                currentHeight + EDGE_PADDING);

        for (GameObject gameObject : objectsInArea) {
            if (gameObject instanceof GameVisibleObject) {
                renderer.drawVisibleObject((GameVisibleObject)gameObject, gameObject.getX() - this.centerX + currentWidth/2,
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
