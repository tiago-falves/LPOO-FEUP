package g24.model.element.objects;

import g24.controller.commands.interaction.DecreaseHealthCommand;
import g24.controller.commands.interaction.IncreaseDamageCommand;
import g24.model.utils.Symbol;

public class Hole extends PowerUp {
    private static final Symbol trapSymbol = new Symbol("▓▓▓","#000000");
    public Hole(int x, int y, int value) {
        super(x, y, trapSymbol, new DecreaseHealthCommand(value));
    }
}
