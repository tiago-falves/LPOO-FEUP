package g24.controller.state;

import g24.controller.commands.user.COMMAND;

public abstract class StateController{

    public abstract void processCommand(COMMAND commandType);

    public abstract boolean hasEnded();
}
