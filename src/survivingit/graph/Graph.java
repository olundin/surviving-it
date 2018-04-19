package survivingit.graph;

import java.util.Set;

public interface Graph<T> {

    public Set<T> getNeighbors(T node);
    public double getWeight(T from,  T to);

}
