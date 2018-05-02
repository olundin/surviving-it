package survivingit.hud;

import survivingit.containers.EquippedItemContainer;
import survivingit.graphics.HudRenderer;
import survivingit.graphics.Sprite;

/**
 * Container hud for an equippable item.
 */
public class EquippedItemContainerHud extends ItemContainerHud {

    private EquippedItemContainer equippedItemContainer;

    private Sprite equippedItemSlotSprite;

    /**
     * Creates a new equipped item container hud
     * @param x X position, percentage of the screen width
     * @param y Y position, percentage of the screen height
     * @param width Width, percentage of screen width
     * @param height Height, percentage of screen height
     * @param equippedItemContainer Item container
     * @param itemsPerRow Items per row
     */
    public EquippedItemContainerHud(double x, double y, double width, double height, EquippedItemContainer equippedItemContainer,
                                    int itemsPerRow) {
        super(x, y, width, height, equippedItemContainer, itemsPerRow, true);
        this.equippedItemContainer = equippedItemContainer;

        this.equippedItemSlotSprite = Sprite.ITEM_SLOT_SELECTED;
    }

    /**
     * Renders the item container
     * @param renderer render to use
     */
    @Override
    public void render(HudRenderer renderer) {
        renderer.drawEquippedInventory(this);
    }

    /**
     * Returns the equipped item container
     * @return The equipped item container
     */
    public EquippedItemContainer getEquippedItemContainer() {
        return this.equippedItemContainer;
    }

    /**
     * Returns the sprite to use for equipped item containers
     * @return The sprite to use for equipped item containers
     */
    public Sprite getEquippedItemSlotSprite() {
        return this.equippedItemSlotSprite;
    }

}
