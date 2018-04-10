package survivingit.hud;

import survivingit.containers.ItemContainer;
import survivingit.graphics.HudRenderer;

public class ItemContainerHud extends HudElement {

    private ItemContainer itemContainer;
    private int itemsPerColumn;

    public ItemContainerHud(double x, double y, double width, double height, ItemContainer itemContainer,
                            int itemsPerColumn) {
        super(x, y, width, height);
        this.itemContainer = itemContainer;
        this.itemsPerColumn = itemsPerColumn;
    }

    public void render(HudRenderer hudRenderer) {
        // TODO:
    }



}
