package survivingit.graphics;

import survivingit.hud.EquippedItemContainerHud;
import survivingit.hud.Icon;
import survivingit.hud.ItemContainerHud;
import survivingit.hud.ProgressBar;

public interface HudRenderer {

    public void drawProgressBar(ProgressBar progressBar);

    public void drawIcon(Icon icon);

    public void drawItemContainer(ItemContainerHud itemContainerHud);

    public void drawEquippedInventory(EquippedItemContainerHud equippedItemContainerHud);

}
