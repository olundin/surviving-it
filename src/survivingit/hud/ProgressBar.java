package survivingit.hud;

import survivingit.graphics.Renderer;

import java.awt.*;

public class ProgressBar extends HudElement {

    protected int max;
    protected int min;
    protected int current;

    private Color color;

    public ProgressBar(final double x, final double y, final double width, final double height, final Color color) {
        super(x, y, width, height);
        this.color = color;
    }


    public int getMax() {
        return this.max;
    }

    public int getMin() {
        return this.min;
    }

    public int getCurrent() {
        return this.current;
    }

    public Color getColor() {
        return this.color;
    }

    public void render(Renderer renderer) {
        renderer.drawProgressBar(this);
    }

}
