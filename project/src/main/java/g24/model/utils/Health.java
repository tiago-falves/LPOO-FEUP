package g24.model.utils;

public class Health {

    private int health;

    public Health(int health) {
        if(health > 0) this.health = health;
        else health = 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void increase(int amount){
        this.health += amount;
    }

    public void decrease(int amount){
        if (this.health - amount < 0) this.health = 0;
        else this.health -= amount;
    }

    public boolean isZero(){
        if(this.health > 0) return false;
        return true;
    }

}
