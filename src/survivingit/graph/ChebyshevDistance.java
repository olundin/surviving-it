package survivingit.graph;

import survivingit.util.Maths;
import survivingit.util.Point;

/**
 * ChebyshevDistance, for use in AStar with Point as Node id:s.
 *
 * Similar to ManhattanDistance, but takes diagonal movement into account.
 *
 * Efficient for 2-dimensional grids with 8 directions of movement (which is the case for our Game).
 *
 * @see AStar
 * @see AStarHeuristic
 * @see ManhattanDistance
 * @see Point
 *
 * @see <a href="https://en.wikipedia.org/wiki/Chebyshev_distance">Chebyshev Distance (Wikipedia)</a>
 */
public class ChebyshevDistance implements AStarHeuristic<Point> {

    /**
     * Calculates and returns the Chebyshev distance value between two Point objects.
     * @param n1 Point object to calculate Chebyshev distance from.
     * @param n2 Point object to calculate Chebyshev distance to.
     * @return double value of the Chebyshev distance between the two Point objects.
     */
    @Override
    public double calculate(Point n1, Point n2) {
        double dx = Math.abs(n1.getX() - n2.getX());
        double dy = Math.abs(n1.getY() - n2.getY());
        return (dx + dy) + (Maths.DIAGONAL_LENGTH - 2) * Math.min(dx, dy);
    }

    /**
     * Returns if the first entered Point object has reached the second entered Point object.
     * @param src Point start object.
     * @param dst Point end object.
     * @return a boolean if src has reached dst.
     */
    @Override
    public boolean reached(Point src, Point dst) {
        return Point.areWithin(src, dst, 0.5);
    }
    
}
