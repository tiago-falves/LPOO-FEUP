package g24.controller.element.movementStrategy;

import g24.GameConfig;
import g24.controller.element.movementstrategy.MoveRandomStrategy;
import g24.model.utils.Position;
import g24.model.utils.Positions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MoveRandomStrategyTest {
    @Test
    public void move(){

        Positions positionsMock = getPositions();
        Positions positionsOther = getOtherPosition();

        when(positionsMock.left()).thenReturn(positionsOther);
        when(positionsMock.up()).thenReturn(positionsOther);
        when(positionsMock.right()).thenReturn(positionsOther);
        when(positionsMock.down()).thenReturn(positionsOther);

        MoveRandomStrategy randomMovementStrategy = new MoveRandomStrategy();


        for (int j = 0; j < 100; j++) {
            //The first FPS/3 Times should be the same
            for (int i = 0; i < GameConfig.FPS/3-1; i++) {
                assertEquals(positionsMock,randomMovementStrategy.move(positionsMock));
            }
            //After that it changes
            assertNotSame(positionsMock,randomMovementStrategy.move(positionsMock));
        }


    }

    public Positions getPositions(){
        Positions positionsMock = mock(Positions.class);
        List<Position> positions = new ArrayList<>();

        Position pos1 = mock(Position.class);
        when(pos1.getX()).thenReturn(1);
        when(pos1.getY()).thenReturn(1);

        Position pos2 = mock(Position.class);
        when(pos2.getX()).thenReturn(2);
        when(pos2.getY()).thenReturn(2);

        Position pos3 = mock(Position.class);
        when(pos3.getX()).thenReturn(3);
        when(pos3.getY()).thenReturn(3);

        positions.add(pos1);
        positions.add(pos2);
        positions.add(pos3);
        when(positionsMock.getPositionList()).thenReturn(positions);
        when(positionsMock.left()).thenReturn(positionsMock);
        when(positionsMock.up()).thenReturn(positionsMock);
        when(positionsMock.right()).thenReturn(positionsMock);
        when(positionsMock.down()).thenReturn(positionsMock);

        return positionsMock;
    }

    public Positions getOtherPosition(){
        Positions positionsMock = mock(Positions.class);
        List<Position> positions = new ArrayList<>();

        Position pos1 = mock(Position.class);
        when(pos1.getX()).thenReturn(2);
        when(pos1.getY()).thenReturn(2);

        Position pos2 = mock(Position.class);
        when(pos2.getX()).thenReturn(3);
        when(pos2.getY()).thenReturn(3);

        Position pos3 = mock(Position.class);
        when(pos3.getX()).thenReturn(4);
        when(pos3.getY()).thenReturn(4);

        positions.add(pos1);
        positions.add(pos2);
        positions.add(pos3);
        when(positionsMock.getPositionList()).thenReturn(positions);

        return positionsMock;
    }

}
