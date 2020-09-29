package g24.view.lanterna.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import g24.GameConfig;
import g24.model.menu.MenuModel;
import g24.view.lanterna.LanternaAction;
import g24.view.lanterna.LanternaStateView;

import java.io.IOException;

public class LanternaMenuView extends LanternaStateView<MenuModel> {
    LanternaButtonView lanternaButtonView;

    public LanternaMenuView(TerminalScreen screen, LanternaAction action, LanternaButtonView lanternaButtonView) {
        super(screen, action);
        this.lanternaButtonView = lanternaButtonView;
    }

    public void draw(MenuModel menu) {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#DDDDDD"));
        textGraphics.putString((GameConfig.ROOM_WIDTH - GameConfig.GAME.length()) / 2, 1, GameConfig.GAME);
        for(int i = 0; i < menu.getNumberOfButtons(); i++) {
            lanternaButtonView.draw(menu.getButtonModels().get(i));
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
