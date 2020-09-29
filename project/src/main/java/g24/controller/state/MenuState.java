package g24.controller.state;

import g24.controller.GameController;
import g24.controller.commands.button.ExitGameCommand;
import g24.controller.commands.button.StartGameCommand;
import g24.controller.menu.MenuController;
import g24.model.menu.MenuModel;
import g24.view.StateView;

public class MenuState extends State<MenuModel>{
    private MenuModel menuModel;
    private MenuController menuController;
    private StateView<MenuModel> menuView;

    public MenuState(GameController game) {
        this.menuModel = new MenuModel();
        this.menuModel.getButtonModels().get(0).setCommand(new StartGameCommand(game));
        this.menuModel.getButtonModels().get(1).setCommand(new ExitGameCommand(game));
        this.menuController = new MenuController(menuModel);
        this.menuView = game.getViewFactory().createMenuStateView();
    }

    @Override
    public void update(GameController game) {
        menuController.processCommand(menuView.getCommand());
        menuView.draw(menuModel);
    }

    @Override
    public MenuModel getStateModel() {
        return menuModel;
    }

    @Override
    public StateView<MenuModel> getStateView() {
        return menuView;
    }

    @Override
    public StateController getStateController() {
        return menuController;
    }
}
