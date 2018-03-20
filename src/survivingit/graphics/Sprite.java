package survivingit.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    public static final Sprite SNOW_1 = new Sprite(80,80, 16, 16, SpriteSheet.TILES);
    public static final Sprite SNOW_2 = new Sprite(128,80, 16, 16, SpriteSheet.TILES);

    public static final Sprite FOX = new Sprite(0, 0,46,57, SpriteSheet.FOXES);
    public static final Sprite MEME_MAN = new Sprite(0,0, 17, 23, SpriteSheet.MEME_MAN);

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
