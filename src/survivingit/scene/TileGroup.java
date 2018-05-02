package survivingit.scene;

import survivingit.Game;
import survivingit.graphics.Sprite;

import java.util.Random;
import java.util.Set;

/**
 * A group of tiles that belong together.
 * Usually contains a single plain tile that is the normal tile in the group,
 * and x number of other tiles that match that tile but may be a bit different.
 *
 * @see Tile
 */
public class TileGroup {

    private Tile[] tiles;
    private int size;

    /**
     * Creates a new tile group from a sprite array with given parameters
     * @param sprites The sprites to use. The first sprite should be the most plain one.
     * @param passable Which tiles should be passable
     * @param fertile If true, the tiles will be considered fertile (vegetation can be generated on them)
     */
    public TileGroup(Sprite[] sprites, boolean[] passable, boolean fertile) {
        this.size = Math.min(sprites.length, passable.length);
        this.tiles = new Tile[size];
        for(int i = 0; i < size; i++) {
            tiles[i] = new Tile(sprites[i], passable[i], fertile);
        }
    }

    /**
     * Returns the tiles of the group
     * @return group tiles
     */
    public Tile[] getTiles() {
        return tiles;
    }

    /**
     * Returns the most basic tile in the group
     * @return the plain tile
     */
    public Tile getPlain() { return tiles[0]; }

    /**
     * Returns a random tile from the group.
     * The first tile in the group (the plain tile) will be more likely than the rest
     * @return Random tile from the group.
     */
    public Tile getRandom() {
        // 10 is just a number that works good
        int rand = Game.RANDOM.nextInt(size*10);
        if(rand >= size) return tiles[0];
        else return tiles[rand];
    }

}
