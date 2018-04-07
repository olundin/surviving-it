package survivingit.graph;

/**
 * AStarNode is a graph node which is used in the
 * A* algorithm. The node contains a g score and an h score
 * which together can be used to rate the node.
 *
 * @param <T> Node identifier
 *
 * @see Node
 * @see AStar
 */
public class AStarNode<T> extends Node<T> {

    private double g; // Distance from start
    private double h; // Heuristic of destination

    public AStarNode(T id) {
        super(id);
        this.g = Double.POSITIVE_INFINITY; // All g-scores have to be considered lower than the initial one
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
