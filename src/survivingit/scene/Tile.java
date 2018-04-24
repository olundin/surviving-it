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
     */
    public static final TileGroup SNOW = new TileGroup(Sprite.SNOW, new boolean[]{true,true,true,true,true,true,true,true,true,true,false,false,true,true,true,true,true,true});
    public static final TileGroup ROCK = new TileGroup(Sprite.ROCK, new boolean[]{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true});
    public static final TileGroup ICE = new TileGroup(Sprite.ICE, new boolean[]{true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true});
    public static final TileGroup WATER = new TileGroup(Sprite.WATER, new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false});

    public static final Tile DEFAULT = new Tile(Sprite.WATER[4], false);

    private Sprite sprite;
    private boolean passable;

    public Tile(Sprite sprite, boolean passable) {
        this.sprite = sprite;
        this.passable = passable;
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