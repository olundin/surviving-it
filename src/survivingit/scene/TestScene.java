package survivingit.scene;

import survivingit.gameobjects.Camera;
import survivingit.gameobjects.Creature;
import survivingit.gameobjects.Fox;
import survivingit.gameobjects.Player;
import survivingit.graphics.Sprite;

public class TestScene extends Scene {

    public TestScene(Camera camera) {
        super(camera);
        this.addPlayer(new Player(0, 0, Sprite.MEME_MAN, 0, 2.5));
        this.add(new Fox(5, 5, Sprite.FOX, 0, 10));
    }

}
