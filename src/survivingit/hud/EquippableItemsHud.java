package survivingit.hud;

import survivingit.containers.EquippableInventory;
import survivingit.graphics.HudRenderer;

public class EquippableItemsHud extends ItemContainerHud {

    private int itemsPerRow;
    private EquippableInventory equippableInventory;

    public EquippableItemsHud(double x, double y, double width, double height, EquippableInventory equippableInventory,
                              int itemsPerRow) {
        super(0, 0, 0, 0, equippableInventory.getContainer(),  itemsPerRow, true);
    }

    public void render(HudRenderer hudRenderer) {

    }

}
