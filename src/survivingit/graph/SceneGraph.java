package survivingit.graph;

import survivingit.scene.Scene;
import survivingit.util.Point;

import java.util.HashSet;
import java.util.Set;

/**
 * Graph implementation for SceneGraph which is based on the game world.
 *
 * Since the SceneGraph shouldn't store the nodes and edges locally but read them from it's scene field it's addNode and
 * addEdge methods are redundant, however they are still included as they are used in the Graph interface where it is a
 * fitting method for general solutions.
 *
 * @see Graph
 */
public class SceneGraph implements Graph<Point> {

    private static final double NODE_OFFSET = 0.5;

    private Scene scene;
    private final Point[] adjacent = {
        new Point(-1, 0),   // west
        new Point(0, -1),   // north
        new Point(1, 0),    // east
        new Point(0, 1),    // south
        new Point(-1, -1),  // north-west
        new Point(1, -1),   // north-east
        new Point(1, 1),    // south-east
        new Point(-1, 1)    // south-west
    };

    /**
     * Creates a new SceneGraph object with the entered Scene.
     * @param scene Scene for the new SceneGraph object.
     */
    public SceneGraph(Scene scene) {
        this.scene = scene;
    }

    /**
     * Returns false and does nothing as SceneGraph shouldn't store nodes in itself.
     * @param node Point object to be added to the graph.
     * @return false.
     */
    @Override
    public boolean addNode(Point node) { return false; }

    /**
     * Returns false and does nothing as SceneGraph shouldn't store edges in itself.
     * @param from Point object to add the edge from.
     * @param to Point object to add the edge to.
     * @param weight double value of the cost of the edge.
     * @return false.
     */
    @Override
    public boolean addEdge(Point from, Point to, double weight) { return false; }

    /**
     * Returns a Set of the neighbors of the entered Point node.
     * @param node Point object whose neighbors are requested.
     * @return a Set of Point objects with contains the neighbors of the entered Point node.
     */
    @Override
    public Set<Point> getNeighbors(Point node) {
        Set<Point> neighbors = new HashSet<>();

        for (Point n : adjacent) {
            Point neighbor = new Point(Math.floor(node.getX()) + n.getX() + NODE_OFFSET,
                                       Math.floor(node.getY()) + n.getY() + NODE_OFFSET);
            // Make sure the neighbor itself is passable
            if (!scene.getTileAt(neighbor.getX(), neighbor.getY()).isPassable()) continue;

            if (n.getX() != 0 && n.getY() != 0) {
                // Neighbor is diagonal
                // n is a neighbor if the tiles connected to both node and n are passable
                if (scene.getTileAt(node.getX() + n.getX(), node.getY()).isPassable() &&
                   scene.getTileAt(node.getX(), node.getY() + n.getY()).isPassable()) {
                    neighbors.add(neighbor);
                }
            } else {
                // Neighbor is horizontal/diagonal
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

    /**
     * Returns the weight of the edge between two nodes from and to.
     * @param from Point start object of the edge.
     * @param to Point end object of the edge.
     * @return a double value of the weight of the edge between from and to.
     */
    @Override
    public double getWeight(Point from, Point to) {
        // Return distance between the two points
        return Math.hypot(from.getX() - from.getX(), from.getY() - to.getY());
    }

}
