package g24.view.lanterna;

import com.googlecode.lanterna.screen.TerminalScreen;
import g24.model.map.DungeonModel;
import g24.model.menu.GameOverModel;
import g24.model.menu.GameWonModel;
import g24.model.menu.MenuModel;
import g24.view.StateView;
import g24.view.ViewFactory;
import g24.view.lanterna.game.LanternaHUDView;
import g24.view.lanterna.game.LanternaPlayView;
import g24.view.lanterna.menu.LanternaButtonView;
import g24.view.lanterna.menu.LanternaGameOverView;
import g24.view.lanterna.menu.LanternaGameWonView;
import g24.view.lanterna.menu.LanternaMenuView;

public class LanternaFactory implements ViewFactory {
    private TerminalScreen screen;

    public LanternaFactory(TerminalScreen screen) throws Exception {
        this.screen = screen;
        this.screen.setCursorPosition(null);
        this.screen.startScreen();
        this.screen.doResizeIfNecessary();
    }

    @Override
    public StateView<MenuModel> createMenuStateView() {
        return new LanternaMenuView(screen, new LanternaAction(screen), new LanternaButtonView(screen));
    }

    @Override
    public StateView<DungeonModel> createPlayStateView() {
        LanternaHUDView hudView = new LanternaHUDView(screen);
        return new LanternaPlayView(screen, new LanternaAction(screen), hudView);
    }

    @Override
    public StateView<GameWonModel> createGameWonStateView() {
        return new LanternaGameWonView(screen, new LanternaAction(screen));
    }

    @Override
    public StateView<GameOverModel> createGameOverStateView() {
        return new LanternaGameOverView(screen, new LanternaAction(screen));
    }
}
