package survivingit.gameobjects;

import survivingit.util.Vec2;

public enum Direction {

    UP(Vec2.UP), DOWN(Vec2.DOWN), LEFT(Vec2.LEFT), RIGHT(Vec2.RIGHT);

    public final Vec2 vec2;

    Direction(final Vec2 deltaPos) {
	this.vec2 = deltaPos;
    }

}
