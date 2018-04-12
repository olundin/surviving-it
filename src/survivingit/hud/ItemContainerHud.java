package survivingit.hud;

import survivingit.containers.ItemContainer;
import survivingit.graphics.HudRenderer;
import survivingit.graphics.Sprite;

public class ItemContainerHud extends HudElement {

    public static final double SLOT_SIZE = 4;

    private ItemContainer itemContainer;
    private int itemsPerRow;

    private Sprite itemSlotSprite;

    public ItemContainerHud(double x, double y, double width, double height, ItemContainer itemContainer,
                            int itemsPerRow) {
        super(x, y, width, height);
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
