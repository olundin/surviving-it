package survivingit.scene;

import survivingit.gameobjects.Campfire;
import survivingit.gameobjects.Player;

/**
 * Creates a scene filled with campfires.
 *
 * @see Scene
 */
public class CampfireScene extends Scene {

    /**
     * Width of the scene.
     */
    public static final int WIDTH = 32;
    /**
     * Width of the scene.
     */
    public static final int HEIGHT = 32;

    /**
     * Creates a new campfire scene.
     * @param camera The camera of the scene.
     */
    public CampfireScene(Camera camera) {
        super(camera, WIDTH, HEIGHT);
        this.setPlayer(new Player(-1, -1, this));
        for(double x = 0.0; x < WIDTH; ++x) {
            for(double y = 0.0; y < HEIGHT; ++y) {
                Campfire campfire = new Campfire(x, y, this);
                this.add(campfire);
            }
        }
    }
}
