package survivingit.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

    public static final SpriteSheet FOXES = new SpriteSheet("foxes.png");
    public static final SpriteSheet MEME_MAN = new SpriteSheet("mememan.png");

    private int width;
    private int height;
    private BufferedImage image;

    public SpriteSheet(String path) {
        try {
	    this.image = ImageIO.read(new File("res/spritesheets/", path));
	    this.width = image.getWidth();
	    this.height = image.getHeight();
	} catch (IOException e)  {
	    System.out.println("Error reading image " + path);
	}
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public BufferedImage getImage() {
	return image;
    }

}
