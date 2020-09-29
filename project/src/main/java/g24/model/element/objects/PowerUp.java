package g24.model.element.objects;

import g24.controller.commands.interaction.Interaction;
import g24.model.element.Element;
import g24.model.utils.Symbol;

public abstract class PowerUp extends Element {

    private Interaction interaction;

    public PowerUp(int x, int y, Symbol symbol, Interaction interaction) {
        super(x, y, symbol);
        this.interaction = interaction;
    }

    public Interaction getInteraction() {return interaction;}
    public void setInteraction(Interaction interaction) {this.interaction = interaction;}
}
