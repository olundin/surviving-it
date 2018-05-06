package survivingit.gameobjects;

import survivingit.containers.PlayerInventory;
import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.items.*;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
import survivingit.messaging.Observable;
import survivingit.messaging.Observer;
import survivingit.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the Player, inherits from Creature and implements Observable.
 *
 * Contains functionality for inventory/Item usage and maintenance, and has observers for health HUD updating.
 *
 * @see Creature
 * @see Observable
 */
public class Player extends Creature implements Observable<Player> {

    private static final int MAX_HEALTH = 50;
    private static final double MOVE_SPEED = 5.0;
    private static final int DAMAGE = 5;
    private static final int ALPHA_LEVEL = 1;
    private static final double RANGE = 2.0;

    private static final double COL_X = -0.2;
    private static final double COL_Y = -0.5;
    private static final double COL_WIDTH = 0.4;
    private static final double COL_HEIGHT = 0.5;

    private static final int PASSIVE_STORAGE_SIZE = 15;
    private static final int EQUIPPABLE_STORAGE_SIZE = 5;

    private static final int SPRITE_WIDTH = 24;
    private static final int SPRITE_HEIGHT = 52;

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
              new CreatureSprite(SpriteSheet.PLAYER, 0, 0, SPRITE_WIDTH, SPRITE_HEIGHT),
              MAX_HEALTH,
              MOVE_SPEED,
              DAMAGE,
              ALPHA_LEVEL,
              RANGE);

        this.setCollider(new Collider(COL_X, COL_Y, COL_WIDTH, COL_HEIGHT, false, this));

        observers = new ArrayList<>();
	    this.playerInventory = new PlayerInventory(PASSIVE_STORAGE_SIZE, EQUIPPABLE_STORAGE_SIZE);
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
     * @param item Item to add
     */
    public void addItemToFirstAvailable(Item item) {
        this.playerInventory.addItemToFirstAvailable(item);
    }

    /**
     * Perform a player attack with either the equipped weapon or just the players bare hands around the player.
     */
    public void playerAttack() {
        if (isCarryingWeapon()) {
            List<Effect> attackEffects = playerInventory.getEquippedItemContainer().getEquippedItem().getEffectsOfEffectType(EffectType.ATTACK);
            for (Effect effect : attackEffects) {
                // Inspection  warning ignored since this solution is the most effective one for our solution.
                // The item system is component based and therefore difficult to abstractify.
                //noinspection InstanceofConcreteClass
                if (effect instanceof MeleeAttackEffect) {
                    MeleeAttackEffect meleeAttackEffect = (MeleeAttackEffect) effect;
                    meleeAttackEffect.attack(this);
                }
            }
        } else {
            performAttack(this.damage, this.range);
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

    /**
     * Sets the current health of the player to the entered amount.
     *
     * Also notifies the player's observers.
     * @param currentHealth int value to set the players's current health to.
     */
    @Override
    public void setCurrentHealth(final int currentHealth) {
        super.setCurrentHealth(currentHealth);
        this.notifyObservers();
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
     */
    @Override
    public void notifyObservers() {
        for(Observer<Player> o : observers) {
            o.onNotify(this);
        }
    }

    /**
     * Receives and reacts to the entered Message.
     *
     * The player receives the message and acts differently based on what type of message it is.
     * If the entered message is a Damage message then the animal takes damage equal to the message data.
     * If the entered message is a Heal message then the animal heals hp equal to the message data
     * @param msg Message object to react to.
     */
    @Override
    public void receiveMessage(Message msg) {
        MessageType type = msg.getType();
        int data = msg.getData();
        switch(type) {
            case DAMAGE:
                this.takeDamage(data);
                break;
            case HEAL:
                this.heal(data);
                break;
            default:
                break;
        }
    }
}
