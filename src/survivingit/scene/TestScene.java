package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.items.ItemFactory;
import survivingit.items.ItemType;

public class TestScene extends Scene {

    public TestScene() {
        super(256, 256);

        SceneGenerator generator = new SceneGenerator(2.0,2.0,0.5,3.0);
        generator.generateScene(this, true, true, true);
        add(new Fox(Math.floor(width/2) + 3.5, Math.floor(height/2) + 3.5));
        Player player = new Player(Math.floor(width/2) + 0.5, Math.floor(height/2) + 0.5);
        for (int i = 0; i < 10; i++) {
            player.addItemToFirstAvilable(ItemFactory.createItem(ItemType.KNIFE));
            player.addItemToFirstAvilable(ItemFactory.createItem(ItemType.BOOTS));
        }
        addPlayer(player);
    }
}