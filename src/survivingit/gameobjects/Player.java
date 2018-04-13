package survivingit.gameobjects;

import survivingit.containers.PlayerInventory;
import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.items.Item;
import survivingit.messaging.Observable;
import survivingit.messaging.Observer;
import survivingit.physics.Collider;

import java.util.ArrayList;
import java.util.List;

public class Player extends Creature implements Observable<Player> {

    private static final int PASSIVE_STORAGE_SIZE = 15;
    private static final int EQUIPPABLE_STORAGE_SIZE = 5;

    private List<Observer<Player>> observers;
    private PlayerInventory playerInventory;

    private double timer = 0.0;

    public Player(final double x, final double y) {
	    super(x,
              y,
              new CreatureSprite(SpriteSheet.HERO, 0, 0, 24, 52),
              50,
              5);
	    observers = new ArrayList<>();
	    this.setCollider(new Collider(-0.2, -0.5, 0.4, 0.5, false, this));
	    this.playerInventory = new PlayerInventory(PASSIVE_STORAGE_SIZE, EQUIPPABLE_STORAGE_SIZE);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        timer += dt;
        if(timer >= 1.0) {
            timer = 0.0;
            this.takeDamage(1);
        }
    }

    // Functions manipulating health are overridden to update observers
    @Override
    public void setCurrentHealth(final int currentHealth) {
        super.setCurrentHealth(currentHealth);
        this.notifyObservers(this);
    }

    @Override
    public void setMaxHealth(final int maxHealth) {
        super.setMaxHealth(maxHealth);
        this.notifyObservers(this);
    }

    public PlayerInventory getPlayerInventory() {
        return this.playerInventory;
    }

    public void addItemToFirstAvilable(Item item) {
        this.playerInventory.addItemToFirstAvailable(item);
    }

    public void attach(Observer<Player> observer) {
        this.observers.add(observer);
    }

    public void notifyObservers(Player data) {
        for(Observer<Player> o : observers) {
            o.onNotify(this, data);
        }
    }

    public void changeEquippedItem(int i) {
        this.playerInventory.changeEquippedItem(i);
    }
}