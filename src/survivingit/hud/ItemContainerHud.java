package survivingit.hud;

import survivingit.containers.ItemContainer;
import survivingit.graphics.HudRenderer;
import survivingit.graphics.Sprite;

public class ItemContainerHud extends HudElement {

    public static final int SLOT_PADDING = 8;
    public static final int SLOT_SIZE = 56;

    public static final int ITEM_PADDING = 4;
    public static final int ITEM_SIZE = 48;

    private ItemContainer itemContainer;
    private int itemsPerRow;

    private Sprite itemSlotSprite;

    public ItemContainerHud(double x, double y, double width, double height, ItemContainer itemContainer,
                            int itemsPerRow, boolean visible) {
        super(x, y, width, height, visible);
        this.itemContainer = itemContainer;
        this.itemsPerRow = itemsPerRow;

        this.itemSlotSprite = Sprite.ITEM_SLOT;
    }

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
