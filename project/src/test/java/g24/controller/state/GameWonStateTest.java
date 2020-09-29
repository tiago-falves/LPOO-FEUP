package g24.controller.state;

import g24.controller.GameController;
import g24.controller.commands.user.COMMAND;
import g24.controller.menu.GameWonController;
import g24.model.menu.GameWonModel;
import g24.view.StateView;
import g24.view.lanterna.LanternaFactory;
import g24.view.lanterna.menu.LanternaGameWonView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class GameWonStateTest {
    private GameController gameControllerMock;
    private LanternaGameWonView gameWonViewMock;
    private GameWonState gameWon;

    @Before
    public void setup() {
        gameWonViewMock = Mockito.mock(LanternaGameWonView.class);

        LanternaFactory viewFactory = Mockito.mock(LanternaFactory.class);
        when(viewFactory.createGameWonStateView()).thenReturn(gameWonViewMock);

        gameControllerMock = Mockito.mock(GameController.class);
        when(gameControllerMock.getViewFactory()).thenReturn(viewFactory);

        gameWon = new GameWonState(gameControllerMock);
    }

    @Test
    public void update() {
        ArgumentCaptor<State> argument = ArgumentCaptor.forClass(State.class);

        when(gameWonViewMock.getCommand()).thenReturn(COMMAND.NOTHING);
        gameWon.update(gameControllerMock);
        verify(gameWonViewMock, times(1)).draw(any(GameWonModel.class));
        verify(gameControllerMock, times(0)).changeState(argument.capture());

        when(gameWonViewMock.getCommand()).thenReturn(COMMAND.QUIT);
        gameWon.update(gameControllerMock);
        verify(gameWonViewMock, times(2)).draw(any(GameWonModel.class));
        verify(gameControllerMock, times(1)).changeState(argument.capture());

        assertTrue(argument.getValue() instanceof MenuState);
    }

    @Test
    public void other() {
        assertTrue(gameWon.getStateController() instanceof GameWonController);
        assertTrue(gameWon.getStateModel() instanceof GameWonModel);
        assertTrue(gameWon.getStateView() instanceof StateView);
    }
}
