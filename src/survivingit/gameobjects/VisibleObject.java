package survivingit.gameobjects;

import survivingit.graphics.Sprite;
import survivingit.physics.Collider;

public abstract class VisibleObject extends GameObject {

    protected Sprite sprite;

    public VisibleObject(final double x, final double y, final Sprite sprite) {
	    super(x, y);
	    this.sprite = sprite;
    }

    public Sprite getSprite() {
	    return sprite;
    }

    public void setSprite(final Sprite sprite) {
	    this.sprite = sprite;
    }

}
