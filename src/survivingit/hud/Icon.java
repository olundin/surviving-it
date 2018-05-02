package survivingit.hud;

import survivingit.graphics.HudRenderer;
import survivingit.graphics.Sprite;

/**
 * Hud element for an image
 */
public class Icon extends HudElement {

    private Sprite sprite;

    /**
     * Create a new Icon hud element
     * @param x x position, percentage of screen
     * @param y y position, percentage of screen
     * @param width width, percentage of screen
     * @param height height, percentage of screen
     * @param sprite icon sprite
     */
    public Icon(final double x, final double y, final double width, final double height, Sprite sprite) {
        super(x, y, width, height, true);
        this.sprite = sprite;
    }

    /**
     * Render icon element
     * @param renderer Renderer to use
     */
    public void render(HudRenderer renderer) {
        renderer.drawIcon(this);
    }

    /**
     * Returns sprite of icon
     * @return Icon's sprite
     */
    public Sprite getSprite() {
        return this.sprite;
    }

}
