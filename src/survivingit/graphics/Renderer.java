package survivingit.graphics;

import survivingit.gameobjects.GameVisibleObject;
import survivingit.hud.ProgressBar;
import survivingit.physics.Collider;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas {

    private static final boolean DEBUG = false;

    public static final int UNIT_SIZE = 32; // Size of 1 game unit in pixels
    private static final int SPRITE_PADDING = 1; // Extra padding to be added to sprite size when rendering

    private int width;
    private int height;

    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private GraphicsEnvironment graphicsEnvironment;

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

    /*
     WORLD - Takes world coordinates
    */

    public void drawSprite(double x, double y, Sprite sprite, double camX, double camY, double camWidth, double camHeight, boolean centered) {

        // Pixels per unit (ppu)
        double ppuWidth = this.width / camWidth;
        double ppuHeight = this.height / camHeight;


        // Position and size on screen
        int drawX = (int)((x - camX) * ppuWidth) - SPRITE_PADDING;
        int drawY = (int)((y - camY) * ppuHeight) - SPRITE_PADDING;
        int drawWidth = (int)(ppuWidth * sprite.getWidth() / UNIT_SIZE) + SPRITE_PADDING * 2;
        int drawHeight = (int)(ppuHeight * sprite.getHeight() / UNIT_SIZE) + SPRITE_PADDING * 2;


        if(centered) {
            drawX -= (int)(ppuWidth * sprite.getWidth() / UNIT_SIZE)/2;
            drawY -= (int)(ppuHeight * sprite.getHeight() / UNIT_SIZE)/2;
        }

        graphics.drawImage(sprite.getImage(), drawX, drawY, drawX + drawWidth, drawY + drawHeight,
			   sprite.getX(), sprite.getY(), sprite.getX() + sprite.getWidth(), sprite.getY() + sprite.getHeight(),
			   null);

        if(DEBUG) {
            // Draw sprite borders
            graphics.setColor(Color.black);
            graphics.drawRect(drawX, drawY, drawWidth, drawHeight);
	    }
    }

    public void drawRect(double x, double y, double width, double height, Color color, double camX, double camY, double camWidth, double camHeight) {
        // Pixels per unit (ppu)
        double ppuWidth = this.width / camWidth;
        double ppuHeight = this.height / camHeight;

        int drawX = (int)((x - camX) * ppuWidth);
        int drawY = (int)((y - camY) * ppuHeight);
        int drawWidth = (int)(ppuWidth * width);
        int drawHeight = (int)(ppuHeight * height);

        graphics.setColor(color);
        graphics.drawRect(drawX, drawY, drawWidth, drawHeight);
    }

    public void drawVisibleObject(GameVisibleObject object, double camX, double camY, double camWidth, double camHeight) {
        // Draw sprite of GameObject
        this.drawSprite(object.getX(), object.getY(), object.getSprite(), camX, camY, camWidth, camHeight, true);

        if(DEBUG) {
            // Draw hitbox
            Collider col = (object).getCollider();
            this.drawRect(col.getWorldX(), col.getWorldY(), col.getWidth(), col.getHeight(), Color.cyan, camX, camY, camWidth, camHeight);
        }
    }


    /*
     HUD - takes screen coordinates
      */

    public void drawProgressBar(ProgressBar progressBar) {
        final int padding = 2;
        int drawX = (int) (progressBar.getX() / 100 * this.width);
        int drawY = (int) (progressBar.getY() / 100 * this.height);
        int drawWidth = (int) (progressBar.getWidth() / 100 * this.width);
        int drawHeight = (int) (progressBar.getHeight() / 100 * this.height);

        // Draw rect behind bar
        graphics.setColor(Color.black);
        graphics.fillRect(drawX - padding, drawY - padding, drawWidth + 2 * padding, drawHeight + 2 * padding);

        // Draw bar itself, with current progress
        graphics.setColor(progressBar.getColor());
        graphics.fillRect(drawX, drawY, drawWidth * progressBar.getCurrent() / progressBar.getMax(), drawHeight);
    }
}
