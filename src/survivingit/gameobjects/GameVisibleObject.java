package survivingit.gameobjects;

import survivingit.graphics.Sprite;

public abstract class GameVisibleObject extends GameObject {

    private Sprite sprite;

    public GameVisibleObject(final double x, final double y, final Sprite sprite) {
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
