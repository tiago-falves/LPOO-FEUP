package g24.controller.map;

import g24.GameConfig;
import g24.controller.commands.user.COMMAND;
import g24.controller.element.CollisionHandler;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.controller.observer.VisitedRoomsObserver;
import g24.model.element.Isaac;
import g24.model.element.monsters.Monster;
import g24.model.map.Compass;
import g24.model.map.RoomModel;
import g24.model.map.RoomType;
import g24.model.utils.Position;
import g24.model.utils.Positions;
import g24.model.utils.Symbol;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.mockito.ArgumentCaptor;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import g24.model.map.MapTemplate;

import java.util.ArrayList;
import java.util.List;

public class MapControllerTest {
    VisitedRoomsObserver visitedRoomsObserverMock;
    Isaac isaacMock;
    MapTemplate mapTemplateMock;
    RoomController roomControllerMock;
    RoomFactory roomFactoryMock;
    TimeController timeControllerMock;
    RoomModel roomModelMock;
    MapController mapController;
    Position startPositionMock;

    @Before
    public void setup() {
        isaacMock = Mockito.mock(Isaac.class);
        roomControllerMock = Mockito.mock(RoomController.class);
        timeControllerMock = Mockito.mock(TimeController.class);
        roomFactoryMock = Mockito.mock(RoomFactory.class);

        Symbol symbolMock = Mockito.mock(Symbol.class);
        when(isaacMock.getSymbol()).thenReturn(symbolMock);
        when(symbolMock.getSize()).thenReturn(3);

        visitedRoomsObserverMock = Mockito.mock(VisitedRoomsObserver.class);
        startPositionMock = Mockito.mock(Position.class);
        when(startPositionMock.getX()).thenReturn(2);
        when(startPositionMock.getY()).thenReturn(2);

        mapTemplateMock = Mockito.mock(MapTemplate.class);
        when(mapTemplateMock.getWidth()).thenReturn(6);
        when(mapTemplateMock.getHeight()).thenReturn(6);
        when(mapTemplateMock.getStart()).thenReturn(startPositionMock);
        when(mapTemplateMock.getRoom(2, 2)).thenReturn(RoomType.START);
        when(mapTemplateMock.getRoom(2, 1)).thenReturn(RoomType.EMPTY);
        when(mapTemplateMock.getRoom(2, 3)).thenReturn(RoomType.EMPTY);
        when(mapTemplateMock.getRoom(1, 2)).thenReturn(RoomType.ENEMY_EASY);
        when(mapTemplateMock.getRoom(3, 2)).thenReturn(RoomType.ENEMY_EASY);


        roomModelMock = Mockito.mock(RoomModel.class);
        when(roomFactoryMock.createIsaac()).thenReturn(isaacMock);
        when(roomFactoryMock.createRoom(any(Isaac.class), any(Compass.class), any(RoomType.class))).thenReturn(roomModelMock);
        mapController = new MapController(mapTemplateMock, roomControllerMock, timeControllerMock, roomFactoryMock);

        when(roomModelMock.getWidth()).thenReturn(GameConfig.ROOM_WIDTH);
        when(roomModelMock.getHeight()).thenReturn(GameConfig.ROOM_HEIGHT);

        ArgumentCaptor<Compass> argumentCaptor = ArgumentCaptor.forClass(Compass.class);
        verify(roomFactoryMock, times(1)).createRoom(any(Isaac.class), argumentCaptor.capture(), any(RoomType.class));
        assertTrue(argumentCaptor.getValue().isWest());
        assertTrue(argumentCaptor.getValue().isEast());
        assertFalse(argumentCaptor.getValue().isNorth());
        assertFalse(argumentCaptor.getValue().isSouth());
    }

    @Test
    public void addObserver() {
        final int[] functionCallCounter = {0};
        Answer<Boolean> answer = invocation -> {
            functionCallCounter[0]++;
            return true;
        };
        doAnswer(answer).when(visitedRoomsObserverMock).updateVisitedRooms();

        mapController.addObserver(visitedRoomsObserverMock);
        mapController.notifyObservers();
        assertEquals(1, functionCallCounter[0]);
    }

    @Test
    public void removeObserver() {
        final int[] functionCallCounter = {0};
        Answer<Boolean>  answer = invocation -> {
            functionCallCounter[0]++;
            return true;
        };
        doAnswer(answer).when(visitedRoomsObserverMock).updateVisitedRooms();

        mapController.addObserver(visitedRoomsObserverMock);
        mapController.removeObserver(visitedRoomsObserverMock);
        mapController.notifyObservers();
        assertEquals(0, functionCallCounter[0]);
    }

    @Test
    public void finish1() {
        doNothing().when(timeControllerMock).updateTime();

        doNothing().when(roomControllerMock).nextIteration();
        when(roomControllerMock.isFinished()).thenReturn(false);
        when(roomControllerMock.hasWon()).thenReturn(false);
        when(roomControllerMock.getRoomModel()).thenReturn(roomModelMock);
        when(roomControllerMock.changeRoom()).thenReturn(false);

        List<Monster> emptyMonsters = new ArrayList<>();
        when(roomModelMock.getMonsters()).thenReturn(emptyMonsters);
        when(roomModelMock.getIsaac()).thenReturn(isaacMock);

        when(mapTemplateMock.getRoom(anyInt(), anyInt())).thenReturn(RoomType.BOSS);

        mapController.nextIteration(COMMAND.NOTHING);

        assertTrue(mapController.hasEnded());
        assertTrue(mapController.hasWon());
    }

    @Test
    public void finish2() {
        doNothing().when(timeControllerMock).updateTime();

        doNothing().when(roomControllerMock).nextIteration();
        when(roomControllerMock.isFinished()).thenReturn(true);
        when(roomControllerMock.hasWon()).thenReturn(false);
        when(roomControllerMock.getRoomModel()).thenReturn(roomModelMock);
        when(roomControllerMock.changeRoom()).thenReturn(false);

        List<Monster> emptyMonsters = new ArrayList<>();
        when(roomModelMock.getMonsters()).thenReturn(emptyMonsters);
        when(roomModelMock.getIsaac()).thenReturn(isaacMock);

        when(mapTemplateMock.getRoom(anyInt(), anyInt())).thenReturn(RoomType.START);

        mapController.nextIteration(COMMAND.NOTHING);

        assertTrue(mapController.hasEnded());
        assertFalse(mapController.hasWon());
    }

    @Test
    public void finish3() {
        doNothing().when(timeControllerMock).updateTime();

        doNothing().when(roomControllerMock).nextIteration();
        when(roomControllerMock.isFinished()).thenReturn(false);
        when(roomControllerMock.hasWon()).thenReturn(false);
        when(roomControllerMock.getRoomModel()).thenReturn(roomModelMock);
        when(roomControllerMock.changeRoom()).thenReturn(false);

        List<Monster> emptyMonsters = new ArrayList<>();
        when(roomModelMock.getMonsters()).thenReturn(emptyMonsters);
        when(roomModelMock.getIsaac()).thenReturn(isaacMock);

        when(mapTemplateMock.getRoom(anyInt(), anyInt())).thenReturn(RoomType.START);

        mapController.nextIteration(COMMAND.QUIT);

        assertTrue(mapController.hasEnded());
        assertFalse(mapController.hasWon());
    }

    @Test
    public void moveUp() {
        doNothing().when(timeControllerMock).updateTime();

        CollisionHandler collisionHandlerMock = mock(CollisionHandler.class);

        doNothing().when(roomControllerMock).nextIteration();
        when(roomControllerMock.isFinished()).thenReturn(false);
        when(roomControllerMock.hasWon()).thenReturn(false);
        when(roomControllerMock.getRoomModel()).thenReturn(roomModelMock);
        when(roomControllerMock.changeRoom()).thenReturn(false);
        when(roomControllerMock.getCollisionHandler()).thenReturn(collisionHandlerMock);


        List<Monster> emptyMonsters = new ArrayList<>();
        when(roomModelMock.getMonsters()).thenReturn(emptyMonsters);
        when(roomModelMock.getIsaac()).thenReturn(isaacMock);

        when(mapTemplateMock.getRoom(anyInt(), anyInt())).thenReturn(RoomType.START);

        Positions positionsMock = mock(Positions.class);
        Positions positionsUpMock = mock(Positions.class);
        when(positionsMock.up()).thenReturn(positionsUpMock);
        when(roomControllerMock.isInsideBoundaries(any(Positions.class))).thenReturn(true);
        when(isaacMock.getPositions()).thenReturn(positionsMock);
        doNothing().when(isaacMock).setPositions(any(Positions.class));

        when(collisionHandlerMock.handleDoorCollisionUp(roomControllerMock,positionsUpMock)).thenReturn(false);
        when(collisionHandlerMock.collidingMonster(any(Positions.class))).thenReturn(false);

        mapController.nextIteration(COMMAND.UP);

        verify(isaacMock,times(1)).setPositions(any(Positions.class));

        assertFalse(mapController.hasEnded());
        assertFalse(mapController.hasWon());
    }

    @Test
    public void moveDown() {
        doNothing().when(timeControllerMock).updateTime();

        CollisionHandler collisionHandlerMock = mock(CollisionHandler.class);

        doNothing().when(roomControllerMock).nextIteration();
        when(roomControllerMock.isFinished()).thenReturn(false);
        when(roomControllerMock.hasWon()).thenReturn(false);
        when(roomControllerMock.getRoomModel()).thenReturn(roomModelMock);
        when(roomControllerMock.changeRoom()).thenReturn(false);
        when(roomControllerMock.getCollisionHandler()).thenReturn(collisionHandlerMock);


        List<Monster> emptyMonsters = new ArrayList<>();
        when(roomModelMock.getMonsters()).thenReturn(emptyMonsters);
        when(roomModelMock.getIsaac()).thenReturn(isaacMock);

        when(mapTemplateMock.getRoom(anyInt(), anyInt())).thenReturn(RoomType.START);

        Positions positionsMock = mock(Positions.class);
        Positions positionsDownMock = mock(Positions.class);
        when(positionsMock.down()).thenReturn(positionsDownMock);
        when(roomControllerMock.isInsideBoundaries(any(Positions.class))).thenReturn(true);
        when(isaacMock.getPositions()).thenReturn(positionsMock);
        doNothing().when(isaacMock).setPositions(any(Positions.class));

        when(collisionHandlerMock.handleDoorCollisionDown(roomControllerMock,positionsDownMock)).thenReturn(false);
        when(collisionHandlerMock.collidingMonster(any(Positions.class))).thenReturn(false);

        mapController.nextIteration(COMMAND.DOWN);

        verify(isaacMock,times(1)).setPositions(any(Positions.class));

        assertFalse(mapController.hasEnded());
        assertFalse(mapController.hasWon());
    }

    @Test
    public void moveLeft() {
        doNothing().when(timeControllerMock).updateTime();

        CollisionHandler collisionHandlerMock = mock(CollisionHandler.class);

        doNothing().when(roomControllerMock).nextIteration();
        when(roomControllerMock.isFinished()).thenReturn(false);
        when(roomControllerMock.hasWon()).thenReturn(false);
        when(roomControllerMock.getRoomModel()).thenReturn(roomModelMock);
        when(roomControllerMock.changeRoom()).thenReturn(false);
        when(roomControllerMock.getCollisionHandler()).thenReturn(collisionHandlerMock);


        List<Monster> emptyMonsters = new ArrayList<>();
        when(roomModelMock.getMonsters()).thenReturn(emptyMonsters);
        when(roomModelMock.getIsaac()).thenReturn(isaacMock);

        when(mapTemplateMock.getRoom(anyInt(), anyInt())).thenReturn(RoomType.START);

        Positions positionsMock = mock(Positions.class);
        Positions positionsLeftMock = mock(Positions.class);
        when(positionsMock.left()).thenReturn(positionsLeftMock);
        when(roomControllerMock.isInsideBoundaries(any(Positions.class))).thenReturn(true);
        when(isaacMock.getPositions()).thenReturn(positionsMock);
        doNothing().when(isaacMock).setPositions(any(Positions.class));

        when(collisionHandlerMock.handleDoorCollisionLeft(roomControllerMock,positionsLeftMock)).thenReturn(false);
        when(collisionHandlerMock.collidingMonster(any(Positions.class))).thenReturn(false);

        mapController.nextIteration(COMMAND.LEFT);

        verify(isaacMock,times(1)).setPositions(any(Positions.class));

        assertFalse(mapController.hasEnded());
        assertFalse(mapController.hasWon());
    }

    @Test
    public void moveRight() {
        doNothing().when(timeControllerMock).updateTime();

        CollisionHandler collisionHandlerMock = mock(CollisionHandler.class);

        doNothing().when(roomControllerMock).nextIteration();
        when(roomControllerMock.isFinished()).thenReturn(false);
        when(roomControllerMock.hasWon()).thenReturn(false);
        when(roomControllerMock.getRoomModel()).thenReturn(roomModelMock);
        when(roomControllerMock.changeRoom()).thenReturn(false);
        when(roomControllerMock.getCollisionHandler()).thenReturn(collisionHandlerMock);


        List<Monster> emptyMonsters = new ArrayList<>();
        when(roomModelMock.getMonsters()).thenReturn(emptyMonsters);
        when(roomModelMock.getIsaac()).thenReturn(isaacMock);

        when(mapTemplateMock.getRoom(anyInt(), anyInt())).thenReturn(RoomType.START);

        Positions positionsMock = mock(Positions.class);
        Positions positionsRightMock = mock(Positions.class);
        when(positionsMock.right()).thenReturn(positionsRightMock);
        when(roomControllerMock.isInsideBoundaries(any(Positions.class))).thenReturn(true);
        when(isaacMock.getPositions()).thenReturn(positionsMock);
        doNothing().when(isaacMock).setPositions(any(Positions.class));

        when(collisionHandlerMock.handleDoorCollisionRight(roomControllerMock,positionsRightMock)).thenReturn(false);
        when(collisionHandlerMock.collidingMonster(any(Positions.class))).thenReturn(false);

        mapController.nextIteration(COMMAND.RIGHT);

        verify(isaacMock,times(1)).setPositions(any(Positions.class));

        assertFalse(mapController.hasEnded());
        assertFalse(mapController.hasWon());
    }

    @Test
    public void changeRoom1() {
        doNothing().when(timeControllerMock).updateTime();

        doNothing().when(roomControllerMock).nextIteration();
        when(roomControllerMock.isFinished()).thenReturn(false);
        when(roomControllerMock.hasWon()).thenReturn(false);
        when(roomControllerMock.getRoomModel()).thenReturn(roomModelMock);

        List<Monster> emptyMonsters = new ArrayList<>();
        when(roomModelMock.getMonsters()).thenReturn(emptyMonsters);
        when(roomModelMock.getIsaac()).thenReturn(isaacMock);

        when(mapTemplateMock.getRoom(anyInt(), anyInt())).thenReturn(RoomType.START);

        when(roomControllerMock.changeRoom()).thenReturn(true);
        when(roomControllerMock.getChangeRoomDirection()).thenReturn(DIRECTION.UP);
        doNothing().when(roomControllerMock).setRoomModel(any(RoomModel.class));
        doNothing().when(roomControllerMock).reset();

        Position nextPositionMock = mock(Position.class);
        when(nextPositionMock.getX()).thenReturn(2);
        when(nextPositionMock.getY()).thenReturn(1);
        when(startPositionMock.up()).thenReturn(nextPositionMock);

        when(mapTemplateMock.getRoom(1, 1)).thenReturn(RoomType.ENEMY_EASY);
        when(mapTemplateMock.getRoom(3, 1)).thenReturn(RoomType.TRAP);
        when(mapTemplateMock.getRoom(2, 0)).thenReturn(RoomType.EMPTY);

        doNothing().when(isaacMock).setPositions(any(Positions.class));

        mapController.nextIteration(COMMAND.NOTHING);

        ArgumentCaptor<Compass> argumentCaptor = ArgumentCaptor.forClass(Compass.class);
        verify(roomFactoryMock, times(2)).createRoom(any(Isaac.class), argumentCaptor.capture(), any(RoomType.class));
        assertTrue(argumentCaptor.getValue().isWest());
        assertTrue(argumentCaptor.getValue().isEast());
        assertFalse(argumentCaptor.getValue().isNorth());
        assertTrue(argumentCaptor.getValue().isSouth());

        verify(isaacMock, times(1)).setPositions(any(Positions.class));

        assertFalse(mapController.hasEnded());
        assertFalse(mapController.hasWon());
    }

    @Test
    public void changeRoom2() {
        doNothing().when(timeControllerMock).updateTime();

        doNothing().when(roomControllerMock).nextIteration();
        when(roomControllerMock.isFinished()).thenReturn(false);
        when(roomControllerMock.hasWon()).thenReturn(false);
        when(roomControllerMock.getRoomModel()).thenReturn(roomModelMock);

        List<Monster> emptyMonsters = new ArrayList<>();
        when(roomModelMock.getMonsters()).thenReturn(emptyMonsters);
        when(roomModelMock.getIsaac()).thenReturn(isaacMock);

        when(mapTemplateMock.getRoom(anyInt(), anyInt())).thenReturn(RoomType.START);

        when(roomControllerMock.changeRoom()).thenReturn(true);
        when(roomControllerMock.getChangeRoomDirection()).thenReturn(DIRECTION.DOWN);
        doNothing().when(roomControllerMock).setRoomModel(any(RoomModel.class));
        doNothing().when(roomControllerMock).reset();

        Position nextPositionMock = mock(Position.class);
        when(nextPositionMock.getX()).thenReturn(2);
        when(nextPositionMock.getY()).thenReturn(3);
        when(startPositionMock.down()).thenReturn(nextPositionMock);

        when(mapTemplateMock.getRoom(2, 4)).thenReturn(RoomType.EMPTY);
        when(mapTemplateMock.getRoom(1, 3)).thenReturn(RoomType.EMPTY);
        when(mapTemplateMock.getRoom(3, 3)).thenReturn(RoomType.EMPTY);

        doNothing().when(isaacMock).setPositions(any(Positions.class));

        mapController.nextIteration(COMMAND.NOTHING);

        ArgumentCaptor<Compass> argumentCaptor = ArgumentCaptor.forClass(Compass.class);
        verify(roomFactoryMock, times(2)).createRoom(any(Isaac.class), argumentCaptor.capture(), any(RoomType.class));
        assertFalse(argumentCaptor.getValue().isWest());
        assertFalse(argumentCaptor.getValue().isEast());
        assertTrue(argumentCaptor.getValue().isNorth());
        assertFalse(argumentCaptor.getValue().isSouth());

        verify(isaacMock, times(1)).setPositions(any(Positions.class));

        assertFalse(mapController.hasEnded());
        assertFalse(mapController.hasWon());

        when(nextPositionMock.up()).thenReturn(startPositionMock);
        when(roomControllerMock.getChangeRoomDirection()).thenReturn(DIRECTION.UP);
        mapController.nextIteration(COMMAND.NOTHING);
        verify(roomFactoryMock, times(2)).createRoom(any(Isaac.class), argumentCaptor.capture(), any(RoomType.class));
    }

    @Test
    public void changeRoom3() {
        doNothing().when(timeControllerMock).updateTime();

        doNothing().when(roomControllerMock).nextIteration();
        when(roomControllerMock.isFinished()).thenReturn(false);
        when(roomControllerMock.hasWon()).thenReturn(false);
        when(roomControllerMock.getRoomModel()).thenReturn(roomModelMock);

        List<Monster> emptyMonsters = new ArrayList<>();
        when(roomModelMock.getMonsters()).thenReturn(emptyMonsters);
        when(roomModelMock.getIsaac()).thenReturn(isaacMock);

        when(mapTemplateMock.getRoom(anyInt(), anyInt())).thenReturn(RoomType.START);

        when(roomControllerMock.changeRoom()).thenReturn(true);
        when(roomControllerMock.getChangeRoomDirection()).thenReturn(DIRECTION.RIGHT);
        doNothing().when(roomControllerMock).setRoomModel(any(RoomModel.class));
        doNothing().when(roomControllerMock).reset();

        Position nextPositionMock = mock(Position.class);
        when(nextPositionMock.getX()).thenReturn(3);
        when(nextPositionMock.getY()).thenReturn(2);
        when(startPositionMock.right()).thenReturn(nextPositionMock);

        when(mapTemplateMock.getRoom(4, 2)).thenReturn(RoomType.TRAP);
        when(mapTemplateMock.getRoom(3, 1)).thenReturn(RoomType.EMPTY);
        when(mapTemplateMock.getRoom(3, 3)).thenReturn(RoomType.EMPTY);

        doNothing().when(isaacMock).setPositions(any(Positions.class));

        mapController.nextIteration(COMMAND.NOTHING);

        ArgumentCaptor<Compass> argumentCaptor = ArgumentCaptor.forClass(Compass.class);
        verify(roomFactoryMock, times(2)).createRoom(any(Isaac.class), argumentCaptor.capture(), any(RoomType.class));
        assertTrue(argumentCaptor.getValue().isWest());
        assertTrue(argumentCaptor.getValue().isEast());
        assertFalse(argumentCaptor.getValue().isNorth());
        assertFalse(argumentCaptor.getValue().isSouth());

        verify(isaacMock, times(1)).setPositions(any(Positions.class));

        assertFalse(mapController.hasEnded());
        assertFalse(mapController.hasWon());
    }

    @Test
    public void changeRoom4() {
        doNothing().when(timeControllerMock).updateTime();

        doNothing().when(roomControllerMock).nextIteration();
        when(roomControllerMock.isFinished()).thenReturn(false);
        when(roomControllerMock.hasWon()).thenReturn(false);
        when(roomControllerMock.getRoomModel()).thenReturn(roomModelMock);

        List<Monster> emptyMonsters = new ArrayList<>();
        when(roomModelMock.getMonsters()).thenReturn(emptyMonsters);
        when(roomModelMock.getIsaac()).thenReturn(isaacMock);

        when(mapTemplateMock.getRoom(anyInt(), anyInt())).thenReturn(RoomType.START);

        when(roomControllerMock.changeRoom()).thenReturn(true);
        when(roomControllerMock.getChangeRoomDirection()).thenReturn(DIRECTION.LEFT);
        doNothing().when(roomControllerMock).setRoomModel(any(RoomModel.class));
        doNothing().when(roomControllerMock).reset();

        Position nextPositionMock = mock(Position.class);
        when(nextPositionMock.getX()).thenReturn(1);
        when(nextPositionMock.getY()).thenReturn(2);
        when(startPositionMock.left()).thenReturn(nextPositionMock);

        when(mapTemplateMock.getRoom(0, 2)).thenReturn(RoomType.EMPTY);
        when(mapTemplateMock.getRoom(1, 1)).thenReturn(RoomType.EMPTY);
        when(mapTemplateMock.getRoom(1, 3)).thenReturn(RoomType.BOSS);

        doNothing().when(isaacMock).setPositions(any(Positions.class));

        mapController.nextIteration(COMMAND.NOTHING);

        ArgumentCaptor<Compass> argumentCaptor = ArgumentCaptor.forClass(Compass.class);
        verify(roomFactoryMock, times(2)).createRoom(any(Isaac.class), argumentCaptor.capture(), any(RoomType.class));
        assertFalse(argumentCaptor.getValue().isWest());
        assertTrue(argumentCaptor.getValue().isEast());
        assertFalse(argumentCaptor.getValue().isNorth());
        assertTrue(argumentCaptor.getValue().isSouth());

        verify(isaacMock, times(1)).setPositions(any(Positions.class));

        assertFalse(mapController.hasEnded());
        assertFalse(mapController.hasWon());
    }

}
