package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.messaging.Observable;
import survivingit.messaging.Observer;
import survivingit.physics.Collider;

import java.util.ArrayList;
import java.util.List;

public class Player extends Creature implements Observable<Player> {

    private List<Observer<Player>> observers;

    private double timer = 0.0;

    public Player(final double x, final double y) {
	    super(x,
              y,
              new CreatureSprite(SpriteSheet.HERO, 0, 0, 24, 52),
              50,
              1);

	    this.setCollider(new Collider(0.2, 1.25, 0.35, 0.35, false, this));
	    observers = new ArrayList<>();
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

    public void attach(Observer<Player> observer) {
        this.observers.add(observer);
    }

    public void notifyObservers(Player data) {
        for(Observer<Player> o : observers) {
            o.onNotify(this, data);
        }
    }

}
