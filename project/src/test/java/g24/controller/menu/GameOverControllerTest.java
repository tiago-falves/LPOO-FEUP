package g24.controller.menu;

import g24.controller.commands.user.COMMAND;
import org.junit.Test;

import static org.junit.Assert.*;


public class GameOverControllerTest {

    @Test
    public void test() {
        GameOverController gameOverController = new GameOverController();

        gameOverController.processCommand(COMMAND.UP);
        assertFalse(gameOverController.hasEnded());

        gameOverController.processCommand(COMMAND.DOWN);
        assertFalse(gameOverController.hasEnded());

        gameOverController.processCommand(COMMAND.LEFT);
        assertFalse(gameOverController.hasEnded());

        gameOverController.processCommand(COMMAND.RIGHT);
        assertFalse(gameOverController.hasEnded());

        gameOverController.processCommand(COMMAND.SELECT);
        assertFalse(gameOverController.hasEnded());

        gameOverController.processCommand(COMMAND.SHOOT);
        assertFalse(gameOverController.hasEnded());

        gameOverController.processCommand(COMMAND.NOTHING);
        assertFalse(gameOverController.hasEnded());

        gameOverController.processCommand(COMMAND.QUIT);
        assertTrue(gameOverController.hasEnded());
    }
}
