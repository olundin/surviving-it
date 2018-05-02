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
     * Attempts to add the entered T object node to the Graph, returns a boolean if it was able to.
     * @param node T object to be added to the graph.
     * @return a boolean if it was able in adding the node to the graph.
     */
    boolean addNode(T node);

    /**
     * Attempts to add an edge from the entered from to the entered to with the entered weight, returns a boolean if it
     * was able to.
     * @param from T object to add the edge from.
     * @param to T object to add the edge to.
     * @param weight double value of the cost of the edge.
     * @return a boolean if it was able in adding the node to the graph.
     */
    boolean addEdge(T from, T to, double weight);

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
