package g24.controller.commands.action;

import g24.controller.element.movementstrategy.DIRECTION;

public abstract class Action {
    public abstract void executeAction(DIRECTION direction);
}
