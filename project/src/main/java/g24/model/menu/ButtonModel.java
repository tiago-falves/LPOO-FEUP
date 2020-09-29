package g24.model.menu;

import g24.controller.commands.button.ButtonCommand;
import g24.model.Model;
import g24.model.utils.Position;

public class ButtonModel extends Model {
    private String text;
    private String textColor, backgroundColor;
    private Position upperLeft, lowerRight;
    private boolean selected;
    private ButtonCommand command;

    public ButtonModel(String text, String textColor, String backgroundColor, Position upperLeft, Position lowerRight) {
        this.text = text;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
        this.selected = false;
    }

    public String getText() {
        return text;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public Position getUpperLeft() {
        return upperLeft;
    }

    public Position getLowerRight() {
        return lowerRight;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setCommand(ButtonCommand command) {
        this.command = command;
    }

    public ButtonCommand getCommand() {
        return command;
    }
}
