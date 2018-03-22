package survivingit.input;

import survivingit.gameobjects.Direction;
import survivingit.gameobjects.Player;
import survivingit.gameobjects.Camera;

public class InputHandler {

    private Keyboard keyboard;

    public InputHandler(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void handleInput(double dt, final Player player, final Camera camera) {
        // Zoom in/out with z/x
        if(keyboard.getKey(65)) {
            player.move(dt, Direction.LEFT);
        }
        if(keyboard.getKey(68)) {
            player.move(dt, Direction.RIGHT);
        }
        if(keyboard.getKey(87)) {
            player.move(dt, Direction.UP);
        }
        if(keyboard.getKey(83)) {
            player.move(dt, Direction.DOWN);
        }

        // Zoom in/out with z/x
        if(keyboard.getKey(90)) {
            camera.zoom(1 * dt);
        }
        if(keyboard.getKey(88)) {
            camera.zoom(-1 * dt);
        }
    }
}
