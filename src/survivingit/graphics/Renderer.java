package survivingit.graphics;

import survivingit.gameobjects.Camera;
import survivingit.gameobjects.GameVisibleObject;
import survivingit.hud.Icon;
import survivingit.hud.ProgressBar;
import survivingit.scene.Tile;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas implements WorldRenderer, HudRenderer {

    private static final boolean DEBUG = false;

    public static final int UNIT_SIZE = 32; // Size of 1 game unit in pixels on image
    private static final int TILE_PADDING = 1; // Extra padding to be added to sprite size when rendering tiles

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
     *  PRIMITIVE DRAWING FUNCTIONS
     */

    private void drawSprite(int x, int y, int width, int height, Sprite sprite) {
        graphics.drawImage(
                sprite.getImage(),
                x,
                y,
                x + width,
                y + height,
                sprite.getX(),
                sprite.getY(),
                sprite.getX() + sprite.getWidth(),
                sprite.getY() + sprite.getHeight(),
                null
        );
    }

    /*
     *  WORLD RENDERER
     */

    public void drawTile(int x, int y, Tile tile, Camera camera) {
        int drawX = camera.worldToScreenX(x) - TILE_PADDING;
        int drawY = camera.worldToScreenY(y) - TILE_PADDING;

        int drawWidth = (int)(camera.pixelsPerUnitX() * tile.getSprite().getWidth() / UNIT_SIZE) + 2*TILE_PADDING;
        int drawHeight = (int)(camera.pixelsPerUnitY() * tile.getSprite().getHeight() / UNIT_SIZE) + 2*TILE_PADDING;

        this.drawSprite(drawX, drawY, drawWidth, drawHeight, tile.getSprite());
    }

    public void drawObject(GameVisibleObject object, Camera camera) {
        int drawX = camera.worldToScreenX(object.getX());
        int drawY = camera.worldToScreenY(object.getY());

        int drawWidth = (int)(camera.pixelsPerUnitX() * object.getSprite().getWidth() / UNIT_SIZE);
        int drawHeight = (int)(camera.pixelsPerUnitY() * object.getSprite().getHeight() / UNIT_SIZE);

        this.drawSprite(drawX, drawY, drawWidth, drawHeight, object.getSprite());
    }

    /*
     *  HUD RENDERER
     */

    public void drawProgressBar(ProgressBar progressBar) {
        // Convert element position and size (percentage 0 - 100) to screen position and size
        int drawX = (int) (progressBar.getX() / 100 * this.width);
        int drawY = (int) (progressBar.getY() / 100 * this.height);
        int drawWidth = (int) (progressBar.getWidth() / 100 * this.width);
        int drawHeight = (int) (progressBar.getHeight() / 100 * this.height);

        int pixelsPerStep = drawWidth / progressBar.getMax();

        // Draw left edge
        this.drawSprite(drawX - progressBar.getLeftEdge().getWidth() * pixelsPerStep,
                        drawY,
                        progressBar.getLeftEdge().getWidth() * pixelsPerStep,
                        drawHeight,
                        progressBar.getLeftEdge());

        // Draw fill
        double filledWidth = drawWidth * progressBar.getCurrent() / progressBar.getMax();
        double emptyWidth = drawWidth - filledWidth;
        this.drawSprite(drawX, drawY, (int)filledWidth, drawHeight, progressBar.getFilled());
        this.drawSprite(drawX + (int)filledWidth, drawY, (int)emptyWidth, drawHeight, progressBar.getEmpty());

        // Draw right edge
        this.drawSprite(drawX + drawWidth,
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
        this.drawSprite(drawX, drawY, drawWidth, drawHeight, icon.getSprite());
    }
}
