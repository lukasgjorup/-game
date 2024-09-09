import java.util.Random;

public class Enemy{

    private Random random;
    private int x;
    private int y;
    private int health;
    private int speed;
    private int damage;

    public Enemy(int SCREEN_WIDTH, int SCREEN_HEIGHT, int UNIT_SIZE) {
        random = new Random();
        int spawnSide = random.nextInt(4);
        switch (spawnSide) {
            case 0: // Top of the screen
                this.x = random.nextInt(SCREEN_WIDTH);  // Random X within screen width
                this.y = -UNIT_SIZE;  // Just above the screen (negative y)
                break;
            case 1: // Bottom of the screen
                this.x = random.nextInt(SCREEN_WIDTH);  // Random X within screen width
                this.y = SCREEN_HEIGHT + UNIT_SIZE;  // Just below the screen
                break;
            case 2: // Left of the screen
                this.x = -UNIT_SIZE;  // Just to the left of the screen (negative x)
                this.y = random.nextInt(SCREEN_HEIGHT);  // Random Y within screen height
                break;
            case 3: // Right of the screen
                this.x = SCREEN_WIDTH + UNIT_SIZE;  // Just to the right of the screen
                this.y = random.nextInt(SCREEN_HEIGHT);  // Random Y within screen height
                break;
        }
        this.health = 10;
        this.speed = random.nextInt(5)+2;
        System.out.println(this.speed);
        this.damage = 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void movementE(Player player, int UNIT_SIZE){

        int dx = player.getX() - this.x;
        int dy = player.getY() - this.y;

        double distance = Math.sqrt(dx*dx + dy*dy);

        double moveX = dx / distance;
        double moveY = dy / distance;

        this.x = this.x + (int)(moveX * this.speed);
        this.y = this.y + (int)(moveY * this.speed);
    }

    public boolean takeDamage(int damage){
        this.health -= damage;
        if(this.health <= 0){
            return true;
        }
        return false;
    }


}