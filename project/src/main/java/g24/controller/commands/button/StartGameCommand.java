package g24.controller.commands.button;

import g24.controller.GameController;
import g24.controller.element.CollisionHandler;
import g24.controller.element.HealthController;
import g24.controller.map.*;
import g24.controller.state.PlayState;
import g24.model.hud.HUDModel;
import g24.model.map.MapTemplate;
import g24.model.map.NullRoomModel;

public class StartGameCommand extends ButtonCommand {

    public StartGameCommand(GameController game) {
        super(game);
    }

    @Override
    public void execute() {
        RoomController roomController = new RoomController(
                new NullRoomModel(),
                new HealthController(),
                new ScoreController(),
                new CollisionHandler());

        game.changeState(new PlayState(
                        game,
                        new MapController(
                                new MapGenerator().generateGrid(),
                                roomController,
                                new TimeController(),
                                new RoomFactory()
                        ),
                        new HUDModel(100, 6)
        ));
    }
}
