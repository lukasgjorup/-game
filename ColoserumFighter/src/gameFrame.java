import javax.swing.*;
import java.awt.*;

public class gameFrame extends JFrame {
    public static Point frameLocation;
    gameFrame(){
        Management healthBar = new Management();
        gamePanel gamePanel = new gamePanel(this,healthBar);
        // Set layout
        this.setLayout(new BorderLayout());
        // Add components with specific layout positions
        this.add(healthBar, BorderLayout.EAST);  // Health bar at the top
        this.add(gamePanel, BorderLayout.CENTER); // Game panel in the center

        this.setTitle("Coloserum Fighter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}