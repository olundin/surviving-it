package survivingit.input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keyboard implements KeyListener {

    private static final int KEYBOARD_SIZE = 524;

    private boolean[] keyDown;
    private boolean[] keyPressed;
    private boolean[] keyReleased;

    public Keyboard() {
        this.keyDown = new boolean[KEYBOARD_SIZE];
        this.keyPressed = new boolean[KEYBOARD_SIZE];
        this.keyReleased = new boolean[KEYBOARD_SIZE];
    }

    public void keyPressed(KeyEvent e) {
        keyDown[e.getKeyCode()] = true;
        System.out.println(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        keyDown[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {}

    public boolean getKey(int keyCode) {
        return keyDown[keyCode];
    }

}
