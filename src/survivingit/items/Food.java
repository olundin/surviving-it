package survivingit.items;

import survivingit.gameobjects.Creature;
import survivingit.graphics.Sprite;

public class Food extends Item implements ConsumableItem {

    private final int healAmount;
    private final boolean cookable;

    public Food(final int healAmount, final boolean cookable, final String description, final Sprite sprite, final int stackSize) {
        super(description, sprite, stackSize);
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
