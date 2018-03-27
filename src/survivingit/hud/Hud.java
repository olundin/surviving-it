package survivingit.hud;

import survivingit.gameobjects.Player;
import survivingit.graphics.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Hud {

    private List<HudElement> elements;

    public Hud(int width, int height, Player player) {
        this.elements = new ArrayList<>();

        this.add(new HealthBar(50, 50, 5, 1, player));
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
