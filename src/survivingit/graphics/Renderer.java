package survivingit.graphics;

import survivingit.gameobjects.GameVisibleObject;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas {

    public static final int STANDARD_TILE_SIZE = 64; // Tile size in pixels when 1:1 scale (represents one unit in game)

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
	drawSprite(100, 100, Sprite.FOX);
    }

    public void display() {
        graphics.dispose(); // Release system resources
	bufferStrategy.show();
    }


    public void drawSprite(int x, int y, Sprite sprite) {
	graphics.drawImage(sprite.getImage(),
			   x,
			   y,
			   x + sprite.getWidth(),
			   y + sprite.getHeight(),
			   sprite.getX(),
			   sprite.getY(),
			   sprite.getX() + sprite.getWidth(),
			   sprite.getY() + sprite.getHeight(),
			   null);
    }

    public void drawVisibleObject(GameVisibleObject gameObject, double tileX, double tileY, double scale) {
	double currentTileSize = scale * STANDARD_TILE_SIZE;
        Sprite sprite = gameObject.getSprite();
        int startX = (int) (tileX * currentTileSize);
        int startY = (int) (tileY * currentTileSize);
        graphics.drawImage(sprite.getImage(),
        			   startX,
        			   startY,
        			   startX + (int) (sprite.getWidth() * scale),
        			   startY + (int) (sprite.getHeight() * scale),
        			   sprite.getX(),
        			   sprite.getY(),
        			   sprite.getX() + sprite.getWidth(),
        			   sprite.getY() + sprite.getHeight(),
        			   null);
    }
}
