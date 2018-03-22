package survivingit.items;

import survivingit.gameobjects.GameObject;
import survivingit.graphics.Sprite;

public class Food extends Item implements ConsumableItem {

    private int healthRestore;
    private boolean cookable;

    public Food(String description, Sprite sprite, int healthRestore, boolean cookable) {
        super(description, sprite);
        this.healthRestore = healthRestore;
        this.cookable = cookable;
    }

    public void consume(GameObject consumer) {

    }


}
