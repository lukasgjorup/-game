import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class gamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1200;
    static final int SCREEN_HEIGHT = 1200;
    static final int UNIT_SIZE = 50;
    static final int DELAY = 25;

    private int applesEaten;
    private int enemyNumbers=0;
    private int spawnTick=0;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private boolean lazerFire = true;
    private Timer timer;
    private Random random;
    private Player player;
    private MousePosition mousePosition;
    private lazer lazerGun;
    private Enemy enemy;
    private Enemy[] allEnemies = new Enemy[40];
    private int CloseE = 0;
    private int[] deadEnemy = new int[40];
    private int deadEnemyN = 0;




    public gamePanel(JFrame frame, Management healthBar) {
        random = new Random();
        player = new Player(healthBar,SCREEN_HEIGHT,SCREEN_WIDTH);
        mousePosition = new MousePosition(frame); // Pass the JFrame reference
        lazerGun = new lazer();
        newEnemy();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(87, 155, 55));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        if(running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            if(lazerFire) {
                g.setColor(Color.RED);
                g.drawLine((int) (player.getX()+(0.5*UNIT_SIZE)), (int) (player.getY()+(0.5*UNIT_SIZE)), (int) (allEnemies[CloseE].getX()+(0.5*UNIT_SIZE)), (int) (allEnemies[CloseE].getY()+(0.5*UNIT_SIZE)));
            }
            // Draw apple
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            //draw enemy
            for (int i = 0; i < enemyNumbers; i++) {
                g.setColor(Color.CYAN);
                g.fillRect(allEnemies[i].getX(),allEnemies[i].getY(),UNIT_SIZE,UNIT_SIZE);
            }

            //draw player
            g.setColor(Color.BLUE);
            g.fillOval(player.getX(), player.getY(), UNIT_SIZE, UNIT_SIZE);

            // Draw score
            g.setColor(Color.RED);
            g.setFont(new Font("Times New Roman", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+ applesEaten, (SCREEN_WIDTH-metrics.stringWidth("Score: "+ applesEaten))/2,g.getFont().getSize());

        } else {
            gameOver.gameOver(g,SCREEN_WIDTH,SCREEN_HEIGHT,applesEaten);
        }
    }

    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
    }

    public void newEnemy(){
        allEnemies[enemyNumbers] = new Enemy(SCREEN_WIDTH,SCREEN_HEIGHT,UNIT_SIZE);
        enemyNumbers++;
    }


    public void checkApple(){
        if (player.getX() < appleX+ UNIT_SIZE && player.getX() > appleX - UNIT_SIZE  && player.getY() < appleY + UNIT_SIZE && player.getY() > appleY - UNIT_SIZE) {
            applesEaten++;
            player.setSpeed(player.getSpeed() + 1);
            newApple();
        }

    }

    public void checkCollisions(){
        //map border
        if (player.getX() < 0 || player.getX() > (SCREEN_WIDTH - UNIT_SIZE) ||
                player.getY() < 0 || player.getY() > (SCREEN_HEIGHT - UNIT_SIZE)) {
            running = false;
        }

        for (int i = 0; i < enemyNumbers; i++) {
            if(player.getX()+(UNIT_SIZE-35)+(UNIT_SIZE/2)>allEnemies[i].getX() && player.getX()-(UNIT_SIZE-35)-(UNIT_SIZE/2)<allEnemies[i].getX() &&
                    player.getY()+(UNIT_SIZE-35)+(UNIT_SIZE/2)>allEnemies[i].getY() && player.getY()-(UNIT_SIZE-35)-(UNIT_SIZE/2)<allEnemies[i].getY()){
                player.setHealth(player.getHealth()-allEnemies[i].getDamage());
                if(player.getHealth() <= 0){
                    running = false;
                }


            }

        }



    }
    public int closestEnemy(){
        int closestE = 0;
        double closestDistance = Double.MAX_VALUE;

        for (int i = 0; i < enemyNumbers ; i++) {
            int dx = player.getX() - allEnemies[i].getX();
            int dy = player.getY() - allEnemies[i].getY();

            double distance = Math.sqrt(dx*dx + dy*dy);
            if(distance < closestDistance){
                closestDistance = distance;
                closestE = i;
            }
        }
        return closestE;
    }

    public void attack(){
        lazerFire = lazerGun.attack(lazerFire,allEnemies,enemyNumbers,player);
        if(lazerFire){
            boolean enemyDead = allEnemies[CloseE].takeDamage(player.getDamage());
            if(enemyDead) {
                deadEnemy[0] = CloseE;
                deadEnemyN++;
            }
        }
    }

    public void dead(){
        for (int i = 0; i < deadEnemyN; i++) {
            int deadIndex = deadEnemy[i];

            // Shift elements to the left, starting from the dead enemy index
            for (int j = deadIndex; j < enemyNumbers - 1; j++) {
                allEnemies[j] = allEnemies[j + 1];  // Shift elements left
            }
            enemyNumbers--;  // Reduce the number of enemies
            deadEnemyN = 0;
            deadEnemy = null;
            deadEnemy = new int[20];
        }
    }

    public void spawn(){
        spawnTick++;
        if (spawnTick > 15 && enemyNumbers < 40){
            newEnemy();
            spawnTick = 0;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            dead();
            player.movement(mousePosition,UNIT_SIZE);
            for (int i = 0; i < enemyNumbers; i++) {
                allEnemies[i].movementE(player,UNIT_SIZE);
            }
            checkApple();
            checkCollisions();
            repaint();
            attack();
            spawn();

            CloseE = closestEnemy();
        }else{
            repaint();
        }
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                direction = 'B';
            }
        }
    }
}
