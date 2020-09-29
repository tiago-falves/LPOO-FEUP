package g24.model.element;

import g24.model.utils.Position;
import g24.model.utils.Positions;
import g24.model.utils.Symbol;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ElementTest {
    List<Element> elementMocks = new ArrayList<>();
    @Before
    public void setup() {
        Symbol symbolMock1 = mock(Symbol.class);
        when(symbolMock1.getSymbol()).thenReturn(":{P");
        when(symbolMock1.getSize()).thenReturn(3);

        elementMocks.add(new Element(1, 2, symbolMock1) {});
    }

    @Test
    public void getPositions() {
        Position positionMock1 = mock(Position.class);
        when(positionMock1.getX()).thenReturn(1);
        when(positionMock1.getY()).thenReturn(2);

        Position positionMock2 = mock(Position.class);
        when(positionMock2.getX()).thenReturn(2);
        when(positionMock2.getY()).thenReturn(2);

        Position positionMock3 = mock(Position.class);
        when(positionMock3.getX()).thenReturn(3);
        when(positionMock3.getY()).thenReturn(2);

        Positions positions = elementMocks.get(0).getPositions();

        assertEquals(positions.getPositionList().size(), 3);

        assertEquals(positions.getPositionList().get(0).getX(), positionMock1.getX());
        assertEquals(positions.getPositionList().get(0).getY(), positionMock1.getY());

        assertEquals(positions.getPositionList().get(1).getX(), positionMock2.getX());
        assertEquals(positions.getPositionList().get(1).getY(), positionMock2.getY());

        assertEquals(positions.getPositionList().get(2).getX(), positionMock3.getX());
        assertEquals(positions.getPositionList().get(2).getY(), positionMock3.getY());
    }

    @Test
    public void setPositions() {
        Position positionMock1 = mock(Position.class);
        when(positionMock1.getX()).thenReturn(6);
        when(positionMock1.getY()).thenReturn(9);

        Position positionMock2 = mock(Position.class);
        when(positionMock2.getX()).thenReturn(7);
        when(positionMock2.getY()).thenReturn(9);

        Position positionMock3 = mock(Position.class);
        when(positionMock3.getX()).thenReturn(8);
        when(positionMock3.getY()).thenReturn(9);

        List<Position> positionsList = new ArrayList<>();
        positionsList.add(positionMock1);
        positionsList.add(positionMock2);
        positionsList.add(positionMock3);

        Positions positionsMock = mock(Positions.class);
        when(positionsMock.getPositionList()).thenReturn(positionsList);

        elementMocks.get(0).setPositions(positionsMock);

        Positions positions = elementMocks.get(0).getPositions();
        assertEquals(positions.getPositionList().size(), 3);

        assertEquals(positions.getPositionList().get(0).getX(), positionMock1.getX());
        assertEquals(positions.getPositionList().get(0).getY(), positionMock1.getY());

        assertEquals(positions.getPositionList().get(1).getX(), positionMock2.getX());
        assertEquals(positions.getPositionList().get(1).getY(), positionMock2.getY());

        assertEquals(positions.getPositionList().get(2).getX(), positionMock3.getX());
        assertEquals(positions.getPositionList().get(2).getY(), positionMock3.getY());
    }

    @Test
    public void getSymbol() {
        assertEquals(elementMocks.get(0).getSymbol().getSymbol(), ":{P");
        assertEquals(elementMocks.get(0).getSymbol().getSize(), 3);
    }
}
