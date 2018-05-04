package survivingit.graphics;

import java.awt.*;

/**
 * Class for a Sprite that is used for rendering.
 *
 * Contains multiple public static fields of Sprites that are used in many classes in alignment with the FlyWeight
 * software design pattern to only store shared data in a single place in the memory.
 *
 * The main usage of the Sprite class is in calls to the getImage() functions that returns the current Sprite which is
 * used for drawing.
 *
 * @see SpriteSheet
 */
public class Sprite {

    /*
     * TILE SPRITES
     */

    /**
     * Static array of Sprites for snow tiles.
     */
    public static final Sprite[] SNOW = SpriteSheet.TILES.toArray(0, 0, 32,
            32, 3, 3);

    /**
     * Static array of Sprites for rock tiles.
     */
    public static final Sprite[] ROCK = SpriteSheet.TILES.toArray(96, 0, 32,
            32, 3, 3);

    /**
     * Static array of Sprites for ice tiles.
     */
    public static final Sprite[] ICE =  SpriteSheet.TILES.toArray(192, 0, 32,
            32, 3, 3);

    /**
     * Static array of Sprites for water tiles.
     */
    public static final Sprite[] WATER = SpriteSheet.TILES.toArray(288, 0, 32,
            32, 3, 3);

    /**
     * Static array of Sprites for void tiles.
     */
    public static final Sprite[] VOID = SpriteSheet.TILES.toArray(384, 0, 32,
            32, 3, 3);

    /**
     * Static array of Sprites for wall tiles.
     */
    public static final Sprite WALL = new Sprite(480, 0, 32, 32, SpriteSheet.TILES);

    /*
     * GAMEOBJECT SPRITES
     */

    /**
     * Static Sprite for a lit Campfire.
     */
    public static final Sprite CAMPFIRE = new Sprite(0, 0, 32, 32, SpriteSheet.CAMP_FIRE);

    /**
     * Static Sprite for an unlit Campfire.
     */
    public static final Sprite CAMPFIRE_UNLIT = new Sprite(160, 0, 32, 32, SpriteSheet.CAMP_FIRE);

    /**
     * Static Sprite for a Pine tree.
     */
    public static final Sprite PINE = new Sprite(0, 0, 86, 166, SpriteSheet.TREES);

    //
    //  HUD SPRITES
    //

    /**
     * Static Sprite for the left edge of a ProgressBar.
     */
    public static final Sprite PROGRESS_BAR_EDGE_LEFT = new Sprite(0, 0, 3, 12, SpriteSheet.HUD);

    /**
     * Static Sprite for the right edge of a ProgressBar.
     */
    public static final Sprite PROGRESS_BAR_EDGE_RIGHT = new Sprite(4, 0, 3, 12, SpriteSheet.HUD);

    /**
     * Static Sprite for the empty fill of a ProgressBar.
     */
    public static final Sprite PROGRESS_BAR_FILL_EMPTY = new Sprite(3, 0, 1, 12, SpriteSheet.HUD);

    /**
     * Static Sprite for the red fill of a ProgressBar.
     */
    public static final Sprite PROGRESS_BAR_FILL_RED = new Sprite(3, 12, 1, 12, SpriteSheet.HUD);

    /**
     * Static Sprite for a heart icon.
     */
    public static final Sprite ICON_HEART = new Sprite(7, 12, 9, 12, SpriteSheet.HUD);

    /**
     * Static Sprite for an item slot.
     */
    public static final Sprite ITEM_SLOT = new Sprite(26, 30, 16, 16, SpriteSheet.HUD);

    /**
     * Static Sprite for a selected item slot.
     */
    public static final Sprite ITEM_SLOT_SELECTED = new Sprite(43, 30, 16, 16, SpriteSheet.HUD);

    //
    //  ITEM SPRITES
    //
    /**
     * Static Sprite for golden shoes Item icon.
     */
    public static final Sprite GYLLENE_SKOR = new Sprite(0, 0, 400, 257, SpriteSheet.BOOTS);

    /**
     * Static Sprite for a knife Item icon.
     */
    public static final Sprite KNIFE = new Sprite(0, 0, 24, 24, SpriteSheet.KNIFE);

    /**
     * Static Sprite for a spear Item icon.
     */
    public static final Sprite SPEAR = new Sprite(0, 0, 395, 395, SpriteSheet.SPEAR);

    private int startX;
    private int startY;
    private int width;
    private int height;

    private SpriteSheet spriteSheet;

    /**
     * Creates a new Sprite object with the entered SpriteSheet, startX, startY, width and height.
     * @param spriteSheet SpriteSheet for the Sprite.
     * @param startX int value of the x position of where the sprite starts in the spriteSheet.
     * @param startY int value of the y position of where the sprite starts in the spriteSheet.
     * @param width int value of the width of the spite in the spriteSheet.
     * @param height int value of the height of the sprite in the spriteSheet.
     */
    public Sprite(int startX, int startY, int width, int height, SpriteSheet spriteSheet) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;

        this.spriteSheet = spriteSheet;
    }

    /**
     * Returns the startX of the Sprite.
     * @return int value of the startX of the Sprite.
     */
    public int getStartX() {
	    return startX;
    }

    /**
     * Returns the startY of the Sprite.
     * @return int value of the startY of the Sprite.
     */
    public int getStartY() {
	    return startY;
    }

    /**
     * Returns the width of the Sprite.
     * @return int value of the width of the Sprite.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the Sprite.
     * @return int value of the height of the Sprite.
     */
    public int getHeight() {
	    return height;
    }

    /**
     * Returns the image of the Sprite.
     * @return Image object of the Sprite.
     */
    public Image getImage() {
        return spriteSheet.getImage();
    }

}
