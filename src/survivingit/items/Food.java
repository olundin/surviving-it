package survivingit.items;

import survivingit.gameobjects.Creature;
import survivingit.graphics.Sprite;

public class Food extends Item implements ConsumableItem {



    private static final boolean stackable = true;

    private int healAmount;
    private boolean cookable;

    public Food(int healAmount, boolean cookable, String description, Sprite sprite) {
        super(description, sprite, stackable);
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
