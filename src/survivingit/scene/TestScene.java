package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.items.ItemFactory;
import survivingit.items.ItemType;

public class TestScene extends Scene {

    public TestScene() {
        super(256, 256);

        SceneGenerator generator = new SceneGenerator(5,1.25,1.25,2.5);
        tiles = generator.generateTiles(this);

        Player player = new Player(0.5, 0.5);
        for (int i = 0; i < 10; i++) {
            player.addItemToFirstAvilable(ItemFactory.createItem(ItemType.KNIFE));
            player.addItemToFirstAvilable(ItemFactory.createItem(ItemType.BOOTS));
        }
        this.addPlayer(player);

        this.tryAdd(new Campfire(3.5, 3.5));
        for(int x = 5; x < 32; x += 10) {
            for(int y = 5; y < 32; y += 10) {
                this.tryAdd(new Fox(x + 0.5, y + 0.5));
            }
        }
    }
}