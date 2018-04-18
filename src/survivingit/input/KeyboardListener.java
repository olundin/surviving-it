package survivingit.input;

import survivingit.messaging.Observable;
import survivingit.messaging.Observer;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyboardListener implements KeyListener, Observable<KeyEvent> {

    private List<Observer<KeyEvent>> observers;

    public KeyboardListener() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer<KeyEvent> observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers(KeyEvent data) {
        for (Observer<KeyEvent> observer : this.observers) {
            observer.onNotify(this, data);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.notifyObservers(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.notifyObservers(e);
    }

    public void keyTyped(KeyEvent e) {
        this.notifyObservers(e);
    }

}
