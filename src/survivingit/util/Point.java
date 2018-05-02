package survivingit.util;

import java.util.Objects;

/**
 * A Point contains an x and a y position.
 * The class also has some helper functions for
 * math with points.
 */
public class Point {

    private double x;
    private double y;

    /**
     * Creates a new Point
     * @param x X value of the point
     * @param y Y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the deltaX value of the point
     * @return X value of the point
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the deltaY value of the point
     * @return Y value of the point
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the deltaX value of the point
     * @param x New deltaX value of point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the deltaY value of the point
     * @param y New deltaY value of point
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get angle between two points using Math.atan2.
     * @see Math#atan2
     *
     * @param from Origin
     * @param to Destination
     * @return Angle between origin and destination
     */
    public static double getAngle(Point from, Point to) {
        return Math.atan2(to.x - from.x, to.y - from.y);
    }

    /**
     * Returns true if two points are within a certain range of each other
     * @param p1 The first point
     * @param p2 The second point
     * @param range The allowed range
     * @return Whether p1 and p2 are within range of each other
     */
    public static boolean areWithin(Point p1, Point p2, double range) {
        return Math.abs(Math.hypot(p1.x - p2.x, p1.y - p2.y)) <= range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(this.x, point.x) == 0 && Double.compare(this.y, point.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Double.valueOf(x), Double.valueOf(y));
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
