package survivingit.scene;

import survivingit.gameobjects.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    private List<GameObject> gameObjects;

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
}
