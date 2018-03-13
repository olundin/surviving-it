package survivingit.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    public static final Sprite FOX = new Sprite(0, 0,46,57, SpriteSheet.FOXES);

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
