package survivingit.hud;

import survivingit.gameobjects.Player;
import survivingit.messaging.PlayerObserver;

public class HealthProgressBar extends ProgressBar implements PlayerObserver {

    public HealthProgressBar(final double x, final double y, final double width, final double height) {
        super(x, y, width, height);
        this.min = 0;
    }

    public void onNotify(Player player) {

    }

}
