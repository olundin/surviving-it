package survivingit.hud;

import survivingit.graphics.Renderer;
import survivingit.graphics.Sprite;

import java.awt.*;

public class ProgressBar extends HudElement {

    protected int min;
    protected int max;
    protected int current;

    private Sprite leftEdge;
    private Sprite rightEdge;
    private Sprite empty;
    private Sprite filled;

    public ProgressBar(double x, double y, double width, double height, int min, int max, int current, Sprite filled) {
        super(x, y, width, height);
        this.min = min;
        this.max = max;
        this.current = current;

        this.leftEdge = Sprite.PROGRESS_BAR_EDGE_LEFT;
        this.rightEdge = Sprite.PROGRESS_BAR_EDGE_RIGHT;
        this.empty = Sprite.PROGRESS_BAR_FILL_EMPTY;
        this.filled = filled;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    public int getCurrent() {
        return this.current;
    }

    public void render(Renderer renderer) {
        renderer.drawProgressBar(this);
    }

    public boolean isEmpty() {
        return this.current == this.min;
    }

    public boolean isFull() {
        return this.current == this.max;
    }

    public Sprite getLeftEdge() {
        return this.leftEdge;
    }

    public Sprite getRightEdge() {
        return this.rightEdge;
    }

    public Sprite getEmpty() {
        return this.empty;
    }
    public Sprite getFilled() {
        return this.filled;
    }

}
