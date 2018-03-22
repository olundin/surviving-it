package survivingit.input;

import survivingit.util.Vec2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    private boolean leftDown;
    private boolean rightDown;

    private boolean scrollDown;
    private int scroll;

    private Vec2 position;

    public Mouse() {

    }

    public void mousePressed(MouseEvent e) {
        System.out.println(e.getX() + ", " + e.getY());
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("hej");
    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

}
