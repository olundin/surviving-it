package survivingit.graph;

import java.util.Set;

/**
 * Graph Interface for a general Graph of objects of the parameter class T that can be used in the AStar class.
 * @param <T>
 *
 * @see AStar
 */
public interface Graph<T> {

    /**
     * Returns a Set of the neighbors of the entered T node.
     * @param node T object whose neighbors are requested.
     * @return a Set of T objects with contains the neighbors of the entered T node.
     */
    Set<T> getNeighbors(T node);

    /**
     * Returns the weight of the edge between two nodes from and to.
     * @param from T start object of the edge.
     * @param to T end object of the edge.
     * @return a double value of the weight of the edge between from and to.
     */
    double getWeight(T from,  T to);

}
