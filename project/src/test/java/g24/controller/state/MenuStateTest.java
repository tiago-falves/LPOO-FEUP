package g24.controller.state;

import g24.controller.GameController;
import g24.controller.commands.user.COMMAND;
import g24.controller.menu.MenuController;
import g24.model.menu.MenuModel;
import g24.view.StateView;
import g24.view.lanterna.LanternaFactory;
import g24.view.lanterna.menu.LanternaMenuView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MenuStateTest {
    private GameController gameControllerMock;
    private LanternaMenuView menuViewMock;
    private MenuState menuState;

    @Before
    public void setup() {
        menuViewMock = Mockito.mock(LanternaMenuView.class);

        LanternaFactory viewFactory = Mockito.mock(LanternaFactory.class);
        when(viewFactory.createMenuStateView()).thenReturn(menuViewMock);

        gameControllerMock = Mockito.mock(GameController.class);
        when(gameControllerMock.getViewFactory()).thenReturn(viewFactory);

        menuState = new MenuState(gameControllerMock);
    }

    @Test
    public void update() {
        when(menuViewMock.getCommand()).thenReturn(COMMAND.NOTHING);

        menuState.update(gameControllerMock);

        verify(menuViewMock, times(1)).getCommand();
        verify(menuViewMock, times(1)).draw(any(MenuModel.class));
    }

    @Test
    public void other() {
        assertTrue(menuState.getStateController() instanceof MenuController);
        assertTrue(menuState.getStateModel() instanceof MenuModel);
        assertTrue(menuState.getStateView() instanceof StateView);
    }
}
