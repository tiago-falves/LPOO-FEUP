package g24.controller.menu;

import g24.controller.commands.user.COMMAND;
import g24.controller.state.StateController;

public class GameOverController extends StateController {
    private boolean isFinished = false;

    @Override
    public void processCommand(COMMAND commandType) {
        if(commandType == COMMAND.QUIT) isFinished = true;
    }

    @Override
    public boolean hasEnded() {
        return isFinished;
    }
}
