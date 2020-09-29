package g24.model.element.monsters;

import g24.controller.commands.action.Action;
import g24.controller.commands.action.NullAction;
import g24.controller.element.movementstrategy.MoveGreedyStrategy;
import g24.controller.element.movementstrategy.MoveStrategy;
import g24.model.element.Isaac;
import g24.model.utils.Health;
import g24.model.utils.Symbol;

public class Pervert extends Monster{
    private static final Symbol enemySymbol = new Symbol("⌐(ಠ۾ಠ)¬","#af98a9");
    private static final int power = 3;
    private static final Health health = new Health(5);
    private Isaac isaac;

    public Pervert(int x, int y, Isaac isaac) {
        super(x, y,health,power,enemySymbol);
        this.isaac = isaac;
        this.moveStrategy = new MoveGreedyStrategy(isaac);
    }

    @Override
    public MoveStrategy createMovementStrategy() {
        return new MoveGreedyStrategy(isaac);
    }
    @Override
    public Action createAction() {return new NullAction(); }
}
