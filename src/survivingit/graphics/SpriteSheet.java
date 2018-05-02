package survivingit.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A spritesheet is a larger image that can be clipped by sprites to create smaller ones.
 */
public final class SpriteSheet {

    /**
     * Static SpriteSheet for Player sprites.
     */
    public static final SpriteSheet PLAYER = new SpriteSheet("hero.png", 72, 208);

    /**
     * Static SpriteSheet for tiles sprites.
     */
    public static final SpriteSheet TILES = new SpriteSheet("tiles.png");

    /**
     * Static SpriteSheet for tree sprites.
     */
    public static final SpriteSheet TREES = new SpriteSheet("trees.png");

    /**
     * Static SpriteSheet for Fox sprites.
     */
    public static final SpriteSheet FOX = new SpriteSheet("fox.png");

    /**
     * Static SpriteSheet for Boar sprites.
     */
    public static final SpriteSheet BOAR = new SpriteSheet("boar.png");

    /**
     * Static SpriteSheet for Penguin sprites.
     */
    public static final SpriteSheet PENGUIN = new SpriteSheet("penguin.png");

    /**
     * Static SpriteSheet for MemeMan/Yeti sprites.
     */
    public static final SpriteSheet MEME_MAN = new SpriteSheet("mememan.png");

    /**
     * Static SpriteSheet for CampFire sprites.
     */
    public static final SpriteSheet CAMP_FIRE = new SpriteSheet("campfire.png");

    /**
     * Static SpriteSheet for HUD sprites.
     */
    public static final SpriteSheet HUD = new SpriteSheet("hud.png");

    /**
     * Static SpriteSheet for Boots sprites.
     */
    public static final SpriteSheet BOOTS = new SpriteSheet("boots.png");

    /**
     * Static SpriteSheet for Knife sprites.
     */
    public static final SpriteSheet KNIFE = new SpriteSheet("knife.png");

    private int width;
    private int height;
    private BufferedImage image;

    /*
     * Creates a new SpriteSheet object with the image of the entered path.
     * @param path String of the path for the image of the new SpriteSheet object.
     */
    private SpriteSheet(String path) {
        // Reads width and height from image itself
        try {
            this.image = ImageIO.read(new File("res/spritesheets/", path));
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (IOException e)  {
            System.out.println("Error reading image " + path);
        }
    }

    private SpriteSheet(String path, int width, int height) {
        // Take width and height as parameters, to make a spritsheet that only contains part of spritesheet
        try {
            this.image = ImageIO.read(new File("res/spritesheets/", path));
            this.width = width;
            this.height = height;
        } catch (IOException e)  {
            System.out.println("Error reading image " + path);
        }
    }

    /**
     * Returns an array representation of the relevant Sprites in the SpriteSheet object based on the entered
     * startX, startY, spriteWidth, spriteHeight, nX and nY.
     * @param startX int value of the start x position of the relevant Sprites in the SpriteSheet.
     * @param startY int value of the start y position of the relevant Sprites in the SpriteSheet.
     * @param spriteWidth int value of the width of each relevant Sprite in the SpriteSheet.
     * @param spriteHeight int value of the height of each relevant Sprite in the SpriteSheet.
     * @param nX int value of number of relevant Sprites in the x dimension in the SpriteSheet.
     * @param nY int value of number of relevant Sprites in the y dimension in the SpriteSheet.
     * @return array representation of the relevant Sprites in the SpriteSheet.
     */
    public Sprite[] toArray(int startX, int startY, int spriteWidth,
                                        int spriteHeight, int nX, int nY) {
        Sprite[] sprites = new Sprite[nX * nY];
        for (int y = 0; y < nY; y++) {
            for (int x = 0; x < nX; x++) {
                sprites[x + y * nX] = new Sprite(x * spriteWidth + startX, y * spriteHeight + startY,
                        spriteWidth, spriteHeight, this);
            }
        }
        return sprites;
    }

    /**
     * Returns the width of the spriteSheet.
     * @return int value of the width of the SpriteSheet.
     */
    public int getWidth() {
	    return width;
    }

    /**
     * Returns the height of the spriteSheet.
     * @return int value of the height of the spriteSheet.
     */
    public int getHeight() {
	    return height;
    }

    /**
     * Returns the image of the spriteSheet.
     * @return BufferedImage of the image of the spriteSheet.
     */
    public BufferedImage getImage() {
	    return image;
    }

}
