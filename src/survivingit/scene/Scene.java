package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.gameobjects.Camera;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    private int width;
    private int height;

    protected Player player;
    protected Camera camera;

    private List<GameObject> gameObjects;
    private Tile[][] tiles;

    public Scene(Camera camera) {
        this.width = 32;
        this.height = 32;

        this.camera = camera;

        this.gameObjects = new ArrayList<>();
        this.tiles = new Tile[this.height][this.width];

        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.height; x++) {
                this.tiles[y][x] = Tile.SNOW_1;
            }
        }
    }

    public void addPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void update(double dt) {
        if (hasPlayer()) {
            player.update(dt);
        }
        for (GameObject gameObject : gameObjects) {
            gameObject.update(dt);
	    }
    }

    public Tile getTileAt(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.SNOW_2;
        } else {
            return tiles[y][x];
        }
    }

    public List<GameObject> getObjectsInArea(double startX, double startY, double endX, double endY) {
        List<GameObject> objectsInArea = new ArrayList<>();
        if (hasPlayer() && isObjectInArea(player, startX, startY, endX, endY)) {
            objectsInArea.add(player);
        }
        for (GameObject gameObject : gameObjects) {
            if (isObjectInArea(gameObject, startX, startY, endX, endY)) {
                objectsInArea.add(gameObject);
            }
        }
        return objectsInArea;
    }

    private boolean isObjectInArea(GameObject gameObject, double startX, double startY, double endX, double endY) {
        double x = gameObject.getX();
        double y = gameObject.getY();
        return startX <= x && x <= endX && startY <= y && y <= endY;
    }

    public boolean hasPlayer() {
        return player != null;
    }
}
