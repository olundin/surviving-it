package survivingit.scene;

import survivingit.graphics.Sprite;

public class Tile {

    public static final TileGroup SNOW = new TileGroup(Sprite.SNOW, new boolean[]{true,false,false,true,true,true,true,true,true}, true);
    public static final TileGroup ROCK = new TileGroup(Sprite.ROCK, new boolean[]{true,true,true,true,true,true,true,true,true}, false);
    public static final TileGroup ICE = new TileGroup(Sprite.ICE, new boolean[]{true,true,true,true,true,true,true,true,true}, false);
    public static final TileGroup WATER = new TileGroup(Sprite.WATER, new boolean[]{false,false,false,false,false,false,false,false,false}, false);
    public static final TileGroup VOID = new TileGroup(Sprite.VOID, new boolean[]{false,false,false,false,false,false,false,false,false}, false);

    public static final Tile DEFAULT = VOID.getPlain();
    public static final Tile WALL = new Tile(Sprite.WALL, false, false);

    private Sprite sprite;
    private boolean passable;
    private boolean fertile;

    public Tile(Sprite sprite, boolean passable, boolean fertile) {
        this.sprite = sprite;
        this.passable = passable;
        this.fertile = fertile;
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

    public boolean isFertile() { return this.fertile; }
}