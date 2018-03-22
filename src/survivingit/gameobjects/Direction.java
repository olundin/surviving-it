package survivingit.gameobjects;

import survivingit.util.Maths;

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

}