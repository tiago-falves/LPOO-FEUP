package g24.model.element.monsters;

import g24.controller.commands.action.Action;
import g24.controller.commands.action.Shoot;
import g24.controller.commands.action.ShootDirections;
import g24.controller.element.movementstrategy.MoveGreedyStrategy;
import g24.controller.element.movementstrategy.MoveQuickGreedyStrategy;
import g24.controller.element.movementstrategy.MoveStrategy;
import g24.model.element.*;
import g24.model.element.projectile.GUNTYPE;
import g24.model.element.projectile.Gun;
import g24.model.utils.Health;
import g24.model.utils.Symbol;

public class Boss extends GunMonster {
    private static final Symbol enemySymbol = new Symbol("┌∩┐(◣_◢)┌∩┐","#af98a9");
    private static final int power = 30;
    private static final Health health = new Health(100);
    private Isaac isaac;

    public Boss(int x, int y, Isaac isaac) {
        super(x, y,health,power,enemySymbol);
        this.isaac = isaac;
        this.moveStrategy = new MoveQuickGreedyStrategy(isaac);
        action = new ShootDirections(this.gun,getPositions(),false);

    }

    @Override
    public MoveStrategy createMovementStrategy() {
        return new MoveQuickGreedyStrategy(isaac);
    }
    @Override
    public Action createAction() {return new ShootDirections(this.gun,getPositions(),false); }


    @Override
    public Gun createGun(int x,int y) {
        return new Gun(x,y, GUNTYPE.GRENADE);
    }
}
