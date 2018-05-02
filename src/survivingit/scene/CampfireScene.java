package survivingit.scene;

import survivingit.gameobjects.Campfire;
import survivingit.gameobjects.Player;

/**
 * Creates a scene filled with campfires.
 */
public class CampfireScene extends Scene {

    /**
     * Creates a new campfire scene.
     * @param camera The camera of the scene.
     */
    public CampfireScene(Camera camera) {
        super(camera, 32, 32);
        this.setPlayer(new Player(-1, -1));
        for(double x = 0.0; x < 50; x += 0.25) {
            for(double y = 0.0; y < 50; y += 0.25) {
                Campfire campfire = new Campfire(x, y);
                campfire.setLit(true);
                this.add(campfire);

            }
        }
    }

}
