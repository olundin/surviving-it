package survivingit.hud;

import survivingit.graphics.HudRenderer;

/**
 * An element to be displayed on the hud
 */
public abstract class HudElement {

    private double x; // 0 - 100 (percentage of window width)
    private double y;  // 0 - 100 (percentage of window width)

    private double width;
    private double height;

    private boolean visible;

    /**
     * Creates a new hud element
     * @param x x position of element, percentage of screen width
     * @param y y position of element, percentage of screen height
     * @param width width of element, percentage of screen width
     * @param height height of element, percentage of screen height
     * @param visible is the element visible
     */
    protected HudElement(final double x, final double y, final double width, final double height, final boolean visible) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = visible;
    }

    /**
     * Returns the x position of the element
     * @return x position of the element
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y position of the element
     * @return y position of the element
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the width of the element
     * @return width of the element
     */
    public double getWidth() {
        return width;
    }
    /**
     * Returns the height of the element
     * @return height of the element
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns whether the element is visible
     * @return Whether the element is visible
     */
    public boolean isVisible() {
        return this.visible;
    }

    /**
     * Toggles element visibility
     */
    public void toggleVisible() {
        this.visible = !this.visible;
    }

    /**
     * Renders element
     * @param renderer Tenderer to use
     */
    public abstract void render(HudRenderer renderer);

}
