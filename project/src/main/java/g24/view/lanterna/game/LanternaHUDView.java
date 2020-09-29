package g24.view.lanterna.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import g24.GameConfig;
import g24.model.hud.HUDModel;
import g24.view.View;

public class LanternaHUDView extends View<HUDModel> {

    private static final String backgroundColor = "#382410";
    private static final String mainTextColor = "#FFFFFF";
    private static final String visitedRoomsColor = "#DDDDDD";
    private static final String scoreSymbol = "Score";
    private static final String timeSymbol = "Time";
    private static final String currentHealthColor = "#FF1100";
    private static final String visitedRoomsSymbol = "    Rooms";
    private static final String scoreColor = "#DDDDDD";
    private static final String timeColor = "#DDDDDD";
    private static final String currentHealthSymbol = "❤";
    private static final int hudHeight = 0;
    private TerminalScreen screen;

    public LanternaHUDView(TerminalScreen screen) {
        this.screen = screen;
    }

    @Override
    public void draw(HUDModel hudModel) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));

        String visitedRoomsValue = " " + hudModel.getVisitedRooms() + "/" + hudModel.getMaxRooms();
        String currentHealthValue = " " + String.format("%03d", hudModel.getCurrentHealth()) + "/" + hudModel.getMaxHealth() + "  ";
        String timeValue = " " + String.format("%03d", hudModel.getTime()) + "s";
        String scoreValue = " " + String.format("%03d", hudModel.getScore());

        int position = 0;
        int emptySpaceLength = (GameConfig.ROOM_WIDTH - currentHealthSymbol.length() - currentHealthValue.length()
                - visitedRoomsColor.length() - visitedRoomsValue.length() - timeSymbol.length() + timeValue.length()
                - scoreSymbol.length() - scoreValue.length()) / 4 ;

        String emptySpace = new String(new char[emptySpaceLength]).replace('\0', ' ');

        drawComponent(textGraphics, visitedRoomsSymbol, visitedRoomsColor, position, hudHeight);
        position += visitedRoomsSymbol.length();
        drawComponent(textGraphics, visitedRoomsValue, mainTextColor, position, hudHeight);
        position += visitedRoomsValue.length();

        drawComponent(textGraphics, emptySpace, mainTextColor, position, hudHeight);
        position += emptySpaceLength;

        drawComponent(textGraphics, timeSymbol, timeColor, position, hudHeight);
        position += timeSymbol.length();
        drawComponent(textGraphics, timeValue, mainTextColor, position, hudHeight);
        position += timeValue.length();

        drawComponent(textGraphics, emptySpace, mainTextColor, position, hudHeight);
        position += emptySpaceLength;

        drawComponent(textGraphics, scoreSymbol, scoreColor, position, hudHeight);
        position += scoreSymbol.length();
        drawComponent(textGraphics, scoreValue, mainTextColor, position, hudHeight);
        position += scoreValue.length();

        drawComponent(textGraphics, emptySpace, mainTextColor, position, hudHeight);
        position += emptySpaceLength;

        drawComponent(textGraphics, currentHealthSymbol, currentHealthColor, position, hudHeight);
        position += currentHealthSymbol.length();
        drawComponent(textGraphics, currentHealthValue, mainTextColor, position, hudHeight);
        position += currentHealthValue.length();

        int emptySpaceLeft = GameConfig.ROOM_WIDTH - emptySpaceLength;
        if(emptySpaceLeft > 0) {
            String missingEmptySpace = new String(new char[emptySpaceLeft]).replace('\0', ' ');
            drawComponent(textGraphics, missingEmptySpace, mainTextColor, position, hudHeight);
        }
    }

    private void drawComponent(TextGraphics graphics, String toDraw, String color, int positionX, int positionY) {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(positionX, positionY), toDraw);
    }
}
