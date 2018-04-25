package survivingit.util;

public class Maths {

    // Constant definitions
    public static final double DIAGONAL_SPEED = Math.sqrt(1.0/2.0); // Units per second to move diagonally
    public static final double DIAGONAL_LENGTH = Math.sqrt(2.0); // Distance of moving one unit on both x and y-axis

    public static double lerp(double v0, double v1, double t) {
        // Return value at t between v0 and v1
        return (1 - t) * v0 + t * v1;
    }

    public static double dotProduct(double ax, double ay, double bx, double by) {
        return ax*bx + ay*by;
    }

    public static double normalize(double in, double inMin, double inMax, double outMin, double outMax) {
        return ((in - inMin) / (inMax - inMin)) * (outMax - outMin) + outMin;
    }


    public static int fastFloor(double x) {
        int xi = (int)x;
        return x < xi ? xi - 1 : xi;
    }


    public static void main(String[] args) {
        System.out.println(normalize(1.0, 0, 10, -1.0, 1.0));
        System.out.println(normalize(1.0 + 2.0, 0, 10, -1.0, 1.0));
        System.out.println(normalize(1.0 + 2.0 + 3.0, 0, 10, -1.0, 1.0));
        System.out.println(normalize(1.0 + 2.0 + 3.0 + 4.0, 0, 10, -1.0, 1.0));
    }
}