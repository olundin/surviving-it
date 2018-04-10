package survivingit.scene;

import survivingit.graphics.Sprite;

public class Tile {

    public static final Tile SNOW_PLAIN = new Tile(Sprite.SNOW_PLAIN, true);
    public static final Tile SNOW_BUMPY = new Tile(Sprite.SNOW_BUMPY, true);
    public static final Tile SNOW_ROCK = new Tile(Sprite.SNOW_ROCK, false);
    public static final Tile SNOW_BUSH = new Tile(Sprite.SNOW_BUSH, false);
    public static final Tile SNOW_PUDDLE = new Tile(Sprite.SNOW_PUDDLE, true);
    public static final Tile SNOW_TRACKS = new Tile(Sprite.SNOW_TRACKS, true);
    public static final Tile SNOW_BRANCH = new Tile(Sprite.SNOW_BRANCH, true);
    public static final Tile SNOW_PEBBLES = new Tile(Sprite.SNOW_PEBBLES, true);
    public static final Tile WATER = new Tile(Sprite.WATER, false);

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

    public static Tile getTile(int t) {
        switch(t) {
            case 0:
                return Tile.SNOW_PLAIN;
            case 1:
                return Tile.SNOW_BUMPY;
            case 2:
                return Tile.SNOW_ROCK;
            case 3:
                return Tile.SNOW_BUSH;
            case 4:
                return Tile.SNOW_PUDDLE;
            case 5:
                return Tile.SNOW_TRACKS;
            case 6:
                return Tile.SNOW_BRANCH;
            case 7:
                return Tile.SNOW_PEBBLES;
            default:
                return Tile.SNOW_PLAIN;
        }
    }
}