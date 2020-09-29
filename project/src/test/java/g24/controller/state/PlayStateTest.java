package g24.controller.state;

import g24.controller.GameController;
import g24.controller.element.HealthController;
import g24.controller.map.MapController;
import g24.controller.map.ScoreController;
import g24.controller.map.TimeController;
import g24.model.hud.HUDModel;
import g24.model.map.DungeonModel;
import g24.view.StateView;
import g24.view.lanterna.LanternaFactory;
import g24.view.lanterna.game.LanternaPlayView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class PlayStateTest {
    private GameController gameControllerMock;
    private MapController mapControllerMock;
    private LanternaPlayView playViewMock;
    private PlayState playState;

    @Before
    public void setup() {
        HUDModel hudModelMock = Mockito.mock(HUDModel.class);
        TimeController timeControllerMock = Mockito.mock(TimeController.class);
        HealthController healthControllerMock = Mockito.mock(HealthController.class);
        ScoreController scoreControllerMock = Mockito.mock(ScoreController.class);

        mapControllerMock = Mockito.mock(MapController.class);
        when(mapControllerMock.getTimeController()).thenReturn(timeControllerMock);
        when(mapControllerMock.getHealthController()).thenReturn(healthControllerMock);
        when(mapControllerMock.getScoreController()).thenReturn(scoreControllerMock);

        playViewMock = Mockito.mock(LanternaPlayView.class);

        LanternaFactory viewFactory = Mockito.mock(LanternaFactory.class);
        when(viewFactory.createPlayStateView()).thenReturn(playViewMock);

        gameControllerMock = Mockito.mock(GameController.class);
        when(gameControllerMock.getViewFactory()).thenReturn(viewFactory);

        playState = new PlayState(gameControllerMock, mapControllerMock, hudModelMock);
    }

    @Test
    public void updateLost() {
        ArgumentCaptor<State> argumentCaptor = ArgumentCaptor.forClass(State.class);

        when(mapControllerMock.hasEnded()).thenReturn(false);
        playState.update(gameControllerMock);
        verify(gameControllerMock, times(0)).changeState(any(State.class));

        when(mapControllerMock.hasEnded()).thenReturn(true);
        when(mapControllerMock.hasWon()).thenReturn(false);
        playState.update(gameControllerMock);
        verify(gameControllerMock, times(1)).changeState(argumentCaptor.capture());
        assertTrue(argumentCaptor.getValue() instanceof GameOverState);
    }

    @Test
    public void updateWon() {
        ArgumentCaptor<State> argumentCaptor = ArgumentCaptor.forClass(State.class);

        when(mapControllerMock.hasEnded()).thenReturn(false);
        playState.update(gameControllerMock);
        verify(gameControllerMock, times(0)).changeState(any(State.class));

        when(mapControllerMock.hasEnded()).thenReturn(true);
        when(mapControllerMock.hasWon()).thenReturn(true);
        playState.update(gameControllerMock);
        verify(gameControllerMock, times(1)).changeState(argumentCaptor.capture());
        assertTrue(argumentCaptor.getValue() instanceof GameWonState);
    }

    @Test
    public void other() {
        assertTrue(playState.getStateModel() instanceof DungeonModel);
        assertTrue(playState.getStateController() instanceof MapController);
        assertTrue(playState.getStateView() instanceof StateView);
    }
}
