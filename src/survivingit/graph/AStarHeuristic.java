package survivingit.graph;

/**
 * Interface for heuristics usable by A* implementation.
 *
 * @param <T> Node identifier
 * @see AStar
 */
public interface AStarHeuristic<T> {

    public double calculate(T n1, T n2);

    public boolean reached(T src, T dst);

}
