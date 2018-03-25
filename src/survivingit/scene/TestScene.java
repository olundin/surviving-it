package survivingit.scene;

import survivingit.gameobjects.*;

public class TestScene extends Scene {

    public TestScene(Camera camera) {
        super(camera);
        this.addPlayer(new Player(-1, -1));
        this.add(new Fox(5, 5));
        this.add(new Campfire(-3, -3));
    }

}
