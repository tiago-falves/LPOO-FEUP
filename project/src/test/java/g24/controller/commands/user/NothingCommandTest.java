package g24.controller.commands.user;

import g24.controller.commands.user.NothingCommand;
import org.junit.Test;

public class NothingCommandTest {
    @Test
    public void execute() {
        NothingCommand nothingCommand = new NothingCommand();
        nothingCommand.execute();
    }
}
