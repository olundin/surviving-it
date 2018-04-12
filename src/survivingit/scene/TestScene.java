package survivingit.scene;

import survivingit.gameobjects.*;

public class TestScene extends Scene {

    public TestScene() {
        super(32, 32);
        this.addPlayer(new Player(0.5, 0.5));
        this.randomizeTiles();
        this.tryAdd(new Campfire(3.5, 3.5));
        for(int x = 5; x < 32; x += 10) {
            for(int y = 5; y < 32; y += 10) {
                this.tryAdd(new Fox(x + 0.5, y + 0.5));
            }
        }
    }
}
