package g24.model.element.monsters;

import g24.controller.commands.action.Action;
import g24.controller.commands.action.NullAction;
import g24.controller.commands.action.Shoot;
import g24.controller.commands.action.ShootDirections;
import g24.controller.element.movementstrategy.MoveGreedyStrategy;
import g24.controller.element.movementstrategy.MoveStrategy;
import g24.model.element.Isaac;
import g24.model.element.projectile.GUNTYPE;
import g24.model.element.projectile.Gun;
import g24.model.utils.Health;
import g24.model.utils.Symbol;

public class Morty extends GunMonster{
    private static final Symbol enemySymbol = new Symbol("༼☉ɷ⊙༽","#af98a9");
    private static final int power = 5;
    private static final Health health = new Health(20);
    private Isaac isaac;

    public Morty(int x, int y, Isaac isaac) {
        super(x, y,health,power,enemySymbol);
        this.isaac = isaac;
        this.moveStrategy = new MoveGreedyStrategy(isaac);
        action = new Shoot(this.gun,getPositions(),false);
    }

    @Override
    public MoveStrategy createMovementStrategy() {
        return new MoveGreedyStrategy(isaac);
    }

    @Override
    public Action createAction() {return new NullAction(); }

    @Override
    public Gun createGun(int x, int y) {
        return new Gun(x,y, GUNTYPE.BULLET);
    }
}
