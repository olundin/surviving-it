package survivingit.input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class Keyboard implements KeyListener {


    private PlayerController playerController;
    private HudController hudController;
    private CameraController cameraController;


    private Set<Integer> keysDown;

    public Keyboard(PlayerController playerController, HudController hudController, CameraController cameraController) {
        this.playerController = playerController;
        this.hudController = hudController;
        this.cameraController = cameraController;

        keysDown = new HashSet<>();
    }

    public void clear() {
        keysDown.clear();
    }

    public boolean isKeyDown(Input k) {
        return keysDown.contains(k.id);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysDown.add(e.getKeyCode());

        if (e.getKeyCode() == Input.KEY_I.id) {
            hudController.handle(HudAction.TOGGLE_INVENTORY);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!keysDown.contains(e.getKeyCode())) {
            throw new IllegalStateException("Key released that has not been pressed");
        }
        keysDown.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
