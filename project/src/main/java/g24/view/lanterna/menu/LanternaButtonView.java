package g24.view.lanterna.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import g24.model.menu.ButtonModel;
import g24.view.View;

import java.awt.*;

public class LanternaButtonView extends View<ButtonModel> {
    private TerminalScreen screen;

    public LanternaButtonView(TerminalScreen screen) {
        this.screen = screen;
    }

    @Override
    public void draw(ButtonModel button) {
        TextGraphics textGraphics = screen.newTextGraphics();

        if(button.isSelected()) {
            textGraphics.setBackgroundColor(TextColor.Factory.fromString(button.getBackgroundColor()));
        } else {
            Color darker = TextColor.Factory.fromString(button.getBackgroundColor()).toColor().darker();
            textGraphics.setBackgroundColor(new TextColor.RGB(darker.getRed(), darker.getGreen(), darker.getBlue()));
        }

        textGraphics.setForegroundColor(TextColor.Factory.fromString(button.getTextColor()));

        for(int i = button.getUpperLeft().getX(); i < button.getLowerRight().getX(); i++) {
            for(int j = button.getUpperLeft().getY(); j < button.getLowerRight().getY(); j++) {
                textGraphics.putString(i, j, " ");
            }
        }

        textGraphics.putString(
                (button.getUpperLeft().getX() + button.getLowerRight().getX() - button.getText().length()) / 2,
                (button.getUpperLeft().getY() + button.getLowerRight().getY()) / 2,
                button.getText()
        );
    }
}
