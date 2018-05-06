package survivingit.input;

import survivingit.gameobjects.Direction;
import survivingit.gameobjects.Player;
import survivingit.scene.Camera;

import survivingit.hud.Hud;
import survivingit.util.Maths;
import survivingit.util.Point;

/**
 * Handles input. Does the things that should be done when
 * certain input events happen.
 */
public class InputHandler {

    private Keyboard keyboard;
    private Mouse mouse;

    private static final double ZOOM_SPEED = 20.0;
    private static final double CAMERA_LERP = 0.1;


    public InputHandler(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    /**
     * Handle input.
     * @param dt Time since last game tick
     * @param player Player
     * @param camera Camera
     * @param hud Hud
     */
    public void handleInput(double dt, final Player player, final Camera camera, final Hud hud) {

        handlePlayerMovement(player);
        handlePlayerItems(player, hud);
        handleCameraMovement(dt, player, camera);

    }

    private void handlePlayerMovement(Player player) {
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
    }

    private void handlePlayerItems(Player player, Hud hud) {

        // Player performAttack with left mouse button
        if (mouse.getButtonPressed(Input.BUTTON_LEFT)) {
            player.useEquippedItem();
        } else if (mouse.getButtonPressed(Input.BUTTON_RIGHT)) {
            player.playerAttack();
        }

        // Switch equipped item with scroll wheel
        if (mouse.getScroll() != 0) {
            player.changeEquippedItem(mouse.getScroll());
        }

        // Switch equipped item with left/right arrow keys
        if (keyboard.getKey(Input.KEY_LEFT)) {
            player.changeEquippedItem(-1);
        } else if (keyboard.getKey(Input.KEY_RIGHT)) {
            player.changeEquippedItem(1);
        }

        // Toggle HUD elements
        if (keyboard.getKey(Input.KEY_I) && keyboard.getKeyPressed(Input.KEY_I)) {
            hud.toggleInvetory();
        }
    }

    private void handleCameraMovement(double dt, Player player, Camera camera) {
        // Zoom in/out with up/down key press
        if (keyboard.getKey(Input.KEY_UP)) {
            camera.zoom(ZOOM_SPEED * dt);
        } else if (keyboard.getKey(Input.KEY_DOWN)) {
            camera.zoom(-ZOOM_SPEED * dt);
        }

        // Set camera position to be between player and mouse
        camera.setTarget(new Point(
                Maths.lerp(player.getX(), camera.screenToWorldX(mouse.getX()), CAMERA_LERP),
                Maths.lerp(player.getY(), camera.screenToWorldY(mouse.getY()), CAMERA_LERP)));
    }

}
