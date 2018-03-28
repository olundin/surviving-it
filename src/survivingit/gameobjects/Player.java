package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.messaging.PlayerObserver;
import survivingit.physics.Collider;

public class Player extends Creature {

    private PlayerObserver healthObserver;

    public Player(final double x, final double y) {
	    super(x,
              y,
              new CreatureSprite(SpriteSheet.HERO, 0, 0, 24, 52),
              50,
              3);

	    this.setCollider(new Collider(-0.2, 0.2, 0.4, 0.6, false, this));
    }

    @Override
    public void update(double dt) {
        super.update(dt);
    }


    // Functions manipulating health are overridden to update observers
    @Override
    public void setCurrentHealth(final int currentHealth) {
        super.setCurrentHealth(currentHealth);
        this.healthObserver.onNotify(this);
    }

    @Override
    public void setMaxHealth(final int maxHealth) {
        super.setMaxHealth(maxHealth);
        this.healthObserver.onNotify(this);
    }

    public void setHealthObserver(PlayerObserver observer) {
        this.healthObserver = observer;
    }


}
