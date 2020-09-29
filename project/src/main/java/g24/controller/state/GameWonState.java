package g24.controller.state;

import g24.controller.GameController;
import g24.controller.menu.GameWonController;
import g24.model.menu.GameWonModel;
import g24.view.StateView;

public class GameWonState extends State<GameWonModel> {
    private GameWonModel gameWonModel;
    private GameWonController gameWonController;
    private StateView<GameWonModel> gameWonView;

    public GameWonState(GameController game) {
        this.gameWonModel = new GameWonModel("Congrats! You've barely defeated the final boss...");
        this.gameWonController = new GameWonController();
        this.gameWonView = game.getViewFactory().createGameWonStateView();
    }

    @Override
    public void update(GameController game) {
        gameWonView.draw(gameWonModel);
        gameWonController.processCommand(gameWonView.getCommand());
        if(gameWonController.hasEnded()) game.changeState(new MenuState(game));
    }

    @Override
    public GameWonModel getStateModel() {
        return gameWonModel;
    }

    @Override
    public StateView<GameWonModel> getStateView() {
        return gameWonView;
    }

    @Override
    public StateController getStateController() {
        return gameWonController;
    }
}
