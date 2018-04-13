package survivingit.hud;

import survivingit.graphics.HudRenderer;

public abstract class HudElement {

    private double x; // 0 - 100 (percentage of window width)
    private double y;  // 0 - 100 (percentage of window width)

    private double width;
    private double height;

    private boolean visible;

    public HudElement(final double x, final double y, final double width, final double height, final boolean visible) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = visible;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public abstract void render(HudRenderer renderer);

}
