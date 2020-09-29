package g24.model.element.objects;

import g24.controller.commands.interaction.IncreaseDamageCommand;
import g24.model.utils.Symbol;

public class IncreaseDamage extends PowerUp {
    private static final Symbol powerUpSymbol = new Symbol("❚══❚","#000000");

    public IncreaseDamage(int x, int y,int value) {
        super(x, y, powerUpSymbol, new IncreaseDamageCommand(value));
    }
}
