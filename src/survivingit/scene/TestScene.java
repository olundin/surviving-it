package survivingit.scene;

import survivingit.gameobjects.*;

import java.util.Random;

public class TestScene extends Scene {

    public TestScene(Camera camera) {
        super(32, 32, camera);
        this.addPlayer(new Player(-1, -1));
        this.add(new Campfire(-3, -3));
        for(int x = 0; x < 16; x++) {
            for(int y = 0; y < 16; y++) {
                this.add(new Fox(x, y));
            }
        }
    }

}
