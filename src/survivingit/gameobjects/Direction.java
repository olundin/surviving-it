package survivingit.gameobjects;

import survivingit.util.Math;

public enum Direction {

    LEFT(-1, 0),
    UP_LEFT(-Math.DIAGONAL_LENGTH, -Math.DIAGONAL_LENGTH),
    UP(0, -1),
    UP_RIGHT(Math.DIAGONAL_LENGTH, -Math.DIAGONAL_LENGTH),
    RIGHT(1, 0),
    DOWN_RIGHT(Math.DIAGONAL_LENGTH, Math.DIAGONAL_LENGTH),
    DOWN(0, 1),
    DOWN_LEFT(-Math.DIAGONAL_LENGTH, Math.DIAGONAL_LENGTH),
    NONE(0, 0);

    public final double x;
    public final double y;

    Direction(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

}