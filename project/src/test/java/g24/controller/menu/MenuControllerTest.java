package g24.controller.menu;

import g24.controller.commands.button.ButtonCommand;
import g24.controller.commands.user.COMMAND;
import g24.model.menu.ButtonModel;
import g24.model.menu.MenuModel;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MenuControllerTest {
    @Test
    public void test() {
        ButtonCommand buttonCommand = Mockito.mock(ButtonCommand.class);

        ButtonModel buttonModelMock = Mockito.mock(ButtonModel.class);
        when(buttonModelMock.getCommand()).thenReturn(buttonCommand);

        MenuModel menuModelMock = Mockito.mock(MenuModel.class);
        when(menuModelMock.getSelectedButton()).thenReturn(buttonModelMock);

        MenuController menuController = new MenuController(menuModelMock);

        menuController.processCommand(COMMAND.UP);
        verify(menuModelMock, times(1)).selectPreviousButton();

        menuController.processCommand(COMMAND.DOWN);
        verify(menuModelMock, times(1)).selectNextButton();

        menuController.processCommand(COMMAND.SELECT);
        verify(buttonCommand, times(1)).execute();

        menuController.processCommand(COMMAND.RIGHT);
        menuController.processCommand(COMMAND.LEFT);
        menuController.processCommand(COMMAND.SHOOT);
        menuController.processCommand(COMMAND.NOTHING);
        assertFalse(menuController.hasEnded());

        menuController.processCommand(COMMAND.QUIT);
        assertTrue(menuController.hasEnded());
    }
}
