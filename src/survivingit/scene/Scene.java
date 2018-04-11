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

    private List<GameObject> gameObjects;
    private Tile[][] tiles;

    private Random random;

    private GameObjectComparator gameObjectComparator; // Sorts objects by y value for correct rendering
    private AStar<Point> astar;

    public Scene(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.gameObjects = new ArrayList<>();
        this.tiles = new Tile[this.height][this.width];
        this.random = new Random();
        this.gameObjectComparator = new GameObjectComparator();
        this.astar = new AStar<>(this.toGraph(), new ManhattanDistance());
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
        // Sort gameObjects by y position. Makes them render correctly
        //gameObjects.sort(this.gameObjectComparator);
        Collections.sort(gameObjects, gameObjectComparator);

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

    protected Tile[][] randomizeTiles() {
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                this.tiles[y][x] = Tile.getTile(random.nextInt(24)); // Higher bound -> less obstacles
            }
        }
        return tiles;
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public boolean inBounds(double x, double y) {
        return x >= 0 && x <= this.width - 1 && y >= 0 && y <= this.height - 1;
    }

    public List<Point> findPath(double x0, double y0, double x1, double y1) {
        return this.astar.findPath(new Point(x0, y0), new Point(x1, y1));
    }

    /**
     * Convert scene to a graph ready for A* pathfinding.
     * For each tile, it will check for neighbors which
     * can be reached and add connections to those in the graph.
     *
     * @return A graph representation of this scen ready for A*
     */
    public Graph<AStarNode<Point>> toGraph() {
        Graph<AStarNode<Point>> graph = new Graph<>();
        Point[] neighbors = {
            new Point(-1, 0),   // west
            new Point(0, -1),   // north
            new Point(1, 0),    // east
            new Point(0, 1),    // south
            new Point(-1, -1),  // north-west
            new Point(1, -1),   // north-east
            new Point(1, 1),    // south-east
            new Point(-1, 1)    // south-west
        };

        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                // Try to add edges for each neighboring tile which is passable, if this tile is passable
                if(this.getTileAt(x, y).isPassable()) {
                    Point current = new Point(x, y);

                    // Check neighbors
                    for(Point n : neighbors) {
                        Point neighbor = new Point(current.getX() + n.getX(), current.getY() + n.getY());

                        // Make sure neighbor is passable and in bounds of level
                        if(!this.getTileAt(neighbor.getX(), neighbor.getY()).isPassable() ||
                           !this.inBounds(neighbor.getX(), neighbor.getY()))
                            continue;

                        // Determine if immediate or diagonal
                        if(n.getX() != 0 && n.getY() != 0) {
                            // Neighbor is diagonal. Make sure it isn't blocked by immediate neighbors
                            if(this.getTileAt(current.getX() + n.getX(), current.getY()).isPassable() &&
                               this.getTileAt(current.getX(), current.getY() + n.getY()).isPassable()) {
                                // Neighbor can be reached from current. Add edge between nodes in graph
                                graph.addEdge(new AStarNode<>(current), new AStarNode<>(neighbor), Maths.DIAGONAL_LENGTH);
                            }
                        } else {
                            // Neighbor is immediate, since it is passable it can be added without problems
                            graph.addEdge(new AStarNode<>(current), new AStarNode<>(neighbor), 1.0);
                        }
                    }
                }
            }
        }
        return graph;
    }

    protected void updateAStar() {
        this.astar.setGraph(this.toGraph());
    }
}
