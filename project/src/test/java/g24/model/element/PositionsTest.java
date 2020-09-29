package g24.model.element;

import g24.model.utils.Position;
import g24.model.utils.Positions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PositionsTest {
    private List<Positions> positionsList = new ArrayList<>();

    @Before
    public void setup(){
        Position positionMock1 = mock(Position.class);
        when(positionMock1.getX()).thenReturn(1);
        when(positionMock1.getY()).thenReturn(1);

        Position positionMock2 = mock(Position.class);
        when(positionMock2.getX()).thenReturn(2);
        when(positionMock2.getY()).thenReturn(1);

        Position positionMock3 = mock(Position.class);
        when(positionMock3.getX()).thenReturn(3);
        when(positionMock3.getY()).thenReturn(1);

        Position positionMock4 = mock(Position.class);
        when(positionMock4.getX()).thenReturn(4);
        when(positionMock4.getY()).thenReturn(1);

        Position positionMock2up = mock(Position.class);
        when(positionMock2up.getX()).thenReturn(2);
        when(positionMock2up.getY()).thenReturn(0);

        Position positionMock2down = mock(Position.class);
        when(positionMock2down.getX()).thenReturn(2);
        when(positionMock2down.getY()).thenReturn(2);

        Position positionMock3up = mock(Position.class);
        when(positionMock3up.getX()).thenReturn(3);
        when(positionMock3up.getY()).thenReturn(0);

        Position positionMock3down = mock(Position.class);
        when(positionMock3down.getX()).thenReturn(3);
        when(positionMock3down.getY()).thenReturn(2);

        when(positionMock2.right()).thenReturn(positionMock3);
        when(positionMock3.right()).thenReturn(positionMock4);

        when(positionMock2.left()).thenReturn(positionMock1);
        when(positionMock3.left()).thenReturn(positionMock2);

        when(positionMock2.up()).thenReturn(positionMock2up);
        when(positionMock3.up()).thenReturn(positionMock3up);

        when(positionMock2.down()).thenReturn(positionMock2down);
        when(positionMock3.down()).thenReturn(positionMock3down);

        when(positionMock1.collide(positionMock1)).thenReturn(true);
        when(positionMock1.collide(positionMock2)).thenReturn(false);
        when(positionMock1.collide(positionMock3)).thenReturn(false);

        when(positionMock2.collide(positionMock1)).thenReturn(false);
        when(positionMock2.collide(positionMock2)).thenReturn(true);
        when(positionMock2.collide(positionMock3)).thenReturn(false);

        when(positionMock3.collide(positionMock1)).thenReturn(false);
        when(positionMock3.collide(positionMock2)).thenReturn(false);
        when(positionMock3.collide(positionMock3)).thenReturn(true);

        Positions positions0 = new Positions();
        positions0.addPosition(positionMock1);
        positions0.addPosition(positionMock2);
        positions0.addPosition(positionMock3);

        Positions positions1 = new Positions();
        positions1.addPosition(positionMock2);
        positions1.addPosition(positionMock3);

        Positions positions2 = new Positions();
        positions2.addPosition(positionMock1);

        positionsList.add(positions0);
        positionsList.add(positions1);
        positionsList.add(positions2);
    }

    @Test
    public void getPositions() {
        Position positionMock1 = mock(Position.class);
        when(positionMock1.getX()).thenReturn(1);
        when(positionMock1.getY()).thenReturn(1);

        Position positionMock2 = mock(Position.class);
        when(positionMock2.getX()).thenReturn(2);
        when(positionMock2.getY()).thenReturn(1);

        Positions positionsToTest = positionsList.get(0);
        assertEquals(positionsToTest.getPositionList().get(0).getX(), positionMock1.getX());
        assertEquals(positionsToTest.getPositionList().get(0).getY(), positionMock1.getY());

        assertEquals(positionsToTest.getPositionList().get(1).getX(), positionMock2.getX());
        assertEquals(positionsToTest.getPositionList().get(1).getY(), positionMock2.getY());
    }

    @Test
    public void getPositionOrder() {
        Position positionMock1 = mock(Position.class);
        when(positionMock1.getX()).thenReturn(1);
        when(positionMock1.getY()).thenReturn(1);

        Position positionMock2 = mock(Position.class);
        when(positionMock2.getX()).thenReturn(3);
        when(positionMock2.getY()).thenReturn(1);

        Positions positionsToTest = positionsList.get(0);
        assertEquals(positionsToTest.getFirstPosition().getX(), positionMock1.getX());
        assertEquals(positionsToTest.getFirstPosition().getX(), positionMock1.getY());

        assertEquals(positionsToTest.getLastPosition().getX(), positionMock2.getX());
        assertEquals(positionsToTest.getLastPosition().getY(), positionMock2.getY());
    }

    @Test
    public void collide() {
        Positions positions0 = positionsList.get(0);
        Positions positions1 = positionsList.get(1);
        Positions positions2 = positionsList.get(2);

        assertTrue(positions0.collide(positions1));
        assertTrue(positions1.collide(positions0));
        assertFalse(positions1.collide(positions2));
        assertFalse(positions2.collide(positions1));
        assertTrue(positions0.collide(positions2));
        assertTrue(positions2.collide(positions0));
    }

    @Test
    public void movement() {
        Positions positions = positionsList.get(1);

        Positions moveLeft = positions.left();
        assertEquals(moveLeft.getPositionList().size(), positions.getPositionList().size());
        assertEquals(moveLeft.getPositionList().get(0).getX(),  positions.getPositionList().get(0).getX() - 1);
        assertEquals(moveLeft.getPositionList().get(0).getY(),  positions.getPositionList().get(0).getY());
        assertEquals(moveLeft.getPositionList().get(1).getX(),  positions.getPositionList().get(1).getX() - 1);
        assertEquals(moveLeft.getPositionList().get(1).getY(),  positions.getPositionList().get(1).getY());

        Positions moveRight = positions.right();
        assertEquals(moveRight.getPositionList().size(), positions.getPositionList().size());
        assertEquals(moveRight.getPositionList().get(0).getX(),  positions.getPositionList().get(0).getX() + 1);
        assertEquals(moveRight.getPositionList().get(0).getY(),  positions.getPositionList().get(0).getY());
        assertEquals(moveRight.getPositionList().get(1).getX(),  positions.getPositionList().get(1).getX() + 1);
        assertEquals(moveRight.getPositionList().get(1).getY(),  positions.getPositionList().get(1).getY());

        Positions moveUp = positions.up();
        assertEquals(moveUp.getPositionList().size(), positions.getPositionList().size());
        assertEquals(moveUp.getPositionList().get(0).getX(),  positions.getPositionList().get(0).getX());
        assertEquals(moveUp.getPositionList().get(0).getY(),  positions.getPositionList().get(0).getY() - 1);
        assertEquals(moveUp.getPositionList().get(1).getX(),  positions.getPositionList().get(1).getX());
        assertEquals(moveUp.getPositionList().get(1).getY(),  positions.getPositionList().get(1).getY() - 1);

        Positions moveDown = positions.down();
        assertEquals(moveDown.getPositionList().size(), positions.getPositionList().size());
        assertEquals(moveDown.getPositionList().get(0).getX(),  positions.getPositionList().get(0).getX());
        assertEquals(moveDown.getPositionList().get(0).getY(),  positions.getPositionList().get(0).getY() + 1);
        assertEquals(moveDown.getPositionList().get(1).getX(),  positions.getPositionList().get(1).getX());
        assertEquals(moveDown.getPositionList().get(1).getY(),  positions.getPositionList().get(1).getY() + 1);

    }
}
