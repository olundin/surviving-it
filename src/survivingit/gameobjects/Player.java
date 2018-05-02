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
import survivingit.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the Player, inherits from Creature and implemens Observable.
 *
 * Contains functionality for inventory/Item usage and maintenance, and has observers for health updating.
 */
public class Player extends Creature implements Observable<Player> {

    private static final int PASSIVE_STORAGE_SIZE = 15;
    private static final int EQUIPPABLE_STORAGE_SIZE = 5;

    private List<Observer<Player>> observers;
    private PlayerInventory playerInventory;

    /**
     * Creates a new Player object with the entered x and y position.
     * @param x double val of the x position of the new Player object.
     * @param y double val of the y position of the new Player object.
     */
    public Player(final double x, final double y, Scene scene) {
	    super(x,
              y,
              scene,
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

    /**
     * Sets the current health of the player to the entered amount.
     *
     * Also notifies the player's observers.
     * @param currentHealth int value to set the players's current health to.
     */
    @Override
    public void setCurrentHealth(final int currentHealth) {
        super.setCurrentHealth(currentHealth);
        this.notifyObservers(this);
    }

    /**
     * Sets the max health of the player to the entered amount.
     *
     * Also notifies the player's observers.
     * @param maxHealth int value to set the players's max health to.
     */
    @Override
    public void setMaxHealth(final int maxHealth) {
        super.setMaxHealth(maxHealth);
        this.notifyObservers(this);
    }

    /**
     * Returns the player's inventory.
     * @return PlayerInventory of the player.
     */
    public PlayerInventory getPlayerInventory() {
        return this.playerInventory;
    }

    /**
     * Adds the entered item to the first available spot in the PlayerInventory.
     * @param item
     */
    public void addItemToFirstAvailable(Item item) {
        this.playerInventory.addItemToFirstAvailable(item);
    }

    /**
     * Attaches the entered player observer to the player.
     * @param observer Observer to be attatched.
     */
    @Override
    public void attach(Observer<Player> observer) {
        this.observers.add(observer);
    }

    /**
     * Notifies the player's observers with the entered data.
     * @param data Player data to notify the observers with.
     */
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

    /**
     * Receives and reacts to the entered Message.
     * @param msg Message to react to.
     */
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

    /**
     * Changes the equipped item index of the player with the entered number of spaces.
     * @param i int value of number of spaces to change the equipped index with.
     */
    public void changeEquippedItem(int i) {
        this.playerInventory.changeEquippedItem(i);
    }

    /**
     * Uses the player's equipped item.
     */
    public void useEquippedItem() {
        this.playerInventory.useEquippedItem(this);
    }

    private boolean isCarryingWeapon() {
        return this.playerInventory.isCarryingWeapon();
    }
}
