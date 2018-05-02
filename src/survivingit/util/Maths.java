package survivingit.util;

/**
 * Utility class for some of the Math used in the game.
 * Named Maths to not interfere with the Java Math class.
 */
public class Maths {

    // Constant definitions
    /**
     * The speed to move with diagonally
     */
    public static final double DIAGONAL_SPEED = Math.sqrt(1.0/2.0); // Units per second to move diagonally
    /**
     * The length of a diagonal movement (dx = dy = 1)
     */
    public static final double DIAGONAL_LENGTH = Math.sqrt(2.0); // Distance of moving one unit on both deltaX and deltaY-axis

    /**
     * Linear interpolation.
     *
     * @param v0 Lowest
     * @param v1
     * @param t
     * @return
     */
    public static double lerp(double v0, double v1, double t) {
        // Return value at t between v0 and v1
        return (1 - t) * v0 + t * v1;
    }

    /**
     * Returns the dot product of vector (ax, ay) and (bx, by)
     * @param ax
     * @param ay
     * @param bx
     * @param by
     * @return
     */
    public static double dotProduct(double ax, double ay, double bx, double by) {
        return ax*bx + ay*by;
    }

    /**
     * Converts number from one range to another, keeping relation.
     * For example, a number X in range (A,B), will be converted to
     * a number Y in range (C,D), where the relation between X-A and
     * B-X is the same as the relation between C-Y and D-Y.
     *
     * @param in The value to convert
     * @param inMin Min of the input value's range
     * @param inMax Max of the input value's range
     * @param outMin Min of the return value's range
     * @param outMax Max of the return value's range
     * @return input value converted to output range
     */
    public static double affineTransformation(double in, double inMin, double inMax, double outMin, double outMax) {
        return ((in - inMin) / (inMax - inMin)) * (outMax - outMin) + outMin;
    }


    /**
     * Floors a double and casts it to int.
     * @param x The value to floor
     * @return Input value floored as an int
     */
    public static int fastFloor(double x) {
        // A little bit faster than (int)Math.floor(deltaX)
        // (Based on time comparisons
        int xi = (int)x;
        return x < xi ? xi - 1 : xi;
    }

}