package survivingit.hud;

import survivingit.gameobjects.Player;
import survivingit.graphics.Sprite;
import survivingit.messaging.Observable;
import survivingit.messaging.Observer;

public class HealthBar extends ProgressBar implements Observer<Player> {

    public HealthBar(final double x, final double y, final double width, final double height, Player player) {
        super(x, y, width, height, 0, player.getMaxHealth(), player.getCurrentHealth(), Sprite.PROGRESS_BAR_FILL_RED);
        player.attach(this);
    }

    @Override
    public void onNotify(Observable<Player> object, Player data) {
        this.max = data.getMaxHealth();
        this.current = data.getCurrentHealth();
    }

}
