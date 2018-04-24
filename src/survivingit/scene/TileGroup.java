package survivingit.scene;

import survivingit.graphics.Sprite;

import java.util.Random;

public class TileGroup {

    /* Tiles are stored as follows...
    [
        top-left, top, top-right,
        left, center0, right,
        bottom-left, bottom, bottom-right,
        center1, center2, center3,
        center4, center5, center6,
        center7, center8, center9
    ]
     */
    private Tile[] tiles;
    private static Random random;

    public TileGroup(Sprite[] sprites, boolean[] passable) {
        // Creates tile group. Requires sprites[] and passable to be of the size 18
        // Will use booleans in passable array to set passable
        tiles = new Tile[18];
        for(int i = 0; i < 18; i++) {
            tiles[i] = new Tile(sprites[i], passable[i]);
        }
        random = new Random();
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public Tile getTopLeft() { return tiles[0]; }
    public Tile getTop() { return tiles[1]; }
    public Tile getTopRight() { return tiles[2]; }
    public Tile getLeft() { return tiles[3]; }
    public Tile getCenter() {
        // Return a random center tile
        int n = random.nextInt(10);
        if(n == 0) return tiles[4];
        else return tiles[8+n];
    }
    public Tile getRight() { return tiles[5]; }
    public Tile getBottomLeft() { return tiles[6]; }
    public Tile getBottom() { return tiles[7]; }
    public Tile getBottomRight() { return tiles[8]; }

}
