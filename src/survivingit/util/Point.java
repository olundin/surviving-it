package survivingit.util;

import java.util.Objects;

public class Point {

    private static final double RANGE = 0.25; // Range in with two points are considered close

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static double getAngle(Point from, Point to) {
        return Math.atan2(to.x - from.x, to.y - from.y);
    }

    public static boolean areClose(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) <= RANGE && Math.abs(p1.y - p2.y) <= RANGE;
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
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
