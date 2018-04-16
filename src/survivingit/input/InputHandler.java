package survivingit.input;

import survivingit.gameobjects.Direction;
import survivingit.gameobjects.Player;
import survivingit.gameobjects.Camera;

import survivingit.hud.Hud;
import survivingit.util.Maths;

public class InputHandler {

    private KeyboardListener keyboardListener;
    private Mouse mouse;

    public InputHandler(KeyboardListener keyboardListener, Mouse mouse) {
        this.keyboardListener = keyboardListener;
        this.mouse = mouse;
    }

    public void handleInput(final Player player, final Camera camera, final Hud hud) {
        // Move with WASD. Allow diagonal movement
        if (keyboardListener.getKey(Input.KEY_A) && keyboardListener.getKey(Input.KEY_W)) {
            player.setDirection(Direction.UP_LEFT);
        } else if (keyboardListener.getKey(Input.KEY_W) && keyboardListener.getKey(Input.KEY_D)) {
            player.setDirection(Direction.UP_RIGHT);
        } else if (keyboardListener.getKey(Input.KEY_S) && keyboardListener.getKey(Input.KEY_A)) {
            player.setDirection(Direction.DOWN_LEFT);
        } else if (keyboardListener.getKey(Input.KEY_S) && keyboardListener.getKey(Input.KEY_D)) {
            player.setDirection(Direction.DOWN_RIGHT);
        } else if (keyboardListener.getKey(Input.KEY_A)) {
            player.setDirection(Direction.LEFT);
        } else if (keyboardListener.getKey(Input.KEY_W)) {
            player.setDirection(Direction.UP);
        } else if (keyboardListener.getKey(Input.KEY_D)) {
            player.setDirection(Direction.RIGHT);
        } else if (keyboardListener.getKey(Input.KEY_S)) {
            player.setDirection(Direction.DOWN);
        } else {
            player.setDirection(Direction.NONE);
        }

        // Switch equipped item with scroll wheel
        if (mouse.getScroll() != 0) {
            player.changeEquippedItem(mouse.getScroll());
        }

        // Toggle HUD elements
        if (keyboardListener.getKey(Input.KEY_I)) {
            hud.toggleInvetory();
            System.out.println("toggle");
        }

        // Zoom in/out with up/down key press
        if (keyboardListener.getKey(Input.KEY_UP)) {
            camera.zoom(0.5);
        } else if (keyboardListener.getKey(Input.KEY_DOWN)) {
            camera.zoom(-0.5);
        }

        // Set camera position to be between player and mouse
        camera.setCenterPos(
                Maths.lerp(player.getX(), camera.screenToWorldX(mouse.getX()), 0.1),
                Maths.lerp(player.getY(), camera.screenToWorldY(mouse.getY()), 0.1)
        );
    }
}