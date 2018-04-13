package survivingit;

import survivingit.gameobjects.Player;
import survivingit.messaging.Observable;
import survivingit.messaging.Observer;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Window extends Frame implements Observable<Window> {

    private int width;
    private int height;

    private static final String TITLE = "Surviving it";

    private boolean open; // Is the window open?

    private List<Observer<Window>> observers;

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
        }); // TODO: Make this call Game.stop(), which in turn calls for System.exit(0);
    }

    public void attach(Observer<Window> observer) {
        this.observers.add(observer);
    }

    public void notifyObservers(Window data) {
        for(Observer<Window> o : observers) {
            o.onNotify(this, data);
        }
    }

    public void onClose() {
        this.open = false;
        notifyObservers(this);
    }

    public boolean isOpen() {
        return this.open;
    }

}
