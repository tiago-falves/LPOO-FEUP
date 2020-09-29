package g24.controller.commands.interaction;

import g24.controller.map.RoomController;
import g24.model.element.Isaac;
import g24.model.element.objects.IncreaseDamage;
import g24.model.element.objects.PowerUp;
import g24.model.map.RoomModel;
import g24.model.utils.Health;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class IncreaseDamageTest {
    @Test
    public void test1(){
        RoomController roomController = mock(RoomController.class);
        Isaac isaac = mock(Isaac.class);
        when(roomController.getIsaac()).thenReturn(isaac);
        PowerUp powerUp1 = mock(PowerUp.class);
        PowerUp powerUp2 = mock(PowerUp.class);
        RoomModel roomModel = mock(RoomModel.class);
        List<PowerUp> powerUps = new ArrayList<>();
        powerUps.add(powerUp1);
        powerUps.add(powerUp2);

        when(roomModel.getPowerUps()).thenReturn(powerUps);
        when(roomController.getRoomModel()).thenReturn(roomModel);

        IncreaseDamageCommand increaseDamageCommand = new IncreaseDamageCommand(5);
        increaseDamageCommand.interact(powerUp1,roomController);

        verify(roomController, times(1)).removeElement(powerUps,powerUp1);
        verify(roomController, times(1)).increaseBulletsDamage(roomController.getIsaac(),5);
    }
}
