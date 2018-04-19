package survivingit.graphics;

import survivingit.gameobjects.Animal;
import survivingit.gameobjects.Camera;
import survivingit.gameobjects.Creature;
import survivingit.gameobjects.VisibleObject;
import survivingit.containers.ItemContainer;
import survivingit.gameobjects.Camera;
import survivingit.gameobjects.GameVisibleObject;
import survivingit.hud.EquippedItemContainerHud;
import survivingit.hud.Icon;
import survivingit.hud.ItemContainerHud;
import survivingit.hud.ProgressBar;
import survivingit.items.Item;
import survivingit.items.ItemType;
import survivingit.physics.Collider;
import survivingit.scene.Tile;
import survivingit.util.Point;

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

    private void drawSprite(int x, int y, int width, int height, final Sprite sprite) {
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

    @Override
    public void drawTile(int x, int y, final Tile tile, final Camera camera) {
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

    @Override
    public void drawProgressBar(final ProgressBar progressBar) {
        // Convert element position and size (percentage 0 - 100) to screen position and size
        int drawX = drawValFromHudVal(progressBar.getX(), this.width);
        int drawY = drawValFromHudVal(progressBar.getY(), this.height);
        int drawWidth = drawValFromHudVal(progressBar.getWidth(), this.width);
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

    @Override
    public void drawIcon(final Icon icon) {
        // Convert element position and size (percentage 0 - 100) to screen position and size
        int drawX = drawValFromHudVal(icon.getX(), this.width);
        int drawY = drawValFromHudVal(icon.getY(), this.height);
        int drawWidth = drawValFromHudVal(icon.getWidth(), this.width);
        int drawHeight = drawValFromHudVal(icon.getHeight(), this.height);

        // Draw icon
        this.drawSprite(drawX, drawY, drawWidth, drawHeight, icon.getSprite());
    }

    @Override
    public void drawItemContainer(final ItemContainerHud itemContainerHud) {
        final ItemContainer itemContainer = itemContainerHud.getItemContainer();
        final int itemsPerColumn = itemContainerHud.getItemsPerRow();
        int size = itemContainer.getSize();
        int drawX = drawValFromHudVal(itemContainerHud.getX(), this.width);
        int drawY = drawValFromHudVal(itemContainerHud.getY(), this.height);

        Sprite itemSlotSprite = itemContainerHud.getItemSlotSprite();

        if (DEBUG) {
            this.drawRect(drawX, drawY, drawValFromHudVal(itemContainerHud.getWidth(), this.width),
                    drawValFromHudVal(itemContainerHud.getHeight(), this.height), Color.RED);
        }

        int iX = 0;
        for (int i = 0; i < size; i++) {
            // draw itemslot
            this.drawSprite(drawX, drawY, ItemContainerHud.SLOT_SIZE, ItemContainerHud.SLOT_SIZE, itemSlotSprite);

            // draw item
            Item item = itemContainer.getItemAt(i);
            if (item.getItemType() != ItemType.NONE) {
                this.drawSprite(drawX + ItemContainerHud.ITEM_PADDING, drawY + ItemContainerHud.ITEM_PADDING,
                        ItemContainerHud.ITEM_SIZE, ItemContainerHud.ITEM_SIZE, item.getSprite());
            }

            iX++;
            drawX += ItemContainerHud.SLOT_SIZE + ItemContainerHud.SLOT_PADDING;
            if (iX >= itemsPerColumn) {
                // next row
                drawY += ItemContainerHud.SLOT_SIZE + ItemContainerHud.SLOT_PADDING;
                iX = 0;
                drawX = drawValFromHudVal(itemContainerHud.getX(), this.width);
            }
        }
    }

    public void drawEquippedInventory(EquippedItemContainerHud equippedItemContainerHud) {
        int drawX = drawValFromHudVal(equippedItemContainerHud.getX(), this.width) +
                equippedItemContainerHud.getEquippedItemContainer().getEquippedIndex() *
                        (ItemContainerHud.SLOT_SIZE + ItemContainerHud.SLOT_PADDING);
        int drawY = drawValFromHudVal(equippedItemContainerHud.getY(), this.height);


        this.drawSprite(drawX, drawY, ItemContainerHud.SLOT_SIZE, ItemContainerHud.SLOT_SIZE,
                equippedItemContainerHud.getEquippedItemSlotSprite());

        this.drawItemContainer(equippedItemContainerHud);
    }

    private int drawValFromHudVal(double hudVal, double rendererVal) {
        return (int) (hudVal / 100 * rendererVal);
    }

}
