package survivingit.input;

import survivingit.messaging.Observable;
import survivingit.messaging.Observer;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyboardListener implements KeyListener, Observable<KeyboardListener> {

    private List<Observer<KeyboardListener>> observers;

    public KeyboardListener() {
        this.observers = new ArrayList<>();
    }

    public void attach(Observer<KeyboardListener> observer) {
        this.observers.add(observer)
    }

    public void notifyObservers() {
        for (Observer<KeyboardListener> observer : this.observers) {
            observer.notify();
        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {}

}
