package survivingit.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    public static final Sprite SNOW_PLAIN = new Sprite(0, 0, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_BUMPY = new Sprite(32, 0, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_ROCK = new Sprite(64, 0, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_BUSH = new Sprite(96, 0, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_PUDDLE = new Sprite(0, 32, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_TRACKS = new Sprite(32, 32, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_BRANCH = new Sprite(64, 32, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_PEBBLES = new Sprite(96, 32, 32, 32, SpriteSheet.SNOW_TILES);

    public static final Sprite WATER_LEFT           = new Sprite(0, 96, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite WATER_TOP_LEFT       = new Sprite(0, 64, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite WATER_TOP            = new Sprite(32, 64, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite WATER_TOP_RIGHT      = new Sprite(64, 64, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite WATER_RIGHT          = new Sprite(96, 96, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite WATER_BOTTOM_RIGHT   = new Sprite(96, 128, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite WATER_BOTTOM         = new Sprite(32, 128, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite WATER_BOTTOM_LEFT    = new Sprite(0, 128, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite WATER                = new Sprite(32, 96, 32, 32, SpriteSheet.SNOW_TILES);

    public static final Sprite CAMPFIRE = new Sprite(0, 0, 32, 32, SpriteSheet.CAMP_FIRE);
    public static final Sprite CAMPFIRE_UNLIT = new Sprite(160, 0, 32, 32, SpriteSheet.CAMP_FIRE);

    public static final Sprite PROGRESS_BAR_EDGE_LEFT = new Sprite(0, 0, 3, 12, SpriteSheet.HUD);
    public static final Sprite PROGRESS_BAR_EDGE_RIGHT = new Sprite(4, 0, 3, 12, SpriteSheet.HUD);

    public static final Sprite PROGRESS_BAR_FILL_EMPTY = new Sprite(3, 0, 1, 12, SpriteSheet.HUD);
    public static final Sprite PROGRESS_BAR_FILL_RED = new Sprite(3, 12, 1, 12, SpriteSheet.HUD);
    public static final Sprite PROGRESS_BAR_FILL_BLUE = new Sprite(3, 24, 1, 12, SpriteSheet.HUD);
    public static final Sprite PROGRESS_BAR_FILL_YELLOW = new Sprite(3, 36, 1, 12, SpriteSheet.HUD);

    public static final Sprite ICON_HEART = new Sprite(7, 12, 9, 12, SpriteSheet.HUD);

    public static final Sprite BELT = new Sprite(21, 0, 43, 29, SpriteSheet.HUD);
    public static final Sprite ITEM_SLOT = new Sprite(26, 30, 16, 16, SpriteSheet.HUD);
    public static final Sprite ITEM_SLOT_SELECTED = new Sprite(43, 30, 16, 16, SpriteSheet.HUD);

    private int x;
    private int y;
    private int width;
    private int height;

    private SpriteSheet spriteSheet;

    public Sprite(int x, int y, int width, int height, SpriteSheet spriteSheet) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.spriteSheet = spriteSheet;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public Image getImage() {
        return spriteSheet.getImage();
    }
}
