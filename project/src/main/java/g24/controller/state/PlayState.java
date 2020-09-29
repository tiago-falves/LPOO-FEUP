package g24.controller.state;

import g24.controller.GameController;
import g24.controller.hud.HUDController;
import g24.controller.map.MapController;
import g24.model.hud.HUDModel;
import g24.model.map.DungeonModel;
import g24.model.map.MapTemplate;
import g24.model.map.RoomModel;
import g24.view.StateView;
import g24.view.ViewFactory;

public class PlayState extends State<DungeonModel> {
    private StateView<DungeonModel> stateView;
    private MapController mapController;
    private HUDController hudController;

    public PlayState(GameController game, MapController mapController, HUDModel hudModel) {
        this.mapController = mapController;
        this.hudController = new HUDController(mapController, hudModel);
        this.stateView = game.getViewFactory().createPlayStateView();
    }

    @Override
    public void update(GameController game) {
        mapController.nextIteration(stateView.getCommand());

        stateView.draw(new DungeonModel(mapController.getCurrentRoom(), hudController.getHudModel()));

        if(mapController.hasEnded()) {
            if(mapController.hasWon())
                game.changeState(new GameWonState(game));
            else
                game.changeState(new GameOverState(game));
        }

    }

    @Override
    public DungeonModel getStateModel() {
        return new DungeonModel(mapController.getCurrentRoom(), hudController.getHudModel());
    }

    @Override
    public StateView<DungeonModel> getStateView() {
        return stateView;
    }

    @Override
    public StateController getStateController() {
        return mapController;
    }
}
