package survivingit.scene;

import survivingit.gameobjects.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    private List<GameObject> gameObjects;
    //private Tile[][] tiles;

    public Scene() {
        this.gameObjects = new ArrayList<>();
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
	}
    }

    public List<GameObject> getObjectsInArea(double startX, double startY, double width, double height) {
        List<GameObject> inArea = new ArrayList<>();
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getX() >= startX &&
                gameObject.getY() >= startY &&
                gameObject.getX() <= startX + width &&
                gameObject.getY() <= startY + height) {
                inArea.add(gameObject);
            }
        }
        return inArea;
    }
}
