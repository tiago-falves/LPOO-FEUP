package g24.controller.commands.user;

import g24.controller.commands.user.MoveLeftCommand;
import g24.controller.element.CollisionHandler;
import g24.controller.map.RoomController;
import g24.model.element.Element;
import g24.model.element.Isaac;
import g24.model.utils.Position;
import g24.model.utils.Positions;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MoveLeftCommandTest {
    private Positions positionsMock;
    private Positions positionsLeftMock;
    private RoomController roomControllerMock;
    private Element element;


    @Before
    public void setup(){
        roomControllerMock = mock(RoomController.class);
        when(roomControllerMock.isInsideBoundaries(any(Positions.class))).thenReturn(true);

        positionsMock = mock(Positions.class);
        positionsLeftMock = mock(Positions.class);
        when(positionsMock.left()).thenReturn(positionsLeftMock);

        element = mock(Element.class);
        when(element.getPositions()).thenReturn(positionsMock);

        doNothing().when(element).setPositions(any(Positions.class));
    }

    @Test
    public void test1(){

        CollisionHandler collisionHandler = mock(CollisionHandler.class);
        when(collisionHandler.handleDoorCollisionLeft(roomControllerMock,positionsLeftMock)).thenReturn(false);
        when(collisionHandler.collidingMonster(any(Positions.class))).thenReturn(false);

        MoveLeftCommand moveLeftCommand = new MoveLeftCommand(element, roomControllerMock,collisionHandler);
        moveLeftCommand.execute();

        verify(element,times(1)).setPositions(any(Positions.class));
    }

    @Test
    public void test2(){

        CollisionHandler collisionHandler = mock(CollisionHandler.class);
        when(collisionHandler.handleDoorCollisionLeft(roomControllerMock,positionsLeftMock)).thenReturn(false);
        when(collisionHandler.collidingMonster(any(Positions.class))).thenReturn(true);
        MoveLeftCommand moveLeftCommand = new MoveLeftCommand(element, roomControllerMock,collisionHandler);
        moveLeftCommand.execute();

        verify(element,times(0)).setPositions(any(Positions.class));
    }

    @Test
    public void test3(){

        CollisionHandler collisionHandler = mock(CollisionHandler.class);
        when(collisionHandler.handleDoorCollisionLeft(roomControllerMock,positionsLeftMock)).thenReturn(true);
        when(collisionHandler.collidingMonster(any(Positions.class))).thenReturn(false);
        MoveLeftCommand moveLeftCommand = new MoveLeftCommand(element, roomControllerMock,collisionHandler);
        moveLeftCommand.execute();

        verify(element,times(0)).setPositions(any(Positions.class));
    }




}
