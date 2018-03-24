package survivingit.scene;

import survivingit.gameobjects.Camera;
import survivingit.gameobjects.Creature;
import survivingit.gameobjects.Fox;
import survivingit.gameobjects.Player;
import survivingit.graphics.Sprite;

public class TestScene extends Scene {

    public TestScene(Camera camera) {
        super(camera);
        this.addPlayer(new Player(-1, -1));
        this.add(new Fox(5, 5));
    }

}
