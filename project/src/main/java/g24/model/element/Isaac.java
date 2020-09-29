package g24.model.element;

import g24.model.element.projectile.Gun;
import g24.model.element.projectile.Projectile;
import g24.model.utils.Health;
import g24.model.utils.Symbol;

import java.util.List;

public class Isaac extends Element {
    private Health health;
    private static final Symbol enemySymbol = new Symbol("( •_•)","#d2afab");



    private Gun gun;

    public Isaac(int x, int y, Health health,Gun gun) {
        super(x, y, enemySymbol);
        this.health = health;
        this.gun = gun;
    }

    public Health getHealth() {
        return health;
    }
    public void setHealth(Health health) {this.health = health;}

    public boolean isDead(){
        return this.health.isZero();
    }

    public Gun getGun(){return gun;}
    public void setGun(Gun gun) {this.gun = gun;}

}
