package survivingit.gameobjects;

import survivingit.graphics.Renderer;
import survivingit.graphics.Sprite;

public class Player extends Creature implements Updatable, Drawable {

    public Player(final float x, final float y, final Sprite sprite, final int health, final int moveSpeed) {
	super(x, y, sprite, health, moveSpeed);
    }

    public void update() {

    }

    public void render(Renderer renderer) {

    }

}
