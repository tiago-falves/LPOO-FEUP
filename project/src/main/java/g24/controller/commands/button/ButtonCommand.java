package g24.controller.commands.button;

import g24.controller.GameController;

public abstract class ButtonCommand {
    GameController game;

    protected ButtonCommand(GameController game) {
        this.game = game;
    }

    public abstract void execute();
}
