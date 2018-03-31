package survivingit.hud;

import survivingit.gameobjects.Player;
import survivingit.graphics.HudRenderer;
import survivingit.graphics.Renderer;
import survivingit.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Hud {

    private List<HudElement> elements;

    public Hud(Player player) {
        this.elements = new ArrayList<>();
        this.add(new HealthBar(5, 90, 15, 5, player));
        this.add(new Icon(21, 90, 2.5, 5, Sprite.ICON_HEART));
    }

    private void add(HudElement element) {
        this.elements.add(element);
    }

    public void render(HudRenderer renderer) {
        for (HudElement element : elements) {
            element.render(renderer);
        }
    }
}
