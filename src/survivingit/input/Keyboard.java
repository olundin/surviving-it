package survivingit.input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keyboard implements KeyListener {

    private static final int KEYBOARD_SIZE = 512;

    private boolean[] key;
    private boolean[] keyPressed;
    private boolean[] keyReleased;

    public Keyboard() {
        key = new boolean[KEYBOARD_SIZE];
        keyPressed = new boolean[KEYBOARD_SIZE];
        keyReleased = new boolean[KEYBOARD_SIZE];
    }

    public void clear() {
        // Reset temporary variable values
        for(int k = 0; k < KEYBOARD_SIZE; k++) {
            keyPressed[k] = false;
            keyReleased[k] = false;
        }
    }

    public boolean getKey(Input k) {
        return key[k.id];
    }

    public boolean getKeyPressed(Input k) {
        return keyPressed[k.id];
    }

    public boolean getKeyReleased(Input k) {
        return keyReleased[k.id];
    }

    public void keyPressed(KeyEvent e) {
        key[e.getKeyCode()] = true;
        keyPressed[e.getKeyCode()] = true;
        System.out.println(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        key[e.getKeyCode()] = false;
        keyReleased[e.getKeyCode()] = true;
    }

    public void keyTyped(KeyEvent e) {}

}
