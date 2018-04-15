package survivingit.input;

import survivingit.messaging.Observable;
import survivingit.messaging.Observer;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Keyboard implements KeyListener, Observable<Keyboard> {

    private List<Observer<Keyboard>> observers;

    public Keyboard() {
        this.observers = new ArrayList<>();
    }

    public void attach(Observer<Keyboard> observer) {
        this.observers.add(observer)
    }

    public void notifyObservers() {
        for (Observer<Keyboard> observer : this.observers) {
            observer.notify();
        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {}

}
