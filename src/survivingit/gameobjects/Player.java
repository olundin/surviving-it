package survivingit.gameobjects;

import survivingit.graphics.Renderer;
import survivingit.graphics.Sprite;

public class Player extends Creature {

    public Player(final double x, final double y, final Sprite sprite, final int health, final double moveSpeed) {
	    super(x, y, sprite, health, moveSpeed);
    }

}
