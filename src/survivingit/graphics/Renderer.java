package survivingit.graphics;

import survivingit.gameobjects.Animal;
import survivingit.gameobjects.Camera;
import survivingit.gameobjects.Creature;
import survivingit.gameobjects.VisibleObject;
import survivingit.hud.Icon;
import survivingit.hud.ProgressBar;
import survivingit.physics.Collider;
import survivingit.scene.Tile;
import survivingit.util.Point;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas implements WorldRenderer, HudRenderer {

    private static final boolean DEBUG = true;

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

    private void drawRect(int x, int y, int width, int height, Color color, boolean fill) {
        graphics.setColor(color);
        if(fill) graphics.fillRect(x, y, width, height);
        else graphics.drawRect(x, y, width, height);
    }

    private void drawCircle(int x, int y, int r, Color color, boolean fill) {
        graphics.setColor(color);
        if(fill) graphics.fillOval(x - r, y - r, r * 2, r * 2);
        else graphics.drawOval(x - r, y - r, r * 2, r * 2);
    }

    private void drawText(int x, int y, String text, int size, Color color) {
        graphics.setFont(new Font(graphics.getFont().getFontName(), Font.PLAIN, size));
        graphics.setColor(color);
        graphics.drawString(text, x, y);
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

        if(DEBUG) {
            // Draw tile edges if it is not passable
            if(!tile.isPassable()) {
                this.drawRect(drawX, drawY,
                         drawWidth - 2*TILE_PADDING, drawHeight - 2*TILE_PADDING,
                         Color.blue, false);
            }
        }
    }

    public void drawObject(VisibleObject object, Camera camera) {
        int drawX = camera.worldToScreenX(object.getX());
        int drawY = camera.worldToScreenY(object.getY());

        double ppuX = camera.pixelsPerUnitX();
        double ppuY = camera.pixelsPerUnitY();
        Sprite sprite = object.getSprite();

        // Object sprites are rendered centered in x and the sprite's bottom is as the objects y
        drawX -= (int)(ppuX * sprite.getWidth() / UNIT_SIZE)/2;
        drawY -= (int)(ppuY * sprite.getHeight() / UNIT_SIZE);


        int drawWidth = (int)(ppuX * sprite.getWidth() / UNIT_SIZE);
        int drawHeight = (int)(ppuY * sprite.getHeight() / UNIT_SIZE);

        this.drawSprite(drawX, drawY, drawWidth, drawHeight, sprite);

        if(DEBUG) {
            // Draw object collider
            Collider col = object.getCollider();
            this.drawRect(camera.worldToScreenX(col.getWorldX()),
                     camera.worldToScreenY(col.getWorldY()),
                     (int)(col.getWidth() * ppuX),
                     (int)(col.getHeight() * ppuY),
                     Color.green, false);

            // Draw creature position
            this.drawText(drawX, drawY,
                          "pos=(" + Math.floor(object.getX()) + "," + Math.floor(object.getY()) + ")",
                          10, Color.black);

            if(object instanceof Creature) {
                // Draw creature health
                this.drawText(drawX, drawY + 10,
                              "health=" + ((Creature)object).getCurrentHealth() + "/" + ((Creature)object).getMaxHealth(),
                              10, Color.red);
            }

            if(object instanceof Animal) {
                // Draw animal path
                for(Point p : ((Animal)object).getPath()) {
                    int sx = camera.worldToScreenX(p.getX());
                    int sy = camera.worldToScreenY(p.getY());
                    this.drawCircle(sx, sy, 4, Color.orange, true);
                }
                // Draw view distance
                double viewDistance = ((Animal)object).getViewDistance();
                int sx = camera.worldToScreenX(object.getX() - viewDistance/2);
                int sy = camera.worldToScreenY(object.getY() - viewDistance/2);
                this.drawRect(sx, sy, (int)(viewDistance*ppuX), (int)(viewDistance*ppuY), Color.gray, false);
            }
        }
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
        int filledWidth = drawWidth * progressBar.getCurrent() / progressBar.getMax();
        int emptyWidth = drawWidth - filledWidth;
        this.drawSprite(drawX, drawY, filledWidth, drawHeight, progressBar.getFilled());
        this.drawSprite(drawX + filledWidth, drawY, emptyWidth, drawHeight, progressBar.getEmpty());

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
