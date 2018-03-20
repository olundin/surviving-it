package survivingit.input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyBoard implements KeyListener {

    private static final int KEYBOARD_SIZE = 100;

    private static boolean[] keyDown = new boolean[KEYBOARD_SIZE];



    private HashMap<Integer, Boolean>[] keys; // KeyCode -> ArrayIndex

    public KeyBoard() {
        this.keys = new HashMap<>();
    }

    public void keyPressed(KeyEvent e) {
        keyDown[e.getKeyCode()] = true;
        //keys.put(new Integer(e.getKeyCode()), new Boolean(true));
    }

    public void keyReleased(KeyEvent e) {
	keyDown[e.getKeyCode()] = false;
    }

}
