package survivingit.gameobjects;

import survivingit.containers.PlayerInventory;
import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.items.*;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
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
              Integer.MAX_VALUE/2,
                1,
                2);
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

    /**
     * Perform a player attack with either the equipped weapon or just the players bare hands in the direction of the
     * target cords.
     * @param targetX double of the deltaX position for the attack to be performed in.
     * @param targetY double of the deltaY position for the attack to be performed in.
     */
    public void playerAttack(final double targetX, final double targetY) {
        double angle = getAngleTo(targetX, targetY);
        if (isCarryingWeapon()) {
            List<Effect> attackEffects = playerInventory.getEquippedItemContainer().getEquippedItem().getEffectsOfEffectType(EffectType.ATTACK);
            for (Effect effect : attackEffects) {
                if (effect instanceof MeleeAttackEffect) {
                    MeleeAttackEffect meleeAttackEffect = (MeleeAttackEffect) effect;
                    meleeAttackEffect.attack(this, angle);
                }
            }
        } else {
            performAttack(this.damage, this.range, angle);
        }
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

    private boolean isCarryingWeapon() {
        return this.playerInventory.isCarryingWeapon();
    }
}
