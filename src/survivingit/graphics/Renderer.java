package survivingit.graphics;

import survivingit.gameobjects.GameVisibleObject;
import survivingit.scene.Tile;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas {

    public static final int UNIT_SIZE = 16; // Size of 1 game unit in pixels

    private int width;
    private int height;

    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    public Renderer(int width, int height) {
        super();

        this.width = width;
        this.height = height;

        this.setSize(width, height);
        this.setVisible(true);
        this.setFocusable(false);

    }

    public void prepare() {
	bufferStrategy = this.getBufferStrategy();
	graphics = bufferStrategy.getDrawGraphics();
    }

    public void clear() {
	graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
    }

    public void display() {
        graphics.dispose(); // Release system resources
	bufferStrategy.show();
    }

    public void drawSprite(double x, double y, Sprite sprite, double cameraWidth, double cameraHeight) {
	// Pixels per unit (ppu)
	double ppuWidth = this.width / cameraWidth;
	double ppuHeight = this.height / cameraHeight;

	// Position and size on screen
	int dX = (int)Math.floor(x * ppuWidth);
	int dY = (int)Math.floor(y * ppuHeight);
	int dWidth = (int)Math.ceil(ppuWidth * sprite.getWidth() / UNIT_SIZE);
	int dHeight = (int)Math.ceil(ppuHeight * sprite.getHeight() / UNIT_SIZE);


	graphics.drawImage(sprite.getImage(), dX, dY, dX + dWidth, dY + dHeight,
			   sprite.getX(), sprite.getY(), sprite.getX() + sprite.getWidth(), sprite.getY() + sprite.getHeight(),
			   null);


    }
}
