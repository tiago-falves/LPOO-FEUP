package g24.controller.commands.button;

import g24.GameConfig;
import g24.controller.GameController;
import g24.controller.commands.user.MoveDownCommand;
import g24.controller.element.CollisionHandler;
import g24.controller.map.MapGenerator;
import g24.controller.map.RoomController;
import g24.controller.state.PlayState;
import g24.model.element.Element;
import g24.model.map.MapTemplate;
import g24.model.utils.Positions;
import g24.view.StateView;
import g24.view.ViewFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StartCommandTest {
    @Test
    public void test1(){
        GameController game  = mock(GameController.class);
        MapGenerator mapGenerator = mock(MapGenerator.class);
        MapTemplate mapTemplate = mock(MapTemplate.class);
        when(mapGenerator.generateGrid()).thenReturn(mapTemplate);
        ViewFactory viewFactory = mock(ViewFactory.class);
        StateView stateView = mock(StateView.class);
        when(viewFactory.createPlayStateView()).thenReturn(stateView);
        when(game.getViewFactory()).thenReturn(viewFactory);

        StartGameCommand startCommand = new StartGameCommand(game);
        startCommand.execute();

        verify(game, times(1)).changeState(any(PlayState.class));
    }
}

