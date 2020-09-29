package g24.view.lanterna;

import com.googlecode.lanterna.screen.TerminalScreen;
import g24.view.lanterna.game.LanternaPlayView;
import g24.view.lanterna.menu.LanternaGameOverView;
import g24.view.lanterna.menu.LanternaGameWonView;
import g24.view.lanterna.menu.LanternaMenuView;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LanternFactoryTest {

    private LanternaFactory lanternaFactory;
    private TerminalScreen screenMock;

    @Before
    public void setUp() {

        screenMock = mock(TerminalScreen.class);
        try {
            lanternaFactory = new LanternaFactory(screenMock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void createMenuViewTest() {
        assertTrue(lanternaFactory.createMenuStateView() instanceof LanternaMenuView);
    }

    @Test
    public void createPlayStateTest() {
        assertTrue(lanternaFactory.createPlayStateView() instanceof LanternaPlayView);
    }

    @Test
    public void createGameOverViewTest() {
        assertTrue(lanternaFactory.createGameOverStateView() instanceof LanternaGameOverView);
    }
    @Test
    public void createWinViewTest() {
        assertTrue(lanternaFactory.createGameWonStateView() instanceof LanternaGameWonView);
    }

    @Test
    public void constructorTest() {
        verify(screenMock, times(1)).setCursorPosition(null);
        verify(screenMock, times(1)).doResizeIfNecessary();
        try {
            verify(screenMock, times(1)).startScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
