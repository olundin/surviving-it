package survivingit.hud;

import survivingit.graphics.HudRenderer;
import survivingit.graphics.Sprite;

/**
 * Progress bar hud element.
 * Displays progress from one value to another
 */
public class ProgressBar extends HudElement {

    protected int min;
    protected int max;
    protected int current;

    private Sprite leftEdge;
    private Sprite rightEdge;
    private Sprite empty;
    private Sprite filled;

    /**
     * Create a new progress bar.
     * @param x X position (percentage of screen size)
     * @param y Y position (percentage of screen size)
     * @param width Width (percentage of screen size)
     * @param height Height (percentage of screen size)
     * @param min Min value of bar
     * @param max Max value of bar
     * @param current Current value of bar
     * @param filled Sprite to fill with
     */
    public ProgressBar(double x, double y, double width, double height, int min, int max, int current, Sprite filled) {
        super(x, y, width, height, true);
        this.min = min;
        this.max = max;
        this.current = current;

        this.leftEdge = Sprite.PROGRESS_BAR_EDGE_LEFT;
        this.rightEdge = Sprite.PROGRESS_BAR_EDGE_RIGHT;
        this.empty = Sprite.PROGRESS_BAR_FILL_EMPTY;
        this.filled = filled;
    }

    /**
     * Returns min value of progress bar
     * @return Min value
     */
    public int getMin() {
        return this.min;
    }

    /**
     * Returns max value of progress bar
     * @return Max value
     */
    public int getMax() {
        return this.max;
    }

    /**
     * Returns current value of progress bar
     * @return Current value
     */
    public int getCurrent() {
        return this.current;
    }

    /**
     * Render the progress bar
     * @param renderer The renderer to render with
     */
    public void render(HudRenderer renderer) {
        renderer.drawProgressBar(this);
    }

    /**
     * Returns whether the progress bar is empty
     * @return Whether the progress bar is empty
     */
    public boolean isEmpty() {
        return this.current == this.min;
    }

    /**
     * Returns whether the progress bar is full
     * @return Whether the progress bar is full
     */
    public boolean isFull() {
        return this.current == this.max;
    }

    /**
     * Get left edge sprite of bar
     * @return Left edge sprite
     */
    public Sprite getLeftEdge() {
        return this.leftEdge;
    }

    /**
     * Get right edge sprite of bar
     * @return Right edge sprite
     */
    public Sprite getRightEdge() {
        return this.rightEdge;
    }

    /**
     * Get sprite to fill empty spots with
     * @return empty sprite
     */
    public Sprite getEmpty() {
        return this.empty;
    }

    /**
     * Get sprite to fill full spots with
     * @return full sprite
     */
    public Sprite getFilled() {
        return this.filled;
    }

}
