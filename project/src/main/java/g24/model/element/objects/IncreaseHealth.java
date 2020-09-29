package g24.model.element.objects;

import g24.controller.commands.interaction.IncreaseHealthCommand;
import g24.model.utils.Symbol;

public class IncreaseHealth extends PowerUp {
    private static final Symbol powerUpSymbol = new Symbol("❤","#FF0000");

    public IncreaseHealth(int x, int y, int value) {
        super(x, y, powerUpSymbol, new IncreaseHealthCommand(value));
    }
}
