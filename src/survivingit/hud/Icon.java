package survivingit.hud;

import survivingit.graphics.HudRenderer;
import survivingit.graphics.Sprite;

public class Icon extends HudElement {

    private Sprite sprite;

    public Icon(final double x, final double y, final double width, final double height, Sprite sprite) {
        super(x, y, width, height);
        this.sprite = sprite;
    }

    public void render(HudRenderer renderer) {
        renderer.drawIcon(this);
    }

    public Sprite getSprite() {
        return this.sprite;
    }

}
