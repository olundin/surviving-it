package survivingit.hud;

import survivingit.gameobjects.Player;
import survivingit.graphics.HudRenderer;
import survivingit.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Hud is a container for hud elements.
 * It creates the elements with correct data and positions.
 */
public class Hud {

    private static final double HEALTHBAR_X = 5;
    private static final double HEALTHBAR_Y = 90;
    private static final double HEALTHBAR_WIDTH = 15;
    private static final double HEALTHBAR_HEIGHT = 5;

    private static final double HEART_ICON_X = 21;
    private static final double HEART_ICON_Y = 90;
    private static final double HEART_ICON_WIDTH = 2.5;
    private static final double HEART_ICON_HEIGHT = 5;

    private static final double EQUIPPED_INVENTORY_X = 80;
    private static final double EQUIPPED_INVENTORY_Y = 90;
    private static final double EQUIPPED_INVENTORY_WIDTH = 15;
    private static final double EQUIPPED_INVENTORY_HEIGHT = 5;

    private static final double INVENTORY_X = 80;
    private static final double INVENTORY_Y = 70;
    private static final double INVENTORY_WIDTH = 15;
    private static final double INVENTORY_HEIGHT = 20;


    private List<HudElement> elements;
    private ItemContainerHud inventoryHud;

    /**
     * Creates a new hud
     * @param player player to set data from
     */
    public Hud(Player player) {
        this.elements = new ArrayList<>();
        this.add(new HealthBar(HEALTHBAR_X, HEALTHBAR_Y, HEALTHBAR_WIDTH, HEALTHBAR_HEIGHT, player));
        this.add(new Icon(HEART_ICON_X, HEART_ICON_Y, HEART_ICON_WIDTH, HEART_ICON_HEIGHT, Sprite.ICON_HEART));
        this.add(new EquippedItemContainerHud(EQUIPPED_INVENTORY_X, EQUIPPED_INVENTORY_Y, EQUIPPED_INVENTORY_WIDTH, EQUIPPED_INVENTORY_HEIGHT,
                player.getPlayerInventory().getEquippedItemContainer(), 5));
        this.inventoryHud = new ItemContainerHud(INVENTORY_X, INVENTORY_Y, INVENTORY_WIDTH, INVENTORY_HEIGHT,
                player.getPlayerInventory().getPassiveStorage(), 5, true);
    }

    private void add(HudElement element) {
        this.elements.add(element);
    }

    /**
     * Renders all hud elements
     * @param renderer Renderer to use
     */
    public void render(HudRenderer renderer) {
        for (HudElement element : elements) {
            if (element.isVisible()) {
                element.render(renderer);
            }
        }
        if (inventoryHud.isVisible()) {
            inventoryHud.render(renderer);
        }
    }

    /**
     * Toggle inventory visibility
     */
    public void toggleInvetory() {
        this.inventoryHud.toggleVisible();
    }
}
