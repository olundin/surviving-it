package survivingit.scene;

import survivingit.graphics.AnimatedSprite;
import survivingit.graphics.Sprite;

public class Tile {

    public static final TileGroup SNOW = new TileGroup(Sprite.SNOW, new boolean[]{true,false,false,true,true,true,true,true,true}, true);
    public static final TileGroup ROCK = new TileGroup(Sprite.ROCK, new boolean[]{true,true,true,true,true,true,true,true,true}, false);
    public static final TileGroup ICE = new TileGroup(Sprite.ICE, new boolean[]{true,true,true,true,true,true,true,true,true}, false);

    public static final AnimatedTile WATER = new AnimatedTile(new AnimatedSprite(Sprite.WATER, 0.2), false, false);
    public static final AnimatedTile VOID = new AnimatedTile(new AnimatedSprite(Sprite.VOID, 0.5), false, false);
    public static final Tile WALL = new Tile(Sprite.WALL, false, false);

    private Sprite sprite;
    private boolean passable;
    private boolean fertile;

    public Tile(Sprite sprite, boolean passable, boolean fertile) {
        this.sprite = sprite;
        this.passable = passable;
        this.fertile = fertile;
    }

    public static void updateAnimated(double dt) {
        WATER.update(dt);
        VOID.update(dt);
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