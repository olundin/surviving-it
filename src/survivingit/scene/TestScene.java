package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.graph.AStar;
import survivingit.graph.ChebyshevDistance;

public class TestScene extends Scene {

    public TestScene(Camera camera) {
        super(32, 32, camera);
        this.randomizeTiles();
        this.updateAStar();
        this.addPlayer(new Player(0.5, 0.5));
        this.add(new Campfire(-0.5, -0.5));
        this.add(new Fox(31.5, 31.5));
    }

}
