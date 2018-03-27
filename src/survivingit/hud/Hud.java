package survivingit.hud;

import survivingit.gameobjects.Player;
import survivingit.graphics.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Hud {

    private List<HudElement> elements;

    public Hud(Player player) {
        this.elements = new ArrayList<>();


        this.add(new HealthBar(2.0, 90.0, 10, 3, player));
    }

    private void add(HudElement element) {
        this.elements.add(element);
    }

    public void render(Renderer renderer) {
        for (HudElement element : elements) {
            element.render(renderer);
        }
    }
}
