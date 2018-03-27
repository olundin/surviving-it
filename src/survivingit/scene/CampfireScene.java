package survivingit.scene;

import survivingit.gameobjects.Camera;
import survivingit.gameobjects.Campfire;
import survivingit.gameobjects.Player;

public class CampfireScene extends Scene {

    public CampfireScene(Camera camera) {
        super(32, 32, camera);
        this.addPlayer(new Player(-1, -1));
        for(double x = 0.0; x < 50; x += 0.25) {
            for(double y = 0.0; y < 50; y += 0.25) {
                this.add(new Campfire(x, y));
            }
        }
    }

}
