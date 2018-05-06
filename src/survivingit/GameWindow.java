package survivingit;

import survivingit.messaging.Observable;
import survivingit.messaging.Observer;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The window class.
 *
 * The GameWindow class is basically a wrapper
 * for java.awt.Frame, with some extra logic.
 */
public class GameWindow extends Frame implements Observable<GameWindow> {

    private static final String TITLE = "Surviving it";

    private boolean open; // Is the window open?

    private List<Observer<GameWindow>> observers;

    /**
     * Creates a new window.
     * @param width The width in pixels of the window.
     * @param height The height in pixels of the window.
     */
    public GameWindow(int width, int height) {
        super(TITLE);

        this.setSize(width, height);
        this.setResizable(false);

        if (Game.FULLSCREEN) {
            this.setExtendedState(Frame.MAXIMIZED_BOTH);
            this.setUndecorated(true);
        }

        this.setVisible(true);
        this.requestFocus();

        this.open = true;

        observers = new ArrayList<>();

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we) {
                onClose();
            }
        });
    }

    /**
     * Attaches an observer to this object.
     * @param observer observer to attach.
     */
    @Override
    public void attach(Observer<GameWindow> observer) {
        this.observers.add(observer);
    }

    /**
     * Notifies attached observers.
     */
    @Override
    public void notifyObservers() {
        for(Observer<GameWindow> o : observers) {
            o.onNotify(this);
        }
    }

    private void onClose() {
        this.open = false;
        // Notify observers when window is closed
        notifyObservers();
    }

    /**
     * Returns whether or not the window is open
     * @return window open status
     */
    public boolean isOpen() {
        return this.open;
    }

}
