package survivingit.graphics;

import survivingit.hud.EquippedItemContainerHud;
import survivingit.hud.Icon;
import survivingit.hud.ItemContainerHud;
import survivingit.hud.ProgressBar;

/**
 * Interface for a HudRenderer that includes methods that are necessary for a Renderer to be a HudRenderer.
 */
public interface HudRenderer {

    /**
     * Draws the entered ProgressBar.
     * @param progressBar ProgressBar to be drawn.
     */
    public void drawProgressBar(ProgressBar progressBar);

    /**
     * Draws the entered Icon.
     * @param icon Icon to be drawn.
     */
    public void drawIcon(Icon icon);

    /**
     * Draws the entered ItemContainerHud.
     * @param itemContainerHud ItemContainerHud to be drawn.
     */
    public void drawItemContainer(ItemContainerHud itemContainerHud);

    /**
     * Draws the enterd EquippedItemContainerHud.
     * @param equippedItemContainerHud EquippedItemContainerHud to be drawn.
     */
    public void drawEquippedInventory(EquippedItemContainerHud equippedItemContainerHud);

}
