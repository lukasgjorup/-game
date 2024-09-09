public class axe {
    private int x;
    private int y;
    private int level;
    private int speed;
    private int damage;

    public axe() {
        this.x = 0;
        this.y = 0;
        this.level = 0;
        this.speed = 10;
        this.damage = 1;
    }

    public int getLevel() {
        return level;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void move(int mousex, int mousey) {
         int dx = mousex - this.x;
         int dy = mousey - this.y;
         int distance = (int) Math.sqrt(dx*dx + dy*dy);
         this.x += this.x + distance / this.speed;
         this.y += this.y + distance / this.speed;
    }


}
