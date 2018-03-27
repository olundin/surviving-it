package survivingit.gameobjects;

import survivingit.util.Maths;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Direction {

    LEFT(-1, 0),
    UP_LEFT(-Maths.DIAGONAL_LENGTH, -Maths.DIAGONAL_LENGTH),
    UP(0, -1),
    UP_RIGHT(Maths.DIAGONAL_LENGTH, -Maths.DIAGONAL_LENGTH),
    RIGHT(1, 0),
    DOWN_RIGHT(Maths.DIAGONAL_LENGTH, Maths.DIAGONAL_LENGTH),
    DOWN(0, 1),
    DOWN_LEFT(-Maths.DIAGONAL_LENGTH, Maths.DIAGONAL_LENGTH),
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

    public static Direction randomDirection() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}