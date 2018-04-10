package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.gameobjects.Camera;
import survivingit.graph.*;
import survivingit.physics.Collider;
import survivingit.util.*;

import java.util.*;

public abstract class Scene {

    protected int width;
    protected int height;

    protected Player player;
    protected Camera camera;

    private List<GameObject> gameObjects;
    private Tile[][] tiles;

    private Random random;

    private AStar<Point> astar;

    public Scene(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.camera = camera;
        this.gameObjects = new ArrayList<>();
        this.tiles = new Tile[this.height][this.width];
        this.random = new Random();
        this.astar = new AStar<>(new SceneGraph(this), new ChebyshevDistance());
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
        for (GameObject gameObject : gameObjects) {
            gameObject.update(dt);
	    }
    }

    public Tile getTileAt(double x, double y) {
        int xInt = (int)Math.floor(x);
        int yInt = (int)Math.floor(y);
        if (!this.inBounds(x, y) || tiles[xInt][yInt] == null) {
            return Tile.WATER;
        } else {
            return tiles[yInt][xInt];
        }
    }

    public List<Tile> getTilesInArea(double startX, double startY, double endX, double endY) {
        List<Tile> tilesInArea = new ArrayList<>();
        for(int y = (int)Math.floor(startY); y < (int)Math.floor(endY) + 1; y++) {
            for(int x = (int)Math.floor(startX); x < (int)Math.floor(endX) + 1; x++) {
                tilesInArea.add(getTileAt(x, y));
            }
        }
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

    protected void randomizeTiles() {
        for (int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                this.tiles[y][x] = Tile.getTile(random.nextInt(24)); // Higher bound -> less obstacles
            }
        }
    }

    protected void fillTiles(int startX, int startY, int endX, int endY, Tile tile) {
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                this.tiles[y][x] = tile;
            }
        }
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public boolean inBounds(double x, double y) {
        return x >= 0 && x <= this.width - 1 && y >= 0 && y <= this.height - 1;
    }

    public Stack<Point> findPath(Point from, Point to) {
        return this.astar.findPath(from, to);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
