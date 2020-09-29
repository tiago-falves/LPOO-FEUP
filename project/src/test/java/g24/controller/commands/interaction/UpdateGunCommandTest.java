package g24.controller.commands.interaction;

import g24.controller.element.HealthController;
import g24.controller.map.RoomController;
import g24.model.element.Isaac;
import g24.model.element.objects.PowerUp;
import g24.model.map.RoomModel;
import g24.model.utils.Health;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UpdateGunCommandTest {
    @Test
    public void test1(){
        Health health = mock(Health.class);
        RoomController roomController = mock(RoomController.class);
        PowerUp powerUp1 = mock(PowerUp.class);
        PowerUp powerUp2 = mock(PowerUp.class);
        List<PowerUp> powerUps = new ArrayList<>();
        powerUps.add(powerUp1);
        powerUps.add(powerUp2);

        RoomModel roomModel = mock(RoomModel.class);
        when(roomModel.getPowerUps()).thenReturn(powerUps);
        when(roomController.getRoomModel()).thenReturn(roomModel);

        UpdateGunCommand updateGun = new UpdateGunCommand();
        updateGun.interact(powerUp1,roomController);

        verify(roomController, times(1)).removeElement(powerUps,powerUp1);
        verify(roomController,times(1)).nextGun();
    }
}
