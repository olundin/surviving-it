package survivingit.graph;

import survivingit.util.Maths;
import survivingit.util.Point;

/**
 * ChebyshevDistance, for use in AStar with Point as Node id:s.
 * Similar to ManhattanDistance, but takes diagonal movement
 * into account.
 *
 * Efficient for 2-dimensional grids with 8 directions of movement
 *
 * @see AStar
 * @see AStarHeuristic
 * @see ManhattanDistance
 * @see Point
 *
 * @see <a href="https://en.wikipedia.org/wiki/Chebyshev_distance">Chebyshev Distance (Wikipedia)</a>
 */
public class ChebyshevDistance implements AStarHeuristic<Point> {

    private static final double REACHED_RANGE = 0.5;

    public double calculate(Point n1, Point n2) {
        double dx = Math.abs(n1.getX() - n2.getX());
        double dy = Math.abs(n1.getY() - n2.getY());
        return (dx + dy) + (Maths.DIAGONAL_LENGTH - 2) * Math.min(dx, dy);
    }

    public boolean reached(Point src, Point dst) {
            return Point.areWithin(src, dst, REACHED_RANGE);
        }
}
