package survivingit.scene;

import survivingit.graphics.Sprite;

public class Tile {

    public static final Tile SNOW_1 = new Tile(Sprite.SNOW_1, true);
    public static final Tile SNOW_2 = new Tile(Sprite.SNOW_2, false);

    public static final Tile TEST = new Tile(Sprite.FOX, true);

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
