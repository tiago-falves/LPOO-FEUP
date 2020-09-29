package g24.controller.state;

import g24.controller.GameController;
import g24.controller.commands.user.COMMAND;
import g24.controller.menu.GameOverController;
import g24.model.menu.GameOverModel;
import g24.view.StateView;
import g24.view.lanterna.LanternaFactory;
import g24.view.lanterna.menu.LanternaGameOverView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GameOverStateTest {
    private GameController gameControllerMock;
    private LanternaGameOverView gameOverViewMock;
    private GameOverState gameOver;

    @Before
    public void setup() {
        gameOverViewMock = Mockito.mock(LanternaGameOverView.class);

        LanternaFactory viewFactory = Mockito.mock(LanternaFactory.class);
        when(viewFactory.createGameOverStateView()).thenReturn(gameOverViewMock);

        gameControllerMock = Mockito.mock(GameController.class);
        when(gameControllerMock.getViewFactory()).thenReturn(viewFactory);

        gameOver = new GameOverState(gameControllerMock);
    }

    @Test
    public void update() {
        ArgumentCaptor<State> argument = ArgumentCaptor.forClass(State.class);

        when(gameOverViewMock.getCommand()).thenReturn(COMMAND.NOTHING);
        gameOver.update(gameControllerMock);
        verify(gameOverViewMock, times(1)).draw(any(GameOverModel.class));
        verify(gameControllerMock, times(0)).changeState(argument.capture());

        when(gameOverViewMock.getCommand()).thenReturn(COMMAND.QUIT);
        gameOver.update(gameControllerMock);
        verify(gameOverViewMock, times(2)).draw(any(GameOverModel.class));
        verify(gameControllerMock, times(1)).changeState(argument.capture());

        assertTrue(argument.getValue() instanceof MenuState);
    }

    @Test
    public void other() {
        assertTrue(gameOver.getStateController() instanceof GameOverController);
        assertTrue(gameOver.getStateModel() instanceof GameOverModel);
        assertTrue(gameOver.getStateView() instanceof StateView);
    }
}
