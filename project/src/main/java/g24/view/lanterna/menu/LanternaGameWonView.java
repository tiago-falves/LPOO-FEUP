package g24.view.lanterna.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import g24.GameConfig;
import g24.model.menu.GameWonModel;
import g24.view.lanterna.LanternaAction;
import g24.view.lanterna.LanternaStateView;

import java.io.IOException;

public class LanternaGameWonView extends LanternaStateView<GameWonModel> {
    private final static String title = "Game Won";
    private final static String leave = "Press [Esc] to exit";

    public LanternaGameWonView(TerminalScreen screen, LanternaAction action) {
        super(screen, action);
    }


    @Override
    public void draw(GameWonModel model) {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#DDDDDD"));
        textGraphics.putString(
                (GameConfig.ROOM_WIDTH - title.length()) / 2,
                1,
                title
        );

        String reason = model.getReason();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#11FF00"));
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
