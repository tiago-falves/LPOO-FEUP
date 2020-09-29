package g24.controller.menu;

import g24.controller.commands.user.COMMAND;
import g24.controller.state.StateController;
import g24.model.menu.MenuModel;

public class MenuController extends StateController {
    private MenuModel menuModel;
    private boolean isFinished;

    public MenuController(MenuModel menuModel) {
        this.menuModel = menuModel;
        isFinished = false;
    }

    @Override
    public void processCommand(COMMAND commandType) {
        switch (commandType) {
            case UP:
                menuModel.selectPreviousButton();
                break;
            case DOWN:
                menuModel.selectNextButton();
                break;
            case SELECT:
                menuModel.getSelectedButton().getCommand().execute();
                break;
            case QUIT:
                isFinished = true;
                break;
            default:
                break;
        }
    }

    @Override
    public boolean hasEnded() {
        return isFinished;
    }
}
