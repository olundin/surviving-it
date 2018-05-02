package survivingit.scene;

import survivingit.graphics.AnimatedSprite;
import survivingit.graphics.Sprite;

/**
 * The tile class. Fills one coordinate in the scene.
 * All tiles should be static since they contain no unique information.
 *
 * @see Scene
 * @see AnimatedTile
 */
public class Tile {

    /**
     * Snow tile group
     */
    public static final TileGroup SNOW = new TileGroup(Sprite.SNOW, new boolean[]{true,false,false,true,true,true,true,true,true}, true);
    /**
     * Rock tile group
     */
    public static final TileGroup ROCK = new TileGroup(Sprite.ROCK, new boolean[]{true,true,true,true,true,true,true,true,true}, false);
    /**
     * Ice tile group
     */
    public static final TileGroup ICE = new TileGroup(Sprite.ICE, new boolean[]{true,true,true,true,true,true,true,true,true}, false);

    /**
     * Water tile, animated. Is updated in the main game loop.
     */
    public static final AnimatedTile WATER = new AnimatedTile(new AnimatedSprite(Sprite.WATER, 0.2), false, false);
    /**
     * Void tile, animated. Is updated in the main game loop.
     */
    public static final AnimatedTile VOID = new AnimatedTile(new AnimatedSprite(Sprite.VOID, 0.5), false, false);

    /**
     * Wall tile
     */
    public static final Tile WALL = new Tile(Sprite.WALL, false, false);

    private Sprite sprite;
    private boolean passable;
    private boolean fertile;

    /**
     * Creates a new tile
     * @param sprite The sprite to use
     * @param passable Is the tile passable?
     * @param fertile Is the tile fertile?
     */
    public Tile(Sprite sprite, boolean passable, boolean fertile) {
        this.sprite = sprite;
        this.passable = passable;
        this.fertile = fertile;
    }

    /**
     * Updates the animated tiles.
     * @param dt Time since last game tick
     */
    public static void updateAnimated(double dt) {
        WATER.update(dt);
        VOID.update(dt);
    }

    /**
     * Returns the tile's sprite
     * @return The sprite of the tile
     */
    public Sprite getSprite() {
	    return sprite;
    }

    /**
     * Sets the tile's sprite
     * @param sprite The new sprite
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Returns whether the tile is passable or not
     * @return Tile passable status
     */
    public boolean isPassable() {
        return this.passable;
    }

    /**
     * Returns whether the tile is fertile or not
     * @return Tile fertility status
     */
    public boolean isFertile() { return this.fertile; }
}