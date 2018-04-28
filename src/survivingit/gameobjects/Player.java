package survivingit.gameobjects;

import survivingit.containers.PlayerInventory;
import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
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

    public Player(final double x, final double y) {
	    super(x,
              y,
              new CreatureSprite(SpriteSheet.HERO, 0, 0, 24, 52),
              50,
                10,
              1,
                1,
                1);
	    observers = new ArrayList<>();
	    this.setCollider(new Collider(-0.2, -0.5, 0.4, 0.5, false, this));
	    this.playerInventory = new PlayerInventory(PASSIVE_STORAGE_SIZE, EQUIPPABLE_STORAGE_SIZE);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
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

    @Override
    public void attach(Observer<Player> observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers(Player data) {
        for(Observer<Player> o : observers) {
            o.onNotify(this, data);
        }
    }

    // TODO: REMOVE! Only a temporary method for testing
    public void attack() {
        sendMesageToCreaturesInArea(new Message(MessageType.ATTACK, damage), range, range);
    }

    @Override
    public void receiveMessage(Message msg) {
        MessageType type = msg.getType();
        int data = msg.getData();
        switch(type) {
            case ATTACK:
                this.takeDamage(data);
                break;
            case ITEM:
                break;
            case HEAL:
                this.heal(data);
                break;
            default:
                break;
        }
    }

    public void changeEquippedItem(int i) {
        this.playerInventory.changeEquippedItem(i);
    }

    public void useEquippedItem() {
        this.playerInventory.useEquippedItem(this);
    }
}
