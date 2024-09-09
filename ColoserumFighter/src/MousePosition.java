import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JFrame;

public class MousePosition {
    private int x;
    private int y;
    private JFrame frame;

    public MousePosition(JFrame frame) {
        this.frame = frame;
        this.x = 0;
        this.y = 0;
    }

    public void update() {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        Point frameLocation = frame.getLocationOnScreen();
        this.x = mouseLocation.x - frameLocation.x;
        this.y = mouseLocation.y - frameLocation.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}