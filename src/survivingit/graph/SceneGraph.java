package survivingit.graph;

import survivingit.scene.Scene;
import survivingit.util.Point;

import java.util.HashSet;
import java.util.Set;

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

    public SceneGraph(Scene scene) {
        this.scene = scene;
    }

    public boolean addNode(Point node) { return false; }
    public boolean addEdge(Point from, Point to, double weight) { return false; }

    public Set<Point> getNeighbors(Point node) {
        Set<Point> neighbors = new HashSet<>();

        for(Point n : adjacent) {
            Point neighbor = new Point(Math.floor(node.getX()) + n.getX() + NODE_OFFSET,
                                       Math.floor(node.getY()) + n.getY() + NODE_OFFSET);
            // Make sure the neighbor itself is passable
            if(!scene.getTileAt(neighbor.getX(), neighbor.getY()).isPassable()) continue;

            if(n.getX() != 0 && n.getY() != 0) {
                // Neighbor is diagonal
                // n is a neighbor if the tiles connected to both node and n are passable
                if(scene.getTileAt(node.getX() + n.getX(), node.getY()).isPassable() &&
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

    public double getWeight(Point from, Point to) {
        // Return distance between the two points
        return Math.hypot(from.getX() - from.getX(), from.getY() - to.getY());
    }

}
