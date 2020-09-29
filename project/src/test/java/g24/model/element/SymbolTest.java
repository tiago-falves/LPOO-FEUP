package g24.model.element;

import g24.model.utils.Symbol;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SymbolTest {
    private List<Symbol> symbols;

    @Before
    public void setup() {
        symbols = new ArrayList<>();
        symbols.add(new Symbol("I", "#af98a9"));
        symbols.add(new Symbol("(╥_╥)","#e2ceb8"));
        symbols.add(new Symbol("CookieMonster","#ffffff"));
    }

    @Test
    public void getSize() {
        assertEquals(symbols.get(0).getSize(), "I".length());
        assertEquals(symbols.get(1).getSize(), "(╥_╥)".length());
        assertEquals(symbols.get(2).getSize(), "CookieMonster".length());
    }

    @Test
    public void getSymbol() {
        assertEquals(symbols.get(0).getSymbol(), "I");
        assertEquals(symbols.get(1).getSymbol(), "(╥_╥)");
        assertEquals(symbols.get(2).getSymbol(), "CookieMonster");
    }

    @Test
    public void getColor() {
        assertEquals(symbols.get(0).getColor(), "#af98a9");
        assertEquals(symbols.get(1).getColor(), "#e2ceb8");
        assertEquals(symbols.get(2).getColor(), "#ffffff");
    }
}
