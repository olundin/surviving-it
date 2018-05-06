package survivingit.input;

import java.awt.event.*;

/**
 * Mouse class, basically a data structure containing all relevant information
 * about the current state of the mouse
 */
public class Mouse extends MouseAdapter {

    private static final int MOUSE_SIZE = 4;

    private boolean[] button;
    private boolean[] buttonPressed;

    private int x;
    private int y;

    private int scroll;

    /**
     * Creates a new mouse object
     */
    public Mouse() {
        this.button = new boolean[MOUSE_SIZE];
        this.buttonPressed = new boolean[MOUSE_SIZE];

        this.x = 0;
        this.y = 0;

        this.scroll = 0;
    }

    /**
     * Clears pressed and released information.
     * Should be done each game loop
     */
    public void clear() {
        // Reset temporary variable values
        for(int b = 0; b < MOUSE_SIZE; b++) {
            buttonPressed[b] = false;
        }

        this.scroll = 0;
    }

    /**
     * Gets the x position of the mouse, relative to window position.
     * @return x position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the y position of the mouse, relative to window position.
     * @return y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * Returns the scroll of the mouse
     * @return The scroll of the mouse
     */
    public int getScroll() {
        return this.scroll;
    }

    /**
     * Returns true if the mouse button went from up to down this tick.
     * @param b The button to check
     * @return Whether the button was pressed this tick
     */
    public boolean getButtonPressed(Input b) {
        if(b.id < buttonPressed.length) {
            return buttonPressed[b.id];
        } else {
            return false;
        }
    }

    /**
     * Called automatically when the mouse is moved
     * @param e The event
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    /**
     * Called automatically when a mouse button is pressed
     * @param e The event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() < button.length) {
            button[e.getButton()] = true;
            buttonPressed[e.getButton()] = true;
        }
    }

    /**
     * Called automatically when a mouse button is pressed
     * @param e The event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() < button.length) {
            button[e.getButton()] = false;
        }
    }

    /**
     * Called automatically when the mouse wheel changes state.
     * @param e The event
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.scroll = e.getWheelRotation();
    }

}
