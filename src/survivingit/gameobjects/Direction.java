package survivingit.gameobjects;

import survivingit.Game;
import survivingit.util.Maths;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Enum class used for movement in different Directions.
 * Stores deltaX and deltaY in the enum values for more readable code.
 */
public enum Direction {

    /**
     * Left direction
     */
    LEFT(-1, 0),
    /**
     * Up left direction
     */
    UP_LEFT(-Maths.DIAGONAL_SPEED, -Maths.DIAGONAL_SPEED),
    /**
     * Up direction
     */
    UP(0, -1),
    /**
     * Up right direction
     */
    UP_RIGHT(Maths.DIAGONAL_SPEED, -Maths.DIAGONAL_SPEED),
    /**
     * Right direction
     */
    RIGHT(1, 0),
    /**
     * Down right direction
     */
    DOWN_RIGHT(Maths.DIAGONAL_SPEED, Maths.DIAGONAL_SPEED),
    /**
     * Down direction
     */
    DOWN(0, 1),
    /**
     * Down left direction
     */
    DOWN_LEFT(-Maths.DIAGONAL_SPEED, Maths.DIAGONAL_SPEED),
    /**
     * None direction
     */
    NONE(0, 0);

    /**
     * Double value of the deltaX the Direction has.
     */
    public final double deltaX;

    /**
     * Double value of the deltaY the Direction has.
     */
    public final double deltaY;

    /**
     * Creates a new enum value with the entered deltaX and deltaY values.
     * @param deltaX double of the deltaX for the enum value.
     * @param deltaY double of the deltaY for the enum value.
     */
    Direction(final double deltaX, final double deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    private static final List<Direction> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();

    /**
     * Returns a random enum Direction value.
     * @return a random enum Diretcion value.
     */
    public static Direction random() {
        return VALUES.get(Game.RANDOM.nextInt(SIZE));
    }

    /**
     * Returns the Direction value closest to the entered angle.
     * @param angle to get the closest enum value for.
     * @return the Direction value closest to the enterd angle.
     */
    public static Direction fromAngle(double angle) {
        angle %= 2 * Math.PI;
        if(angle >= 0) {
            if(angle < Math.PI*(1.0)/16) return DOWN;
            else if(angle < Math.PI*(7.0/16)) return DOWN_RIGHT;
            else if(angle < Math.PI*(9.0/16)) return RIGHT;
            else if(angle < Math.PI*(15.0/16)) return UP_RIGHT;
            else return UP;
        } else {
            // angle < 0
            if(angle >= -Math.PI*(-1.0)/16) return DOWN;
            else if(angle >= Math.PI*(-7.0/16)) return DOWN_LEFT;
            else if(angle >= Math.PI*(-9.0/16)) return LEFT;
            else if(angle >= Math.PI*(-15.0/16)) return UP_LEFT;
            else return UP;
        }
    }

}