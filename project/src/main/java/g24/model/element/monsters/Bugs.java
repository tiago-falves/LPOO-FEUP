package g24.model.element.monsters;

import g24.controller.commands.action.Action;
import g24.controller.commands.action.NullAction;
import g24.controller.element.movementstrategy.MoveQuickRandomStrategy;
import g24.controller.element.movementstrategy.MoveStrategy;
import g24.model.utils.Health;
import g24.model.utils.Symbol;

public class Bugs extends Monster{
    private static final Symbol bugSymbol = new Symbol("(°□°)","#e2ceb8");
    private static final int power = 1;
    private static final Health health = new Health(1);

    public Bugs(int x, int y) {
        super(x, y,health,power, bugSymbol);
    }

    @Override
    public MoveStrategy createMovementStrategy() {
        return new MoveQuickRandomStrategy();
    }

    @Override
    public Action createAction() {return new NullAction(); }


}
