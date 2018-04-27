package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.items.ItemFactory;
import survivingit.items.ItemType;

public class TestScene extends Scene {

    public TestScene() {
        super(256, 256);

        // Add player
        Player player = new Player(Math.floor(width/2) + 0.5, Math.floor(height/2) + 0.5);
        for (int i = 0; i < 10; i++) {
            player.addItemToFirstAvilable(ItemFactory.createItem(ItemType.KNIFE));
            player.addItemToFirstAvilable(ItemFactory.createItem(ItemType.BOOTS));
        }
        addPlayer(player);

        // Generate tiles, trees, e.t.c.
        SceneGenerator generator = new SceneGenerator(2.0,2.0,0.5,3.0);
        generator.generateScene(this, true, true, true);

        // Try to add some animals
        tryAdd(new Penguin(Math.floor(width/2) - 10.5, Math.floor(height/2)));
        tryAdd(new Boar(Math.floor(width/2) + 10.5, Math.floor(height/2)));
        tryAdd(new Fox(Math.floor(width/2), Math.floor(height/2) + 10.5));
        add(new Yeti(64, 64));
        add(new Yeti(80, 80));
    }
}