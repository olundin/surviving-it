package survivingit.graph;

/**
 * AStarNode is a graph node which is used in the
 * A* algorithm. The node contains a g score and an h score
 * which together can be used to rate the node.
 *
 * @see AStar
 */
public class AStarNode<T> {

    private T id;
    private double g; // Distance from start
    private double h; // Heuristic of destination

    public AStarNode(T id) {
        this.id = id;
        this.g = Double.POSITIVE_INFINITY; // All g-scores have to be considered lower than the initial one
    }

    public AStarNode(T id, final double g, final double h) {
        this.id = id;
        this.g = g;
        this.h = h;
    }

    public T getId() {
        return id;
    }

    public double getG() {
        return this.g;
    }

    public double getF() {
        // f score is used to compare AStarNodes
        return this.g + this.h;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setH(double h) {
        this.h = h;
    }
}
