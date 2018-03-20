package survivingit.graphics;

import survivingit.gameobjects.GameObject;
import survivingit.scene.Scene;

public class Camera {

    private float x;
    private float y;
    private float width;
    private float height;

    private GameObject target;

    public Camera() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.width = 10.0f;
        this.height = 5.0f;
    }

    public Camera(final float x, final float y, final float width, final float height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void render(Renderer renderer, Scene scene) {

    }
}
