package survivingit.graph;

import java.util.Set;

public interface Graph<T> {

    boolean addNode(T node);
    boolean addEdge(T from, T to, double weight);
    Set<T> getNeighbors(T node);
    double getWeight(T from,  T to);

}
