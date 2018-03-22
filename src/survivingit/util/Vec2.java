package survivingit.util;

public class Vec2 {

    public static final Vec2 UP = new Vec2(0, -1);
    public static final Vec2 DOWN = new Vec2(0, 1);
    public static final Vec2 LEFT = new Vec2(-1, 0);
    public static final Vec2 RIGHT = new Vec2(1, 0);

    public double x;
    public double y;

    public Vec2(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        Vec2 zeros = new Vec2(0, 0);
        Vec2 oneZero = new Vec2(1, 0);
        Vec2 zeroOne = new Vec2(0, 1);
        Vec2 twoZero = new Vec2(2, 0);
        Vec2 zeroTwo = new Vec2(0, 2);

        Vec2 negativePos = new Vec2(-1, 1);
        Vec2 posNegative = new Vec2(1, -1);
        Vec2 negativeNegative = new Vec2(-1, -1);
        Vec2 posPos = new Vec2(1, 1);

        System.out.println(Vec2.sub(zeros, oneZero));
    }

    public static Vec2 add(Vec2 vec2a, Vec2 Vec2b) {
        return new Vec2(vec2a.x + Vec2b.x, vec2a.y + Vec2b.y);
    }

    public static Vec2 addConst(Vec2 vec2, double c) {
        return new Vec2(vec2.x + c, vec2.y + c);
    }

    public static Vec2 sub(Vec2 vec2a, Vec2 vec2b) {
        return new Vec2(vec2a.x - vec2b.x, vec2a.y - vec2b.y);
    }

    public static Vec2 subConst(Vec2 vec2, double c) {
        return new Vec2(vec2.x - c, vec2.x - c);
    }

    public static Vec2 mult(double scalar, Vec2 vec2) {
        return new Vec2(vec2.x*scalar, vec2.y*scalar);
    }

    public static Vec2 div(double scalar, Vec2 vec2) {
        if (scalar == 0) {
            throw new IllegalArgumentException("Illegal divison by zero");
        }
        return new Vec2(vec2.x/scalar, vec2.y/scalar);
    }

    public static boolean between(Vec2 target, Vec2 start, Vec2 end) {
        return start.x <= target.x && start.y <= target.y && end.x >= target.x && end.y >= target.y;
    }

    @Override
    public String toString() {
        return "" + x + ", " + y;
    }
}
