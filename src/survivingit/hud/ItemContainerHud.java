package survivingit.hud;

import survivingit.containers.ItemContainer;
import survivingit.graphics.HudRenderer;
import survivingit.graphics.Sprite;

/**
 * Item container hud element.
 */
public class ItemContainerHud extends HudElement {

    /**
     * Padding between slots
     */
    public static final int SLOT_PADDING = 8;
    /**
     * Size of a slot
     */
    public static final int SLOT_SIZE = 56;
    /**
     * Padding between slot and item
     */
    public static final int ITEM_PADDING = 4;
    /**
     * Size of an item
     */
    public static final int ITEM_SIZE = 48;

    private ItemContainer itemContainer;
    private int itemsPerRow;

    private Sprite itemSlotSprite;

    /**
     * Creates a new Item container hud element
     * @param x X position of element, percentage of screen size
     * @param y Y position of element, percentage of screen size
     * @param width Width of element, percentage of screen size
     * @param height Height of element, percentage of screen size
     * @param itemContainer Container
     * @param itemsPerRow Items per row
     * @param visible Visible
     */
    public ItemContainerHud(double x, double y, double width, double height, ItemContainer itemContainer,
                            int itemsPerRow, boolean visible) {
        super(x, y, width, height, visible);
        this.itemContainer = itemContainer;
        this.itemsPerRow = itemsPerRow;

        this.itemSlotSprite = Sprite.ITEM_SLOT;
    }

    /**
     * Render item container
     * @param renderer render to use
     */
    @Override
    public void render(HudRenderer renderer) {
        renderer.drawItemContainer(this);
    }

    /**
     * Returns the item container of the hud element
     * @return the item container
     */
    public ItemContainer getItemContainer() {
        return itemContainer;
    }

    /**
     * Get number of items per row
     * @return number of items per row
     */
    public int getItemsPerRow() {
        return itemsPerRow;
    }

    /**
     * Returns sprite used for item slots
     * @return Sprite of an item slots
     */
    public Sprite getItemSlotSprite() {
        return this.itemSlotSprite;
    }
}
