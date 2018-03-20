package survivingit.scene;

import survivingit.graphics.Sprite;

public class Tile {

    private Sprite sprite;
    private boolean passable;

    public Tile(Sprite sprite, boolean passable) {
        this.sprite = sprite;
        this.passable = passable;
    }

    public Sprite getSprite() {
	return sprite;
    }
}
