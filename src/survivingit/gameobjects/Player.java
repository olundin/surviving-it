package survivingit.gameobjects;

import survivingit.containers.PlayerInventory;
import survivingit.graphics.Sprite;
import survivingit.items.Item;

public class Player extends Creature {

    private static final int PASSIVE_INVENTORY_SIZE = 10;
    private static final int EQUIPPABLE_INVENTORY_SIZE = 4;

    private PlayerInventory inventory;

    public Player(final double x, final double y, final Sprite sprite, final int health, final double moveSpeed) {
	    super(x, y, sprite, health, moveSpeed);
	    this.inventory = new PlayerInventory(PASSIVE_INVENTORY_SIZE, EQUIPPABLE_INVENTORY_SIZE);
    }

    @Override
    public void update(double dt) {
        this.move(this.direction.x * this.moveSpeed * dt, this.direction.y * this.moveSpeed * dt);
    }

    public void addItemToFirstAvilable(Item item) {
        this.inventory.addItemToFirstAvailable(item);
    }
}
