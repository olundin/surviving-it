package survivingit.gameobjects;

import survivingit.graphics.Sprite;
import survivingit.messaging.PlayerObserver;
import survivingit.physics.Collider;

public class Player extends Creature {

    private double secondTimer;

    private double lastX;
    private double lastY;
    private int frame;

    private PlayerObserver healthObserver;

    private Sprite[] sprites;

    public Player(final double x, final double y) {
	    super(x, y, Sprite.HERO_DOWN[0], 50, 5);
	    this.setCollider(new Collider(0.2, 1.25, 0.35, 0.35, false, this));

	    this.secondTimer = 0.0;
	    this.lastX = this.x;
	    this.lastY = this.y;
	    this.frame = 0;
	    sprites = Sprite.HERO_DOWN;
    }


    public void update(double dt) {
        super.update(dt);

        /*
        secondTimer += dt;

        switch(this.direction) {
            case LEFT:
                sprites = Sprite.HERO_LEFT;
                break;
            case UP:
                sprites = Sprite.HERO_UP;
                break;
            case RIGHT:
                sprites = Sprite.HERO_RIGHT;
                break;
            case DOWN:
                sprites = Sprite.HERO_DOWN;
                break;
        }
        if(Math.abs(this.x - this.lastX) > 0.001 || Math.abs(this.y - this.lastY) > 0.001) {
            if(secondTimer >= 0.4) {
                frame++;
                if(frame > 3) {
                    frame = 0;
                }
                secondTimer = 0.0;
            }
            setSprite(sprites[frame]);
        } else {
            if(secondTimer >= 3) {
                this.setSprite(sprites[4]);
            }
            if(secondTimer >= 4) {
                this.setSprite(sprites[0]);
                secondTimer = 0.0;
            }
        }
        */

        this.lastX = this.x;
        this.lastY = this.y;
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
