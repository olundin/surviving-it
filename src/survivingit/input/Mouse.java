package survivingit.input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    private boolean leftDown;
    private boolean rightDown;

    private boolean scrollDown;
    private int scroll;

    private double x;
    private double y;

    public Mouse() {
        this.x = 0;
        this.y = 0;
    }

    public void update() {
        this.x = MouseInfo.getPointerInfo().getLocation().getX();
        this.y = MouseInfo.getPointerInfo().getLocation().getY();
    }


    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }



}
