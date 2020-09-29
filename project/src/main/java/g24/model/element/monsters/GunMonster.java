package g24.model.element.monsters;

import g24.controller.commands.action.Action;
import g24.controller.commands.action.Shoot;
import g24.controller.commands.action.ShootDirections;
import g24.controller.element.movementstrategy.MoveStrategy;
import g24.model.element.projectile.Gun;
import g24.model.utils.Health;
import g24.model.utils.Symbol;

public abstract class GunMonster extends Monster {
    protected Gun gun;

    public GunMonster(int x, int y, Health health, int power, Symbol symbol) {
        super(x, y, health, power, symbol);
        gun = createGun(x,y);
    }
    public abstract Gun createGun(int x,int y);

    public Gun getGun() {return gun;}

    public void setGun(Gun gun) {this.gun = gun;}
}
