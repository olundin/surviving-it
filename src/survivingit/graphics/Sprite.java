package survivingit.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    public static final Sprite[] HERO_LEFT = {
        new Sprite(0, 0, 24, 52, SpriteSheet.HERO),
        new Sprite(24, 0, 24, 52, SpriteSheet.HERO),
        new Sprite(48, 0, 24, 52, SpriteSheet.HERO),
        new Sprite(72, 0, 24, 52, SpriteSheet.HERO),
        new Sprite(96, 0, 24, 52, SpriteSheet.HERO)
    };
    public static final Sprite[] HERO_UP = {
        new Sprite(0, 52, 24, 52, SpriteSheet.HERO),
        new Sprite(24, 52, 24, 52, SpriteSheet.HERO),
        new Sprite(48, 52, 24, 52, SpriteSheet.HERO),
        new Sprite(72, 52, 24, 52, SpriteSheet.HERO),
        new Sprite(96, 52, 24, 52, SpriteSheet.HERO)
    };
    public static final Sprite[] HERO_RIGHT = {
        new Sprite(0, 104, 24, 52, SpriteSheet.HERO),
        new Sprite(24, 104, 24, 52, SpriteSheet.HERO),
        new Sprite(48, 104, 24, 52, SpriteSheet.HERO),
        new Sprite(72, 104, 24, 52, SpriteSheet.HERO),
        new Sprite(96, 104, 24, 52, SpriteSheet.HERO)
    };
    public static final Sprite[] HERO_DOWN = {
        new Sprite(0, 156, 24, 52, SpriteSheet.HERO),
        new Sprite(24, 156, 24, 52, SpriteSheet.HERO),
        new Sprite(48, 156, 24, 52, SpriteSheet.HERO),
        new Sprite(72, 156, 24, 52, SpriteSheet.HERO),
        new Sprite(96, 156, 24, 52, SpriteSheet.HERO)
    };

    public static final Sprite SNOW_PLAIN   = new Sprite(0, 0, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_BUMPY   = new Sprite(32, 0, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_ROCK    = new Sprite(64, 0, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_BUSH    = new Sprite(96, 0, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_PUDDLE  = new Sprite(0, 32, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_TRACKS  = new Sprite(32, 32, 32, 32, SpriteSheet.SNOW_TILES);
    public static final Sprite SNOW_BRANCH  = new Sprite(64, 32, 32, 32, SpriteSheet.SNOW_TILES);
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
