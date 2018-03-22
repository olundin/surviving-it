package survivingit.input;

import survivingit.gameobjects.Direction;
import survivingit.gameobjects.Player;
import survivingit.gameobjects.Camera;

public class InputHandler {

    private Keyboard keyboard;

    public InputHandler(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void handleInput(final Player player, final Camera camera) {
        // Zoom in/out with z/x
        if(keyboard.getKey(65)) {
            player.move(Direction.LEFT);
        }
        if(keyboard.getKey(68)) {
            player.move(Direction.RIGHT);
        }
        if(keyboard.getKey(87)) {
            player.move(Direction.UP);
        }
        if(keyboard.getKey(83)) {
            player.move(Direction.DOWN);
        }

        // Zoom in/out with z/x
        if(keyboard.getKey(90)) {
            camera.zoom(0.1);
        }
        if(keyboard.getKey(88)) {
            camera.zoom(-0.1);
        }
    }
}
