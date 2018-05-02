package survivingit.scene;

import survivingit.graphics.AnimatedSprite;
import survivingit.graphics.Sprite;

/**
 * Animated tile class.
 * Has to be updated.
 *
 * @see Tile
 */
public class AnimatedTile extends Tile {

    /**
     * Water tile, animated. Is updated in the main game loop.
     */
    public static final AnimatedTile WATER = new AnimatedTile(new AnimatedSprite(Sprite.WATER, 0.2), false, false);
    /**
     * Void tile, animated. Is updated in the main game loop.
     */
    public static final AnimatedTile VOID = new AnimatedTile(new AnimatedSprite(Sprite.VOID, 0.5), false, false);

    private AnimatedSprite sprite;

    /**
     * Creates a new animated tile
     * @param sprite The animated sprite to use
     * @param passable Is the tile passable
     * @param fertile Is the tile fertile
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

    /**
     * Updates the animated tiles.
     * @param dt Time since last game tick
     */
    public static void updateAll(double dt) {
        WATER.update(dt);
        VOID.update(dt);
    }

}
