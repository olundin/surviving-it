package survivingit;

import survivingit.gameobjects.Player;
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
 * The Window class is basically a wrapper
 * for java.awt.Frame, with some extra logic.
 */
public class Window extends Frame implements Observable<Window> {

    private int width;
    private int height;

    private static final String TITLE = "Surviving it";

    private boolean open; // Is the window open?

    private List<Observer<Window>> observers;

    /**
     * Creates a new window.
     * @param width The width in pixels of the window.
     * @param height The height in pixels of the window.
     */
    public Window(int width, int height) {
        super(TITLE);

        this.width = width;
        this.height = height;

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
    public void attach(Observer<Window> observer) {
        this.observers.add(observer);
    }

    /**
     * Notifies attached observers.
     * @param data The object to notify with.
     */
    public void notifyObservers(Window data) {
        for(Observer<Window> o : observers) {
            o.onNotify(this, data);
        }
    }

    private void onClose() {
        this.open = false;
        // Notify observers when window is closed
        notifyObservers(this);
    }

    /**
     * Returns whether or not the window is open
     * @return window open status
     */
    public boolean isOpen() {
        return this.open;
    }

}
