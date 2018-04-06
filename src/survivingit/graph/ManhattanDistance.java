package survivingit.graph;

import survivingit.util.Point;

/**
 * ManhattanDistance, for use in AStar with Point as Node id:s.
 * Calculates absolute distance between two points by adding
 * difference in x and difference in y together.
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

    public double calculate(Point n1, Point n2) {
        return Math.abs(n1.getX() - n2.getX()) + Math.abs(n1.getY() - n2.getY());
    }
}
