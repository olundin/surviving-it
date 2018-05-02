package survivingit.input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Data structure for all relevant keyboard data.
 */
public class Keyboard implements KeyListener {

    private static final int KEYBOARD_SIZE = 512;

    private boolean[] key;
    private boolean[] keyPressed;
    private boolean[] keyReleased;

    /**
     * Creates a new keyboard.
     */
    public Keyboard() {
        key = new boolean[KEYBOARD_SIZE];
        keyPressed = new boolean[KEYBOARD_SIZE];
        keyReleased = new boolean[KEYBOARD_SIZE];
    }

    /**
     * Clears pressed and released information.
     * Should be done each game loop
     */
    public void clear() {
        // Reset temporary variable values
        for(int k = 0; k < KEYBOARD_SIZE; k++) {
            keyPressed[k] = false;
            keyReleased[k] = false;
        }
    }

    /**
     * Gets the state of a key.
     * @param k The key which state to check
     * @return True if the key is down, else false.
     */
    public boolean getKey(Input k) {
        return key[k.id];
    }

    /**
     * Returns true if the given key was pressed this tick
     * @param k The key to check
     * @return True if the key was pressed this tick
     */
    public boolean getKeyPressed(Input k) {
        return keyPressed[k.id];
    }

    /**
     * Returns true if the given key was released this tick
     * @param k The key to check
     * @return True if the key was released this tick
     */
    public boolean getKeyReleased(Input k) {
        return keyReleased[k.id];
    }

    /**
     * Automatically called when a key is pressed
     * @param e The event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        key[e.getKeyCode()] = true;
        keyPressed[e.getKeyCode()] = true;
    }

    /**
     * Automatically called when a key is released
     * @param e The event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        key[e.getKeyCode()] = false;
        keyReleased[e.getKeyCode()] = true;
    }

    /**
     * Automatically called when a key has been held down for a while
     * @param e The event
     */
    @Override
    public void keyTyped(KeyEvent e) {}

}
