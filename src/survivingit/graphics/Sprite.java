package survivingit.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    //
    //  TILE SPRITES
    //
    public static final Sprite[] SNOW = Sprite.sheetToArray(SpriteSheet.TILES, 0, 0, 32, 32, 3, 3);
    public static final Sprite[] ROCK = Sprite.sheetToArray(SpriteSheet.TILES, 96, 0, 32, 32, 3, 3);
    public static final Sprite[] ICE = Sprite.sheetToArray(SpriteSheet.TILES, 192, 0, 32, 32, 3, 3);
    public static final Sprite[] WATER = Sprite.sheetToArray(SpriteSheet.TILES, 288, 0, 32, 32, 3, 3);
    public static final Sprite[] VOID = Sprite.sheetToArray(SpriteSheet.TILES, 384, 0, 32, 32, 3, 3);

    public static final Sprite WALL = new Sprite(480, 0, 32, 32, SpriteSheet.TILES);

    //
    //  GAMEOBJECT SPRITES
    //
    public static final Sprite CAMPFIRE = new Sprite(0, 0, 32, 32, SpriteSheet.CAMP_FIRE);
    public static final Sprite CAMPFIRE_UNLIT = new Sprite(160, 0, 32, 32, SpriteSheet.CAMP_FIRE);
    public static final Sprite PINE = new Sprite(0, 0, 86, 166, SpriteSheet.TREES);

    //
    //  HUD SPRITES
    //
    public static final Sprite PROGRESS_BAR_EDGE_LEFT = new Sprite(0, 0, 3, 12, SpriteSheet.HUD);
    public static final Sprite PROGRESS_BAR_EDGE_RIGHT = new Sprite(4, 0, 3, 12, SpriteSheet.HUD);
    public static final Sprite PROGRESS_BAR_FILL_EMPTY = new Sprite(3, 0, 1, 12, SpriteSheet.HUD);
    public static final Sprite PROGRESS_BAR_FILL_RED = new Sprite(3, 12, 1, 12, SpriteSheet.HUD);
    public static final Sprite ICON_HEART = new Sprite(7, 12, 9, 12, SpriteSheet.HUD);
    public static final Sprite ITEM_SLOT = new Sprite(26, 30, 16, 16, SpriteSheet.HUD);
    public static final Sprite ITEM_SLOT_SELECTED = new Sprite(43, 30, 16, 16, SpriteSheet.HUD);

    //
    //  ITEM SPRITES
    //
    public static final Sprite GYLLENE_SKOR = new Sprite(0, 0, 400, 257, SpriteSheet.BOOTS);
    public static final Sprite KNIFE = new Sprite(0, 0, 24, 24, SpriteSheet.KNIFE);

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

    public static Sprite[] sheetToArray(SpriteSheet spriteSheet, int subX, int subY, int spriteWidth, int spriteHeight, int nX, int nY) {
        Sprite[] sprites = new Sprite[nX * nY];
        for(int y = 0; y < nY; y++) {
            for(int x = 0; x < nX; x++) {
                sprites[x + y * nX] = new Sprite(x * spriteWidth + subX, y * spriteHeight + subY, spriteWidth, spriteHeight, spriteSheet);
            }
        }
        return sprites;
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
