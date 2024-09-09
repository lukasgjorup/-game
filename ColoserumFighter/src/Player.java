public class Player {
    public static int healthBarI;
    private int x;
    private int y;
    private int health;
    private int speed;
    private int damage;
    private Management healthBar;

    public Player(Management healthBar, int screenHeight, int screenWidth) {
        this.x = screenWidth / 2;
        this.y = screenHeight / 2;
        this.health = 100;
        this.speed = 5;
        this.damage = 1;
        healthBarI = this.health;
        this.healthBar = healthBar;
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

    public int getHealth() {
        return health;
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
    public void setHealth(int health) {
        this.health = health;
        healthBarI = this.health;
        healthBar.repaint();
    }

    public void movement(MousePosition mousePosition,int UNIT_SIZE) {
        mousePosition.update();
        int dx = mousePosition.getX() - (this.x + 7 + UNIT_SIZE-(UNIT_SIZE/2));
        int dy = mousePosition.getY() - (this.y + 2 + UNIT_SIZE);

        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 0.5 * UNIT_SIZE) {
            double moveX = dx / distance;
            double moveY = dy / distance;

            this.x = this.x + (int)(moveX * speed);
            this.y = this.y + (int)(moveY * speed);
        }
    }
}