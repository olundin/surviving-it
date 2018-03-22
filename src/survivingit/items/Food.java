package survivingit.items;

import survivingit.gameobjects.Creature;
import survivingit.graphics.Sprite;

public class Food extends Item implements ConsumableItem {

    private int healAmount;
    private boolean cookable;

    public Food(String description, Sprite sprite, int healAmount, boolean cookable) {
        super(description, sprite);
        this.healAmount = healAmount;
        this.cookable = cookable;
    }

    public void consume(Creature consumer) {
        consumer.heal(this.healAmount);
    }

    public boolean isCookable() {
        return cookable;
    }

}
