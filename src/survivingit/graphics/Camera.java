package survivingit.graphics;

import survivingit.gameobjects.GameObject;
import survivingit.scene.Scene;

public class Camera {

    private double x;
    private double y;
    private double width;
    private double height;

    private GameObject target;

    public Camera() {
        this.x = 0;
        this.y = 0;
        this.width = 10;
        this.height = 5;
    }

    public Camera(final double x, final double y, final double width, final double height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void render(Renderer renderer, Scene scene) {

    }
}
