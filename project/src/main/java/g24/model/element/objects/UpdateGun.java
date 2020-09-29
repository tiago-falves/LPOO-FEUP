package g24.model.element.objects;

import g24.controller.commands.interaction.UpdateGunCommand;
import g24.model.utils.Symbol;

public class UpdateGun extends PowerUp {
    private static final Symbol powerUpSymbol = new Symbol("╦╤─","#000000");

    public UpdateGun(int x, int y, int value) {
        super(x, y, powerUpSymbol, new UpdateGunCommand());
    }
}
