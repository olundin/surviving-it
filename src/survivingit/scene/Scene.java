package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    private int width;
    private int height;

    private List<GameObject> gameObjects;
    private Tile[][] tiles;

    public Scene() {
        this.width = 32;
        this.height = 32;

        this.gameObjects = new ArrayList<>();
        this.tiles = new Tile[this.height][this.width];

        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.height; x++) {
                this.tiles[y][x] = new Tile(Sprite.FOX, true);
            }
        }
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
	}
    }

    public Tile getTileAt(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return new Tile(Sprite.FOX, true);
        } else {
            return tiles[y][x];
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
