package g24.controller.commands.button;

import g24.controller.GameController;

public class ExitGameCommand extends ButtonCommand {

    public ExitGameCommand(GameController game) {
        super(game);
    }

    @Override
    public void execute() {
        game.shutdown();
    }
}
