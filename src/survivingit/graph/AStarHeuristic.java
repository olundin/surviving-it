package survivingit.graph;

import survivingit.util.Point;

/**
 * Interface for heuristics usable by A* implementation.
 *
 * @param <T> Node identifier
 * @see AStar
 */
public interface AStarHeuristic<T> {

    /**
     * Calculates and returns the heuristic value between two T objects.
     * @param n1 T object to calculate heuristic from.
     * @param n2 T object to calculate heuristic to.
     * @return double value of the heuristic between the two T objects.
     */
    public double calculate(T n1, T n2);

    /**
     * Returns if the first entered T object has reached the second entered T object.
     * @param src T start object.
     * @param dst T end object.
     * @return a boolean if src has reached dst.
     */
    public boolean reached(T src, T dst);

}
