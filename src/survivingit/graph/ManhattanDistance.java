package survivingit.graph;

import survivingit.util.Point;

/**
 * ManhattanDistance, for use in AStar with Point as Node id:s.
 *
 * Calculates absolute distance between two points by adding the difference in deltaX and difference in deltaY together.
 *
 * Efficient for 2-dimensional grids with 4 directions of movement
 *
 * @see AStar
 * @see AStarHeuristic
 * @see Point
 *
 * @see <a href="https://en.wiktionary.org/wiki/Manhattan_distance">Manhattan Distance (Wikipedia)</a>
 */
public class ManhattanDistance implements AStarHeuristic<Point> {

    private static final double REACHED_RANGE = 0.5;

    /**
     * Calculates and returns the Manhattan distance value between two Point objects.
     * @param n1 Point object to calculate the Manhattan distance from.
     * @param n2 Point object to calculate the Manhattan distance to.
     * @return double value of the Manhattan distance between the two Point objects.
     */
    @Override
    public double calculate(Point n1, Point n2) {
        return Math.abs(Math.hypot(n1.getX() - n2.getX(), n1.getY() - n2.getY()));
    }

    /**
     * Returns if the first entered Point object has reached the second entered Point object.
     * @param src Point start object.
     * @param dst Point end object.
     * @return a boolean if src has reached dst.
     */
    @Override
    public boolean reached(Point src, Point dst) {
        return Point.areWithin(src, dst, REACHED_RANGE);
    }

}
