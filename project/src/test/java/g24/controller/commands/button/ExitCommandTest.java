package g24.controller.commands.button;

import g24.controller.GameController;
import g24.controller.map.MapGenerator;
import g24.controller.state.PlayState;
import g24.model.map.MapTemplate;
import g24.view.StateView;
import g24.view.ViewFactory;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ExitCommandTest {
    @Test
    public void test1(){
        GameController game  = mock(GameController.class);
        ExitGameCommand exitGameCommand = new ExitGameCommand(game);
        exitGameCommand.execute();
        verify(game, times(1)).shutdown();
    }
}

