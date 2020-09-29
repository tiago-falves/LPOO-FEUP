package g24.controller.hud;

import g24.controller.element.HealthController;
import g24.controller.map.MapController;
import g24.controller.map.ScoreController;
import g24.controller.map.TimeController;
import g24.controller.observer.IsaacHealthObserver;
import g24.controller.observer.ScoreObserver;
import g24.controller.observer.TimeObserver;
import g24.model.hud.HUDModel;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class HUDControllerTest {

    @Test
    public void test() {
        HUDModel hudModelMock = Mockito.mock(HUDModel.class);
        TimeController timeControllerMock = Mockito.mock(TimeController.class);
        HealthController healthControllerMock = Mockito.mock(HealthController.class);
        ScoreController scoreControllerMock = Mockito.mock(ScoreController.class);

        MapController mapControllerMock = Mockito.mock(MapController.class);
        when(mapControllerMock.getTimeController()).thenReturn(timeControllerMock);
        when(mapControllerMock.getHealthController()).thenReturn(healthControllerMock);
        when(mapControllerMock.getScoreController()).thenReturn(scoreControllerMock);

        HUDController hudController = new HUDController(mapControllerMock, hudModelMock);

        assertTrue(hudController.getHudModel() instanceof HUDModel);

        verify(timeControllerMock).addObserver(any(TimeObserver.class));
        verify(healthControllerMock).addObserver(any(IsaacHealthObserver.class));
        verify(scoreControllerMock).addObserver(any(ScoreObserver.class));
    }
}
