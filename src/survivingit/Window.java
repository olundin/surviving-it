package survivingit;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends Frame {

    private int width;
    private int height;

    private static final String TITLE = "Surviving it";

    public Window(int width, int height) {
	super(TITLE);

        this.width = width;
        this.height = height;

        this.setSize(width, height);
        this.setResizable(false);
        this.setVisible(true);
        this.requestFocus();

	this.addWindowListener(new WindowAdapter(){
	    public void windowClosing(WindowEvent we){
		System.exit(0);
	    }
	}); // TODO: Make this call Game.stop(), which in turn calls for System.exit(0);
    }

}
