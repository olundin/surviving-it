package survivingit.scene;

import survivingit.gameobjects.*;

public class TestScene extends Scene {

    public TestScene() {
        super(32, 32);
        this.addPlayer(new Player(0.5, 0.5));
        this.add(new Campfire(5, 5));
        this.randomizeTiles();
        for(int x = 1; x < 32; x += 5) {
            for(int y = 0; y < 32; y += 5) {
                if(this.getTileAt(x, y).isPassable()) {
                    // Add fox if tile is walkable
                    this.add(new Fox(x + 0.5, y +  0.5));
                }
            }
        }
    }
}
