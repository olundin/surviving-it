package survivingit.graphics;

import survivingit.gameobjects.GameVisibleObject;
import survivingit.hud.Icon;
import survivingit.hud.ProgressBar;
import survivingit.physics.Collider;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas {

    private static final boolean DEBUG = false;

    public static final int UNIT_SCALE = 32; // Size of 1 game unit in pixels
    private static final int SPRITE_PADDING = 1; // Extra padding to be added to sprite size when rendering

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

    /*
     WORLD - Takes world coordinates
    */

    public void drawSpriteWorld(double x, double y, Sprite sprite, double camX, double camY, double camWidth, double camHeight) {

        // Pixels per unit (ppu)
        double ppuWidth = this.width / camWidth;
        double ppuHeight = this.height / camHeight;


        // Position and size on screen
        int drawX = (int)((x - camX) * ppuWidth) - SPRITE_PADDING;
        int drawY = (int)((y - camY) * ppuHeight) - SPRITE_PADDING;
        int drawWidth = (int)(ppuWidth * sprite.getWidth() / UNIT_SCALE) + SPRITE_PADDING * 2;
        int drawHeight = (int)(ppuHeight * sprite.getHeight() / UNIT_SCALE) + SPRITE_PADDING * 2;

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
        this.drawSpriteWorld(object.getX(), object.getY(), object.getSprite(), camX, camY, camWidth, camHeight);

        if(DEBUG) {
            // Draw hitbox
            Collider col = (object).getCollider();
            this.drawRect(col.getWorldX(), col.getWorldY(), col.getWidth(), col.getHeight(), Color.cyan, camX, camY, camWidth, camHeight);
        }
    }


    /*
     HUD - takes screen coordinates
      */

    public void drawSpriteScreen(int x, int y, int width, int height, Sprite sprite) {
        graphics.drawImage(sprite.getImage(),
                           x, y,
                           x + width,
                           y + height,
                           sprite.getX(),
                           sprite.getY(),
                           sprite.getX() + sprite.getWidth(),
                           sprite.getY() + sprite.getHeight(),
                           null);
    }

    public void drawProgressBar(ProgressBar progressBar) {
        // Convert element position and size (percentage 0 - 100) to screen position and size
        int drawX = (int) (progressBar.getX() / 100 * this.width);
        int drawY = (int) (progressBar.getY() / 100 * this.height);
        int drawWidth = (int) (progressBar.getWidth() / 100 * this.width);
        int drawHeight = (int) (progressBar.getHeight() / 100 * this.height);

        int pixelsPerStep = drawWidth / progressBar.getMax();

        // Draw left edge
        this.drawSpriteScreen(drawX - progressBar.getLeftEdge().getWidth() * pixelsPerStep,
                              drawY,
                              progressBar.getLeftEdge().getWidth() * pixelsPerStep,
                              drawHeight,
                              progressBar.getLeftEdge());

        // Draw fill
        double filledWidth = drawWidth * progressBar.getCurrent() / progressBar.getMax();
        double emptyWidth = drawWidth - filledWidth;
        this.drawSpriteScreen(drawX, drawY, (int)filledWidth, drawHeight, progressBar.getFilled());
        this.drawSpriteScreen(drawX + (int)filledWidth, drawY, (int)emptyWidth, drawHeight, progressBar.getEmpty());

        // Draw right edge
        this.drawSpriteScreen(drawX + drawWidth,
                              drawY,
                              progressBar.getRightEdge().getWidth() * pixelsPerStep,
                              drawHeight,
                              progressBar.getRightEdge());
    }

    public void drawIcon(Icon icon) {
        // Convert element position and size (percentage 0 - 100) to screen position and size
        int drawX = (int) (icon.getX() / 100 * this.width);
        int drawY = (int) (icon.getY() / 100 * this.height);
        int drawWidth = (int) (icon.getWidth() / 100 * this.width);
        int drawHeight = (int) (icon.getHeight() / 100 * this.height);

        // Draw icon
        this.drawSpriteScreen(drawX, drawY, drawWidth, drawHeight, icon.getSprite());
    }
}
