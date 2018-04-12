package survivingit.hud;

import survivingit.containers.EquippedInventory;
import survivingit.graphics.HudRenderer;
import survivingit.graphics.Sprite;

public class EquippedInventoryHud extends ItemContainerHud {

    private EquippedInventory equippedInventory;

    private Sprite equippedItemSlotSprite;

    public EquippedInventoryHud(double x, double y, double width, double height, EquippedInventory equippedInventory,
                                int itemsPerRow) {
        super(0, 0, 0, 0, equippedInventory.getContainer(),  itemsPerRow, true);
        this.equippedInventory = equippedInventory;

        this.equippedItemSlotSprite = Sprite.ITEM_SLOT_SELECTED;
    }

    public void render(HudRenderer hudRenderer) {
        hudRenderer.drawEquippedInventory(this);
    }

}
