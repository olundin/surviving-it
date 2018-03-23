package survivingit.gameobjects;

import survivingit.graphics.Sprite;
import survivingit.physics.Collider;

public class Player extends Creature {

    public Player(final double x, final double y) {
	    super(x, y, Sprite.HERO_DOWN, 50, 5);
	    this.setCollider(new Collider(0.5, 2.0, 1.0, 1.6, false, this));
    }

}
