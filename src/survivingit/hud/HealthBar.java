package survivingit.hud;

import survivingit.gameobjects.Player;
import survivingit.graphics.Sprite;
import survivingit.messaging.Observable;
import survivingit.messaging.Observer;

/**
 * A progress bar dedicated to the player's health
 */
public class HealthBar extends ProgressBar implements Observer<Player> {

    /**
     * Creates a new health bar for the given player
     * @param x X position, percentage of screen width
     * @param y Y position, percentage of screen height
     * @param width Width, percentage of screen width
     * @param height Height, percentage of screen height
     * @param player The player
     */
    public HealthBar(final double x, final double y, final double width, final double height, Player player) {
        super(x, y, width, height, 0, player.getMaxHealth(), player.getCurrentHealth(), Sprite.PROGRESS_BAR_FILL_RED);
        player.attach(this);
    }

    /**
     * Called by  player when it's health changes.
     * @param object The observable object
     * @param data It's data (the object itself)
     */
    @Override
    public void onNotify(Observable<Player> object, Player data) {
        this.max = data.getMaxHealth();
        this.current = data.getCurrentHealth();
    }

}
