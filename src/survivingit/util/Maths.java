package survivingit.util;

public class Maths
{

    // Constant definitions
    public static final double DIAGONAL_SPEED = Math.sqrt(1.0/2.0); // Units per second to move diagonally
    public static final double DIAGONAL_LENGTH = Math.sqrt(2.0); // Distance of moving one unit on both x and y-axis

    public static double lerp(double v0, double v1, double t) {
        // Return value at t between v0 and v1
        return (1 - t) * v0 + t * v1;
    }


}