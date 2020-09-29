package g24.model.element.objects;

import g24.model.element.Element;
import g24.model.utils.Symbol;

public class Wall extends IndestructibleObject {
    private static final Symbol wallSymbol = new Symbol("W","#af98a9");

    public Wall(int x, int y) {
        super(x, y, wallSymbol);
    }

}
