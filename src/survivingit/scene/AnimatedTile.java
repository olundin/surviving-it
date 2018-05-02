package survivingit.scene;

import survivingit.graphics.AnimatedSprite;

/**
 * Animated tile class.
 * Has to be updated.
 *
 * @see Tile
 */
public class AnimatedTile extends Tile {

    private AnimatedSprite sprite;

    /**
     * Creates a new animated tile
     * @param sprite The animated sprite to use
     * @param passable
     * @param fertile
     */
    public AnimatedTile(AnimatedSprite sprite, boolean passable, boolean fertile) {
        super(sprite.getSprite(), passable, fertile);
        this.sprite = sprite;
    }

    /**
     * Update animated tile
     * @param dt Time since last game tick
     */
    public void update(double dt) {
        sprite.update(dt);
        this.setSprite(sprite.getSprite());
    }

}
