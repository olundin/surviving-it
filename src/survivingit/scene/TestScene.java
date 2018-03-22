package survivingit.scene;

import survivingit.gameobjects.Camera;
import survivingit.gameobjects.Player;
import survivingit.graphics.Sprite;

public class TestScene extends Scene {

    public TestScene(Camera camera) {
        super(camera);
        this.addPlayer(new Player(0, 0, Sprite.MEME_MAN, 0, 5));
        this.camera.setTarget(this.player);
    }

}
