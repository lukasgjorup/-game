import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Management extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 100;
    static final int SCREEN_HEIGHT = 1200;
    private int startBar;
    public Management(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.startBar = Player.healthBarI;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(25, 25, 50, SCREEN_HEIGHT-50);

        g.setColor(new Color(255, 0, 0));  // Health bar color


        // Draw a vertical health bar. The height depends on the health value.
        int barHeight = (int) (SCREEN_HEIGHT * (Player.healthBarI / 100.0));  // Calculate height based on health percentage
        g.fillRect(25, SCREEN_HEIGHT+25 - barHeight, 50, barHeight-50);  // Draw the health bar
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
