package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.gameobjects.Camera;
import survivingit.util.Vec2;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    private int width;
    private int height;

    private Player player;
    private Camera currentCamera;

    private List<GameObject> gameObjects;
    private Tile[][] tiles;

    public Scene(Camera camera) {
        this.width = 32;
        this.height = 32;

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

    public void update() {
        if (player != null) {
            player.update();
        }
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
	}
    }

    public Tile getTileAt(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.SNOW_2;
        } else {
            return tiles[y][x];
        }
    }

    public List<GameObject> getObjectsInArea(Vec2 start, Vec2 end) {
        List<GameObject> inArea = new ArrayList<>();
        if (hasPlayer() && isObjectInArea(player, start, end)) {
            inArea.add(player);
        }
        for (GameObject gameObject : gameObjects) {
            if (isObjectInArea(gameObject, start, end)) {
                inArea.add(gameObject);
            }
        }
        return inArea;
    }

    private boolean isObjectInArea(GameObject gameObject, Vec2 start, Vec2 end) {
        Vec2 pos = gameObject.getPos();
        return Vec2.between(pos, start, end);
    }

    public boolean hasPlayer() {
        return player != null;
    }
}
