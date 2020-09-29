package g24.model.element.monsters;

import g24.controller.commands.action.Action;
import g24.controller.element.movementstrategy.MoveStrategy;
import g24.model.element.Element;
import g24.model.utils.Health;
import g24.model.utils.Symbol;

public abstract class Monster extends Element {

    protected MoveStrategy moveStrategy;
    protected Action action;
    private int power;
    private Health health;

    public Monster(int x, int y,Health health,int power, Symbol symbol) {
        super(x, y,symbol);
        this.moveStrategy = createMovementStrategy();
        this.action = createAction();
        this.power = power;
        this.health = health;
    }
    public abstract MoveStrategy createMovementStrategy();
    public abstract Action createAction();

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public int getPower(){ return this.power;}

    public Health getHealth() {
        return health;
    }

    public Action getAction() {return action;}

    public void setAction(Action action) {this.action = action;}



}
