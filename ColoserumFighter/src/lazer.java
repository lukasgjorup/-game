
public class lazer {

    private int x;
    private int y;
    private int level;
    private int shoot;

    public lazer(){

        this.x = 0;
        this.y = 0;
        this.level = 1;
        this.shoot = 0;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getLevel() {
        return level;
    }
    public int getShoot() {
        return shoot;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setShoot(int shoot){
        this.shoot = shoot;
    }

    public boolean attack(boolean lazerFire,Enemy[] allEnemies,int enemyNumbers,Player player){
        if(level != 0){
            if(shoot< 10) {
                shoot = shoot + level;
                return lazerFire = false;
            }else{
                //fire gun!
               shoot = 0;
                lazerFire = true;
            }
        }
        return lazerFire;
    }

}
