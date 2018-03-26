package survivingit.messaging;

import survivingit.gameobjects.GameObject;
import survivingit.gameobjects.Player;

public interface PlayerObserver {

    public void onNotify(Player gameObject);

}
