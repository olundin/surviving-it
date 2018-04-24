package survivingit.scene;

import survivingit.graphics.Sprite;

public class Tile {

    /* Tiles are stored as follows...
    [
        top-left, top, top-right,
        left, center0, right,
        bottom-left, bottom, bottom-right,
        center1, center2, center3,
        center4, center5, center6,
        center7, center8, center9
    ]
    ... where center7 - center9 are not passable
     */
    public static final Tile[] SNOW = tileGroup(Sprite.SNOW, new boolean[]{true,true,true,true,true,true,true,true,true,true,false,false,true,true,true,true,true,true});
    public static final Tile[] ROCK = tileGroup(Sprite.ROCK, new boolean[]{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true});
    public static final Tile[] ICE = tileGroup(Sprite.ICE, new boolean[]{true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true});
    public static final Tile[] WATER = tileGroup(Sprite.WATER, new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false});

    public static final Tile DEFAULT = new Tile(Sprite.WATER[4], false);

    private Sprite sprite;
    private boolean passable;

    public Tile(Sprite sprite, boolean passable) {
        this.sprite = sprite;
        this.passable = passable;
    }

    private static Tile[] tileGroup(Sprite[] sprites, boolean[] passable) {
        // Creates tile group. Requires sprites[] and passable to be of the size 18
        Tile[] tiles = new Tile[18];
        for(int i = 0; i < 18; i++) {
            tiles[i] = new Tile(sprites[i], passable[i]);
        }
        return tiles;
    }

    public Sprite getSprite() {
	return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public boolean isPassable() {
        return this.passable;
    }
}