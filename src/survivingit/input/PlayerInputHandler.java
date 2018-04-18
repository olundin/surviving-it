package survivingit.input;

import survivingit.gameobjects.Player;
import survivingit.messaging.Observable;
import survivingit.messaging.Observer;

import java.awt.event.KeyEvent;

/**
 * Created by AngusLothian on 2018-04-18.
 */
public class PlayerInputHandler implements Observer<KeyEvent> {

    private Player player;

    public PlayerInputHandler(Player player) {
        this.player = player;
    }

    @Override
    public void onNotify(Observable<KeyEvent> object, KeyEvent data) {

    }

}
