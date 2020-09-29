package g24.model.element;

import g24.model.utils.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class PositionTest{
    private List<Position> positions;
    @Before
    public void setup(){
        positions = new ArrayList<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(1, 3));
        positions.add(new Position(5, 2));
        positions.add(new Position(57, 39));
    }

    @Test
    public void equalPosition1() {
        Position p0 = new Position(0, 0);
        assertEquals(positions.get(0), p0);
    }

    @Test
    public void equalPosition2() {
        Position p1 = new Position(1, 3);
        assertEquals(positions.get(1), p1);
    }

    @Test
    public void equalPosition3() {
        assertEquals(positions.get(1), positions.get(1));
    }

    @Test
    public void notEqualPosition1() {
        Position p2 = new Position(4, 2);
        assertNotEquals(positions.get(2), p2);
    }

    @Test
    public void notEqualPosition2() {
        Position p3 = new Position(-57, 40);
        assertNotEquals(positions.get(3), p3);
    }

    @Test
    public void notEqualPosition3() {
        assertNotEquals(positions.get(3), new String());
    }

    @Test
    public void collide() {
        assertTrue(positions.get(0).collide(positions.get(0)));
        assertFalse(positions.get(0).collide(positions.get(1)));
    }

    @Test
    public void positionGetCoords1() {
        assertEquals(5, positions.get(2).getX());
        assertEquals(2, positions.get(2).getY());
    }

    @Test
    public void positionGetCoords2() {
        assertEquals(57, positions.get(3).getX());
        assertEquals(39, positions.get(3).getY());
    }

    @Test
    public void positionMoveUp1() {
        assertEquals(new Position(0, -1), positions.get(0).up());
    }

    @Test
    public void positionMoveUp2() {
        assertEquals(new Position(1, 2), positions.get(1).up());
    }

    @Test
    public void positionMoveDown1() {
        assertEquals(new Position(5, 3), positions.get(2).down());
    }

    @Test
    public void positionMoveDown2() {
        assertEquals(new Position(57, 40), positions.get(3).down());
    }

    @Test
    public void positionMoveLeft1() {
        assertEquals(new Position(-1, 0), positions.get(0).left());
    }

    @Test
    public void positionMoveLeft2() {
        assertEquals(new Position(0, 3), positions.get(1).left());
    }

    @Test
    public void positionMoveRight1() {
        assertEquals(new Position(6, 2), positions.get(2).right());
    }

    @Test
    public void positionMoveRight2() {
        assertEquals(new Position(58, 39), positions.get(3).right());
    }

    @Test
    public void positionHashCode1() {
        assertEquals(Objects.hash(positions.get(1).getX(), positions.get(1).getY()), positions.get(1).hashCode());
    }

    @Test
    public void positionHashCode2() {
        assertEquals(Objects.hash(positions.get(2).getX(), positions.get(2).getY()), positions.get(2).hashCode());
    }


}