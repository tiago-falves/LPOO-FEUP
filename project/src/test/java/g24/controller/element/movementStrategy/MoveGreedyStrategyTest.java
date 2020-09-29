package g24.controller.element.movementStrategy;

import g24.GameConfig;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.controller.element.movementstrategy.MoveGreedyStrategy;
import g24.model.element.Isaac;
import g24.model.utils.Position;
import g24.model.utils.Positions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class MoveGreedyStrategyTest {
    @Test
    public void move(){

        Position middle = mock(Position.class);

        Positions positionsMock = getPositions();
        Positions positionsOther = getOtherPosition();

        when(positionsMock.getMoveDirection(middle)).thenReturn(DIRECTION.RIGHT);
        when(positionsMock.moveTo(DIRECTION.RIGHT)).thenReturn(positionsMock);

        Isaac isaac = mock(Isaac.class);
        when(positionsOther.getMiddlePosition()).thenReturn(middle);
        when(isaac.getPositions()).thenReturn(positionsOther);

        MoveGreedyStrategy greedyMoveStrategy = new MoveGreedyStrategy(isaac);

        //The first FPS/3 Times should be the same
        for (int i = 0; i < GameConfig.FPS/6-1; i++) {
            assertEquals(positionsMock,greedyMoveStrategy.move(positionsMock));
        }
        Positions result = greedyMoveStrategy.move(positionsMock);

        verify(positionsMock, times(1)).getMoveDirection(any());
        verify(positionsMock, times(1)).moveTo(DIRECTION.RIGHT);


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
        when(pos1.getY()).thenReturn(1);

        Position pos2 = mock(Position.class);
        when(pos2.getX()).thenReturn(3);
        when(pos2.getY()).thenReturn(2);

        Position pos3 = mock(Position.class);
        when(pos3.getX()).thenReturn(4);
        when(pos3.getY()).thenReturn(3);

        positions.add(pos1);
        positions.add(pos2);
        positions.add(pos3);
        when(positionsMock.getPositionList()).thenReturn(positions);

        return positionsMock;
    }

}
