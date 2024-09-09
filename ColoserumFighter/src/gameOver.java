import javax.swing.*;
import java.awt.*;



public class gameOver {

    public static void gameOver(Graphics g, int SCREEN_WIDTH, int SCREEN_HEIGHT, int applesEaten){
        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 75));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH-metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        FontMetrics metrics2 = g.getFontMetrics(g.getFont());
        g.drawString("Score: "+ applesEaten, (SCREEN_WIDTH-metrics2.stringWidth("Score: "+ applesEaten))/2, g.getFont().getSize());
    }
}
