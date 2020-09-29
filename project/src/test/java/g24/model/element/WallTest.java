package g24.model.element;

import g24.model.element.objects.Wall;
import g24.model.utils.Symbol;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class WallTest {
    @Test
    public void test() {
        Symbol symbolMock = mock(Symbol.class);
        when(symbolMock.getSymbol()).thenReturn("W");

        Wall wall = new Wall(1, 1);

        assertEquals(wall.getSymbol().getSymbol(), symbolMock.getSymbol());
    }
}
