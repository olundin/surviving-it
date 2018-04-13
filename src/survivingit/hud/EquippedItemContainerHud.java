package survivingit.hud;

import survivingit.containers.EquippedItemContainer;
import survivingit.graphics.HudRenderer;
import survivingit.graphics.Sprite;

public class EquippedItemContainerHud extends ItemContainerHud {

    private EquippedItemContainer equippedItemContainer;

    private Sprite equippedItemSlotSprite;

    public EquippedItemContainerHud(double x, double y, double width, double height, EquippedItemContainer equippedItemContainer,
                                    int itemsPerRow) {
        super(x, y, width, height, equippedItemContainer, itemsPerRow, true);
        this.equippedItemContainer = equippedItemContainer;

        this.equippedItemSlotSprite = Sprite.ITEM_SLOT_SELECTED;
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        hudRenderer.drawEquippedInventory(this);
    }

    public EquippedItemContainer getEquippedItemContainer() {
        return this.equippedItemContainer;
    }

    public Sprite getEquippedItemSlotSprite() {
        return this.equippedItemSlotSprite;
    }

}
