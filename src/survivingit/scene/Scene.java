package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.graph.*;
import survivingit.graphics.WorldRenderer;
import survivingit.physics.Collider;
import survivingit.util.*;

import java.util.*;

/**
 * The scene class. A scene is a 2D tile array and a list of gameobjects.
 * These make up the world of the game.
 *
 * @see Tile
 * @see GameObject
 */
public abstract class Scene {

    private Camera camera;

    protected int width;
    protected int height;

    protected Player player;

    private List<GameObject> gameObjects;
    protected Tile[][] tiles;

    private Random random;
    private GameObjectComparator gameObjectComparator; // Sorts objects by deltaY value for correct rendering

    private AStar<Point> aStar;

    /**
     * Creates a new scene
     * @param camera The camera that "sees" the scene
     * @param width The width of the scene
     * @param height The height of the scene
     */
    public Scene(final Camera camera, final int width, final int height) {
        this.camera = camera;
        this.width = width;
        this.height = height;
        this.gameObjects = new ArrayList<>();
        this.tiles = new Tile[this.height][this.width];
        this.random = new Random();
        this.gameObjectComparator = new GameObjectComparator();
        this.aStar = new AStar<>(new SceneGraph(this), new ChebyshevDistance());
    }

    /**
     * Updates the scene
     * @param dt Time since last game tick
     */
    public void update(double dt) {
        camera.update();

        for (GameObject gameObject : camera.getVisibleObjects(this)) {
            gameObject.update(dt);

            if(!gameObject.isAlive()) {
                // GameObject should be removed from list
                gameObjects.remove(gameObject);
            }
        }
    }

    /**
     * Render the scene.
     * @param renderer The renderer responsible for drawing things on screen.
     */
    public void render(WorldRenderer renderer) {
        // Render tiles
        camera.renderVisibleTiles(this, renderer);

        // Render gameobjects
        List<GameObject> visibleObjects = camera.getVisibleObjects(this);
        //visibleObjects.sort(gameObjectComparator); // Sort visible objects by Y value to ensure correct rendering
        Collections.sort(visibleObjects, gameObjectComparator);
        for(GameObject object : visibleObjects) {
            renderer.drawObject((VisibleObject)object, camera);
        }
    }

    /**
     * Set the scene's player
     * @param player The player object
     */
    public void setPlayer(Player player) {
        this.player = player;
        this.add(player);
    }

    /**
     * Gets the scene's player
     * @return The player object
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Adds a gameobject at it's position in the scene,
     * regardless of whether this will cause a collision or not.
     * @param gameObject The gameobject to add to scene
     */
    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
        gameObject.setScene(this);
    }

    /**
     * Adds a gameobject to the scene if this doesn't cause any collisions.
     * @param gameObject The gameobject to add
     * @return Whether the add was successful (collision occurred) or not
     */
    public boolean tryAdd(GameObject gameObject) {
        // Makes sure gameObject won't get stuck in tile
        if(!this.getTileAt(gameObject.getX(), gameObject.getY()).isPassable()) {
            return false;
        }
        // make sure gameObject won't get stuck in another GameObject
        Collider objCol = gameObject.getCollider();
        if(!this.getObjectsInArea(objCol.getWorldX(),
                                  objCol.getWorldY(),
                            objCol.getWorldX() + objCol.getWidth(),
                            objCol.getWorldY() + objCol.getHeight()).isEmpty()) {
            return false;
        }

        this.add(gameObject);
        return true;
    }

    /**
     * Returns tile at given position
     * @param x X coordinate to get tile from
     * @param y Y coordinate to get tile from
     * @return The tile at (x, y)
     */
    public Tile getTileAt(double x, double y) {
        int xInt = (int)Math.floor(x);
        int yInt = (int)Math.floor(y);
        if (!this.inBounds(x, y) || tiles[xInt][yInt] == null) {
            return Tile.VOID;
        } else {
            return tiles[yInt][xInt];
        }
    }

    /**
     * Returns a list of the tiles in the given area
     * @param startX Start x of the area
     * @param startY Start y of the area
     * @param endX End x of the area
     * @param endY End y of the area
     * @return A list of the tiles in the given area
     */
    public List<Tile> getTilesInArea(double startX, double startY, double endX, double endY) {
        List<Tile> tilesInArea = new ArrayList<>();
        for(int y = (int)Math.floor(startY); y < (int)Math.floor(endY) + 1; y++) {
            for(int x = (int)Math.floor(startX); x < (int)Math.floor(endX) + 1; x++) {
                tilesInArea.add(getTileAt(x, y));
            }
        }
        return tilesInArea;
    }

    /**
     * Returns a list of the gameobjects in the given area.
     * @param startX Start x of the area
     * @param startY Start y of the area
     * @param endX End x of the area
     * @param endY End y of the area
     * @return A list of the gameobjects in the given area
     */
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

    /**
     * Returns whether the given gameobjects is within the given area
     * @param gameObject The gameobject to check
     * @param startX Start x of the area
     * @param startY Start y of the area
     * @param endX End x of the area
     * @param endY End y of the area
     * @return Whether the gameobject is in the area or not
     */
    private boolean isObjectInArea(GameObject gameObject, double startX, double startY, double endX, double endY) {
        // Returns true if object's collider is within area
        Collider collider = gameObject.getCollider();
        double x1 = collider.getWorldX();
        double y1 = collider.getWorldY();
        double x2 = x1 + collider.getWidth();
        double y2 = y1 + collider.getHeight();

        return x1 <= endX && startX <= x2 && y1 <= endY && startY <= y2;
    }

    /**
     * Returns whether the scene has a player or not
     * @return Whether the scene has a player.
     */
    public boolean hasPlayer() {
        return player != null;
    }

    /**
     * Sets the tile at the given position
     * @param x The x position
     * @param y The y position
     * @param tile The tile to set to
     */
    public void setTileAt(double x, double y, Tile tile) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            tiles[Maths.fastFloor(y)][Maths.fastFloor(x)] = tile;
        }
    }

    /**
     * Fills the given area with the given tiles.
     * @param startX Start x of the area
     * @param startY Start y of the area
     * @param endX End x of the area
     * @param endY End y of the area
     * @param tile The tile type to fill with
     */
    protected void fillTiles(int startX, int startY, int endX, int endY, Tile tile) {
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                this.tiles[y][x] = tile;
            }
        }
    }

    /**
     * Returns the scene's tiles
     * @return The tiles
     */
    public Tile[][] getTiles() {
        return this.tiles;
    }

    /**
     * Returns whether the given position is withing the scenes size
     * @param x The x position to check
     * @param y The y position to check
     * @return If the given position is in bounds
     */
    public boolean inBounds(double x, double y) {
        return x >= 0 && x <= this.width - 1 && y >= 0 && y <= this.height - 1;
    }

    /**
     * Finds a path from one point to another using the scenes A* field
     * @param from The origin
     * @param to The destination
     * @return A stack of points that need to be traveled in order to travel from origin to destination
     */
    public Stack<Point> findPath(Point from, Point to) {
        return this.aStar.findPath(from, to);
    }

    /**
     * Returns the width of the scene.
     * @return The width of the scene
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the scene.
     * @return The height of the scene
     */
    public int getHeight() {
        return this.height;
    }
}
