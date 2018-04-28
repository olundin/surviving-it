package survivingit.input;

import survivingit.gameobjects.Direction;
import survivingit.gameobjects.Player;
import survivingit.scene.Camera;

import survivingit.hud.Hud;
import survivingit.util.Maths;
import survivingit.util.Point;

public class InputHandler {

    private Keyboard keyboard;
    private Mouse mouse;

    public InputHandler(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public void handleInput(double dt, final Player player, final Camera camera, final Hud hud) {
        // Move with WASD. Allow diagonal movement
        if (keyboard.getKey(Input.KEY_A) && keyboard.getKey(Input.KEY_W)) {
            player.setDirection(Direction.UP_LEFT);
        } else if (keyboard.getKey(Input.KEY_W) && keyboard.getKey(Input.KEY_D)) {
            player.setDirection(Direction.UP_RIGHT);
        } else if (keyboard.getKey(Input.KEY_S) && keyboard.getKey(Input.KEY_A)) {
            player.setDirection(Direction.DOWN_LEFT);
        } else if (keyboard.getKey(Input.KEY_S) && keyboard.getKey(Input.KEY_D)) {
            player.setDirection(Direction.DOWN_RIGHT);
        } else if (keyboard.getKey(Input.KEY_A)) {
            player.setDirection(Direction.LEFT);
        } else if (keyboard.getKey(Input.KEY_W)) {
            player.setDirection(Direction.UP);
        } else if (keyboard.getKey(Input.KEY_D)) {
            player.setDirection(Direction.RIGHT);
        } else if (keyboard.getKey(Input.KEY_S)) {
            player.setDirection(Direction.DOWN);
        } else {
            player.setDirection(Direction.NONE);
        }

        // Player attack with left mouse button
        if (mouse.getButtonPressed(Input.BUTTON_LEFT)) {
            player.useEquippedItem();
        } else if (mouse.getButtonPressed(Input.BUTTON_RIGHT)) {
            player.attack();
        }

        // Switch equipped item with scroll wheel
        if (mouse.getScroll() != 0) {
            player.changeEquippedItem(mouse.getScroll());
        }

        // Toggle HUD elements
        if (keyboard.getKey(Input.KEY_I) && keyboard.getKeyPressed(Input.KEY_I)) {
            hud.toggleInvetory();
        }

        // Zoom in/out with up/down key press
        if (keyboard.getKey(Input.KEY_UP)) {
            camera.zoom(20.0 * dt);
        } else if (keyboard.getKey(Input.KEY_DOWN)) {
            camera.zoom(-20.0 * dt);
        }

        // Set camera position to be between player and mouse
        camera.setTarget(new Point(
                Maths.lerp(player.getX(), camera.screenToWorldX(mouse.getX()), 0.1),
                Maths.lerp(player.getY(), camera.screenToWorldY(mouse.getY()), 0.1)));
    }

}
