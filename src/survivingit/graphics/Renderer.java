package survivingit.graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas {

    private int width;
    private int height;

    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    public Renderer(int width, int height) {
        super();

        this.width = width;
        this.height = height;

        this.setSize(width, height);
        this.setVisible(true);
        this.setFocusable(false);

    }

    public void prepare() {
	bufferStrategy = this.getBufferStrategy();
	graphics = bufferStrategy.getDrawGraphics();
    }

    public void clear() {
	graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
    }

    public void display() {
        graphics.dispose(); // Release system resources
	bufferStrategy.show();
    }
}
