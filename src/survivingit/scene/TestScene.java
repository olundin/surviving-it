package survivingit.scene;

import survivingit.gameobjects.*;

public class TestScene extends Scene {

    public TestScene() {
        super(32, 32);
        this.addPlayer(new Player(0.5, 0.5));
        //this.add(new Campfire(5.5, 5.5));
        this.randomizeTiles();
        //this.fillTiles(0, 0, 32, 32, Tile.SNOW_PLAIN);
        //this.add(new Fox(5, 5));
        for(int x = 5; x < 32; x += 10) {
            for(int y = 5; y < 32; y += 10) {
                if(this.getTileAt(x, y).isPassable()) {
                    // Add fox if tile is walkable
                    this.add(new Fox(x + 0.5, y +  0.5));
                }
            }
        }
    }
}
