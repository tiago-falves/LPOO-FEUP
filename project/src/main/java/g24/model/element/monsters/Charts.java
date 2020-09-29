package g24.model.element.monsters;

import g24.controller.commands.action.Action;
import g24.controller.commands.action.NullAction;
import g24.controller.element.movementstrategy.MoveRandomStrategy;
import g24.controller.element.movementstrategy.MoveStrategy;
import g24.model.utils.Health;
import g24.model.utils.Symbol;

public class Charts extends Monster{
    private static final Symbol enemySymbol = new Symbol("(◕_◕)","#af98a9");
    private static final int power = 2;
    private static final Health health = new Health(10);


    public Charts(int x, int y) {
        super(x, y,health,power,enemySymbol);
    }

    @Override
    public MoveStrategy createMovementStrategy() {
        return new MoveRandomStrategy();
    }

    @Override
    public Action createAction() {return new NullAction(); }
}
