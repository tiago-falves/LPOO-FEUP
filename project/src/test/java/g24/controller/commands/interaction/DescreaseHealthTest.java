package g24.controller.commands.interaction;

import g24.controller.map.RoomController;
import g24.model.element.Isaac;
import g24.model.element.objects.PowerUp;
import g24.model.utils.Health;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class DescreaseHealthTest {
    @Test
    public void test1(){
        Health health = mock(Health.class);
        RoomController roomController = mock(RoomController.class);
        Isaac isaac = mock(Isaac.class);
        when(health.isZero()).thenReturn(true);
        when(isaac.getHealth()).thenReturn(health);
        when(roomController.getIsaac()).thenReturn(isaac);
        PowerUp powerUp = mock(PowerUp.class);
        DecreaseHealthCommand decreaseHealthCommand = new DecreaseHealthCommand(5);
        decreaseHealthCommand.interact(powerUp,roomController);
        verify(roomController, times(1)).finishRoom(false);
    }
}
