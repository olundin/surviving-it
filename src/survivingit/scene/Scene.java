package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.gameobjects.Camera;
import survivingit.graphics.Sprite;
import survivingit.physics.Collider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Scene {

    private int width;
    private int height;

    protected Player player;
    protected Camera camera;

    private List<GameObject> gameObjects;
    private Tile[][] tiles;

    private GameObjectComparator gameObjectComparator; // Sorts objects by y value for correct rendering

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

        this.gameObjectComparator = new GameObjectComparator();
    }

    public void addPlayer(Player player) {
        this.player = player;
        this.add(player);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
        gameObject.setScene(this);
    }

    public void update(double dt) {
        gameObjects.sort(this.gameObjectComparator);

        for (GameObject gameObject : gameObjects) {
            gameObject.update(dt);
	    }
    }

    public Tile getTileAt(double x, double y) {
        int xInt = (int)Math.floor(x);
        int yInt = (int)Math.floor(y);
        if (xInt < 0 || xInt >= width || yInt < 0 || yInt >= height) {
            return Tile.SNOW_2;
        } else {
            return tiles[yInt][xInt];
        }
    }

    public List<Tile> getTilesInArea(double startX, double startY, double endX, double endY) {
        List<Tile> tilesInArea = new ArrayList<>();
        //System.out.println(startX + "; " + startY + "; " + endX + "; " + endY);
        for(int y = (int)Math.floor(startY); y < (int)Math.floor(endY) + 1; y++) {
            for(int x = (int)Math.floor(startX); x < (int)Math.floor(endX) + 1; x++) {
                tilesInArea.add(getTileAt(x, y));
            }
        }
        System.out.println(tilesInArea.size());

        return tilesInArea;
    }

    public List<GameObject> getObjectsInArea(double startX, double startY, double endX, double endY) {
        // Find all return all objects whose colliders intersect with given rect
        List<GameObject> objectsInArea = new ArrayList<>();
        for (GameObject gameObject : gameObjects) {
            if (isObjectInArea(gameObject, startX, startY, endX, endY)) {
                objectsInArea.add(gameObject);
            }
        }
        return objectsInArea;
    }

    private boolean isObjectInArea(GameObject gameObject, double startX, double startY, double endX, double endY) {
        // Returns true if object's collider is within area
        Collider col = gameObject.getCollider();
        double x1 = col.getWorldX();
        double y1 = col.getWorldY();
        double x2 = x1 + col.getWidth();
        double y2 = y1 + col.getHeight();

        return x1 <= endX && startX <= x2 && y1 <= endY && startY <= y2;
    }

    public boolean hasPlayer() {
        return player != null;
    }

    public void setTileAt(double x, double y, Tile tile) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            tiles[(int) Math.floor(y)][(int) Math.floor(x)] = tile;
        }
    }
}
