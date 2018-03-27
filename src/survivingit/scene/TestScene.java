package survivingit.scene;

import survivingit.gameobjects.*;

import java.util.Random;

public class TestScene extends Scene {

    public TestScene(Camera camera) {
        super(32, 32, camera);
        this.addPlayer(new Player(-1, -1));
        this.add(new Fox(5, 5));
        this.add(new Campfire(-3, -3));
    }

}
