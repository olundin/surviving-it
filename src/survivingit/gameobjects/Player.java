package survivingit.gameobjects;

import survivingit.graphics.Renderer;
import survivingit.graphics.Sprite;

public class Player extends Creature implements Updateable, Drawable {

    public Player(final float x, final float y, final Sprite sprite, final int health, final float moveSpeed) {
	super(x, y, sprite, health, moveSpeed);
    }

    public void update() {
    }
}
