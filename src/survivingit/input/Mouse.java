package survivingit.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseWheelListener, MouseMotionListener {

    private static final int MOUSE_SIZE = 4;

    private boolean[] button;
    private boolean[] buttonPressed;
    private boolean[] buttonReleased;

    private int x;
    private int y;

    private int scroll;

    public Mouse() {
        this.button = new boolean[MOUSE_SIZE];
        this.buttonPressed = new boolean[MOUSE_SIZE];
        this.buttonReleased = new boolean[MOUSE_SIZE];

        this.x = 0;
        this.y = 0;

        this.scroll = 0;
    }

    public void clear() {
        // Reset temporary variable values
        for(int b = 0; b < MOUSE_SIZE; b++) {
            buttonPressed[b] = false;
            buttonReleased[b] = false;
        }

        this.scroll = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getScroll() {
        return this.scroll;
    }

    public boolean getButton(Input b) {
        if(b.id < button.length) {
            return button[b.id];
        } else {
            return false;
        }
    }

    public boolean getButtonPressed(Input b) {
        if(b.id < buttonPressed.length) {
            return buttonPressed[b.id];
        } else {
            return false;
        }
    }

    public boolean getButtonReleased(Input b) {
        if(b.id < buttonReleased.length) {
            return buttonReleased[b.id];
        } else {
            return false;
        }
    }

    // Mouse motion events
    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) { }

    // Mouse events

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() < button.length) {
            button[e.getButton()] = true;
            buttonPressed[e.getButton()] = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() < button.length) {
            button[e.getButton()] = false;
            buttonReleased[e.getButton()] = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }

    // Mouse wheel events

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.scroll = e.getWheelRotation();
    }

}
