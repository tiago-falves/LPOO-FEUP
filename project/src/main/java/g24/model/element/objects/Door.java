package g24.model.element.objects;

import g24.model.element.Element;
import g24.model.utils.Symbol;

public class Door extends IndestructibleObject {
    private static final Symbol doorSymbol = new Symbol("D","#EEBB44");

    public Door(int x, int y) {
        super(x, y, doorSymbol);
    }
}
