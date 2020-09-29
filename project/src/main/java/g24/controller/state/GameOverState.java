package g24.controller.state;

import g24.controller.GameController;
import g24.controller.menu.GameOverController;
import g24.model.menu.GameOverModel;
import g24.view.StateView;

public class GameOverState extends State<GameOverModel> {
    private GameOverModel gameOverModel;
    private GameOverController gameOverController;
    private StateView<GameOverModel> gameOverView;

    public GameOverState(GameController game) {
        this.gameOverModel = new GameOverModel("What a loser...");
        this.gameOverController = new GameOverController();
        this.gameOverView = game.getViewFactory().createGameOverStateView();
    }

    @Override
    public void update(GameController game) {
        gameOverView.draw(gameOverModel);
        gameOverController.processCommand(gameOverView.getCommand());
        if(gameOverController.hasEnded()) game.changeState(new MenuState(game));
    }

    @Override
    public GameOverModel getStateModel() {
        return gameOverModel;
    }

    @Override
    public StateView<GameOverModel> getStateView() {
        return gameOverView;
    }

    @Override
    public StateController getStateController() {
        return gameOverController;
    }
}
