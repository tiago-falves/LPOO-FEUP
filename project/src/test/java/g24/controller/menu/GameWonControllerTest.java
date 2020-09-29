package g24.controller.menu;

import g24.controller.commands.user.COMMAND;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameWonControllerTest {
    @Test
    public void test() {
        GameWonController gameWonController = new GameWonController();

        gameWonController.processCommand(COMMAND.UP);
        assertFalse(gameWonController.hasEnded());

        gameWonController.processCommand(COMMAND.DOWN);
        assertFalse(gameWonController.hasEnded());

        gameWonController.processCommand(COMMAND.LEFT);
        assertFalse(gameWonController.hasEnded());

        gameWonController.processCommand(COMMAND.RIGHT);
        assertFalse(gameWonController.hasEnded());

        gameWonController.processCommand(COMMAND.SELECT);
        assertFalse(gameWonController.hasEnded());

        gameWonController.processCommand(COMMAND.SHOOT);
        assertFalse(gameWonController.hasEnded());

        gameWonController.processCommand(COMMAND.NOTHING);
        assertFalse(gameWonController.hasEnded());

        gameWonController.processCommand(COMMAND.QUIT);
        assertTrue(gameWonController.hasEnded());
    }
}
