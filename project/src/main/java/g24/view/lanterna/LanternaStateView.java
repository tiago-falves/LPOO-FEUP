package g24.view.lanterna;

import com.googlecode.lanterna.screen.TerminalScreen;
import g24.controller.commands.user.COMMAND;
import g24.model.Model;
import g24.view.StateView;

import java.io.IOException;

public abstract class LanternaStateView<M extends Model> extends StateView<M> {
    protected final TerminalScreen screen;
    protected final LanternaAction action;

    protected LanternaStateView(TerminalScreen screen, LanternaAction action) {
        this.action = action;
        this.screen = screen;
    }

    @Override
    public COMMAND getCommand() {
        return action.keyboardParser();
    }

    @Override
    public void exit() {
        try {
            this.screen.close();
        } catch (IOException ignore) {}
    }
}
