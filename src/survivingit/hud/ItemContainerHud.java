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
     * @param x
     * @param y
     * @param width
     * @param height
     * @param itemContainer
     * @param itemsPerRow
     * @param visible
     */
    public ItemContainerHud(double x, double y, double width, double height, ItemContainer itemContainer,
                            int itemsPerRow, boolean visible) {
        super(x, y, width, height, visible);
        this.itemContainer = itemContainer;
        this.itemsPerRow = itemsPerRow;

        this.itemSlotSprite = Sprite.ITEM_SLOT;
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        hudRenderer.drawItemContainer(this);
    }

    public ItemContainer getItemContainer() {
        return itemContainer;
    }

    public int getItemsPerRow() {
        return itemsPerRow;
    }

    public Sprite getItemSlotSprite() {
        return this.itemSlotSprite;
    }
}
