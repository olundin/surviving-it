package survivingit.hud;

import survivingit.containers.ItemContainer;
import survivingit.graphics.HudRenderer;

public class ItemContainerHud extends HudElement {

    public static final double SLOT_SIZE = 4;

    private ItemContainer itemContainer;
    private int itemsPerRow;

    public ItemContainerHud(double x, double y, double width, double height, ItemContainer itemContainer,
                            int itemsPerRow) {
        super(x, y, width, height);
        this.itemContainer = itemContainer;
        this.itemsPerRow = itemsPerRow;
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
}
