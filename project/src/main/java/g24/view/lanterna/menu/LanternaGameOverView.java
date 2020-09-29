package g24.view.lanterna.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import g24.GameConfig;
import g24.model.menu.GameOverModel;
import g24.view.lanterna.LanternaAction;
import g24.view.lanterna.LanternaStateView;

import java.io.IOException;

public class LanternaGameOverView extends LanternaStateView<GameOverModel> {
    private final static String title = "Game Over";
    private final static String leave = "Press [Esc] to exit";

    public LanternaGameOverView(TerminalScreen screen, LanternaAction action) {
        super(screen, action);
    }

    @Override
    public void draw(GameOverModel model) {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#DDDDDD"));
        textGraphics.putString(
                (GameConfig.ROOM_WIDTH - title.length()) / 2,
                1,
                title
        );

        String reason = model.getReason();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF1100"));
        textGraphics.putString(
                (GameConfig.ROOM_WIDTH - reason.length()) / 2,
                5,
                reason
        );

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#DDDDDD"));
        textGraphics.putString(
                (GameConfig.ROOM_WIDTH - leave.length()) / 2,
                10,
                leave
        );

        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
