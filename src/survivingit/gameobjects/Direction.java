package survivingit.gameobjects;

import survivingit.util.Maths;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Direction {

    LEFT(-1, 0),
    UP_LEFT(-Maths.DIAGONAL_SPEED, -Maths.DIAGONAL_SPEED),
    UP(0, -1),
    UP_RIGHT(Maths.DIAGONAL_SPEED, -Maths.DIAGONAL_SPEED),
    RIGHT(1, 0),
    DOWN_RIGHT(Maths.DIAGONAL_SPEED, Maths.DIAGONAL_SPEED),
    DOWN(0, 1),
    DOWN_LEFT(-Maths.DIAGONAL_SPEED, Maths.DIAGONAL_SPEED),
    NONE(0, 0);

    public final double x;
    public final double y;

    Direction(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Direction random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static Direction fromAngle(double angle) {
        angle %= 2 * Math.PI;
        double maxDiff = Math.PI / 8; // 2 * pi / (8 * 2)
        for(int d = 0; d < SIZE; d++) {
            Direction dir = VALUES.get(d);
            if(dir == NONE) continue;
            double dirAngle = Math.atan2(dir.x, dir.y);
            if(Math.abs(angle - dirAngle) <= maxDiff) return dir;
        }
        return NONE;

    }

}