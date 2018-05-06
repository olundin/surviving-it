package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.items.ItemFactory;
import survivingit.items.ItemType;
import survivingit.util.Maths;

/**
 * Scene used for testing.
 *
 * @see Scene
 */
public class TestScene extends Scene {

    private static final int WIDTH = 128;
    private static final int HEIGHT = 128;

    private static final double SNOW_OCCURRENCE = 2.0;
    private static final double ROCK_OCCURRENCE = 2.0;
    private static final double ICE_OCCURRENCE = 0.5;
    private static final double WATER_OCCURRENCE = 3.0;

    /**
     * Creates a new test scene with the given camera.
     * @param camera The camera to see the scene.
     */
    public TestScene(Camera camera) {
        super(camera, WIDTH, HEIGHT);

        // Add player
        Player player = new Player(Maths.fastFloor(width/(double)2), Maths.fastFloor(height/(double)2), this);
        player.addItemToFirstAvailable(ItemFactory.createItem(ItemType.KNIFE));
        player.addItemToFirstAvailable(ItemFactory.createItem(ItemType.SPEAR));
        player.addItemToFirstAvailable(ItemFactory.createItem(ItemType.FLINT_AND_STEEL));
        player.addItemToFirstAvailable(ItemFactory.createItem(ItemType.BERRY));
        player.addItemToFirstAvailable(ItemFactory.createItem(ItemType.BOOTS));
        setPlayer(player);

        // Generate tiles, trees, e.t.c.
        SceneGenerator generator = new SceneGenerator(SNOW_OCCURRENCE,ROCK_OCCURRENCE,ICE_OCCURRENCE,WATER_OCCURRENCE);
        generator.generateScene(this, true, true, true, true);
    }
}