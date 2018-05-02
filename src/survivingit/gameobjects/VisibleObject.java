package survivingit.gameobjects;

import survivingit.graphics.Sprite;

/**
 * Abstract superclass for all visible game objects, inherits from GameObject.
 *
 * Contains data and handling for rendering information which is stored in the Sprite.
 */
public abstract class VisibleObject extends GameObject {

    protected Sprite sprite;

    /**
     * Creates a new VisibleObject sprite with the entered deltaX, deltaY cords, and sprite.
     * @param x double of the initial deltaX coordinate for the VisibleObject.
     * @param y double of the initial deltaX coordinate for the VisibleObject.
     * @param sprite Sprite with the rendering data for the VisibleObject.
     */
    public VisibleObject(final double x, final double y, final Sprite sprite) {
	    super(x, y);
	    this.sprite = sprite;
    }

    /**
     * Returns the Sprite of the visibleObject.
     * @return the Sprite of the visibleObject.
     */
    public Sprite getSprite() {
	    return sprite;
    }

    /**
     * Sets the Sprite of the entered visibleObject to the entered Sprite.
     * @param sprite Sprite to be set for the visibleObject-
     */
    public void setSprite(final Sprite sprite) {
	    this.sprite = sprite;
    }

}
