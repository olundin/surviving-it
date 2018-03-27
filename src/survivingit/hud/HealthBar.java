package survivingit.hud;

import survivingit.gameobjects.Player;
import survivingit.messaging.PlayerObserver;

import java.awt.Color;

public class HealthBar extends ProgressBar implements PlayerObserver {

    public HealthBar(final double x, final double y, final double width, final double height, Player player) {
        super(x, y, width, height, Color.red);
        this.min = 0;
        this.max = player.getMaxHealth();
        this.current = player.getCurrentHealth();

        player.setHealthObserver(this);
    }

    public void onNotify(Player player) {
        this.max = player.getMaxHealth();
        this.current = player.getCurrentHealth();
    }

}
