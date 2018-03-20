package survivingit.scene;

import survivingit.gameobjects.Player;
import survivingit.graphics.Sprite;

public class TestScene extends Scene {

    public TestScene() {
        super();

        this.add(new Player(0, 0, Sprite.MEME_MAN, 0, 0));
    }

}
