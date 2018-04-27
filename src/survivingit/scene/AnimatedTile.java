package survivingit.scene;

import survivingit.graphics.AnimatedSprite;

public class AnimatedTile extends Tile {

    private AnimatedSprite sprite;

    public AnimatedTile(AnimatedSprite sprite, boolean passable, boolean fertile) {
        super(sprite.getSprite(), passable, fertile);
        this.sprite = sprite;
    }

    public void update(double dt) {
        sprite.update(dt);
        this.setSprite(sprite.getSprite());
    }

}
