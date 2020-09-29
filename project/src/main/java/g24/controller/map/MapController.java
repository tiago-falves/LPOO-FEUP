package g24.controller.map;

import g24.controller.commands.room.*;
import g24.controller.commands.user.*;
import g24.controller.element.CollisionHandler;
import g24.controller.element.HealthController;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.controller.observer.Observable;
import g24.controller.observer.VisitedRoomsObserver;
import g24.controller.state.StateController;
import g24.model.element.Isaac;
import g24.model.utils.Position;
import g24.model.map.Compass;
import g24.model.map.MapTemplate;
import g24.model.map.RoomModel;
import g24.model.map.RoomType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MapController extends StateController implements Observable<VisitedRoomsObserver> {
    private List<VisitedRoomsObserver> visitedRoomsObservers = new ArrayList<>();
    private MapTemplate grid;
    private Position currentGridPosition;
    private RoomController roomController;
    private TimeController timeController;
    private RoomFactory roomFactory;
    private LinkedHashMap<Position, RoomModel> positionToRoom;
    private Isaac isaac;
    private boolean isFinished;
    private boolean won;

    public MapController(MapTemplate grid, RoomController roomController, TimeController timeController, RoomFactory roomFactory) {
        this.grid = grid;
        this.currentGridPosition = grid.getStart();
        this.positionToRoom = new LinkedHashMap<>();
        this.roomFactory = roomFactory;
        this.timeController = timeController;
        this.roomController = roomController;
        this.isFinished = false;
        this.won = false;
        this.isaac = roomFactory.createIsaac();

        Compass firstRoomDoors = determineRoomAccess(currentGridPosition);
        RoomModel firstRoom = roomFactory.createRoom(
                isaac,
                firstRoomDoors,
                grid.getRoom(
                        currentGridPosition.getX(),
                        currentGridPosition.getY()
                )
        );
        positionToRoom.put(currentGridPosition, firstRoom);

        roomController.setRoomModel(firstRoom);
    }

    public void nextIteration(COMMAND currentCommand) {
        processCommand(currentCommand);
        timeController.updateTime();

        roomController.nextIteration();
        if(roomController.isFinished()){
            won = roomController.hasWon();
            isFinished = true;
        }

        if(grid.getRoom(currentGridPosition.getX(), currentGridPosition.getY()) == RoomType.BOSS
                && roomController.getRoomModel().getMonsters().size() == 0) {
            won = true;
            isFinished = true;
        }

        if(roomController.changeRoom()) {
            DIRECTION direction = roomController.getChangeRoomDirection();

            switch (direction) {
                case UP:
                    changeRoom(currentGridPosition.up(), new PlaceBottom());
                    break;
                case DOWN:
                    changeRoom(currentGridPosition.down(), new PlaceTop());
                    break;
                case LEFT:
                    changeRoom(currentGridPosition.left(), new PlaceRight());
                    break;
                case RIGHT:
                    changeRoom(currentGridPosition.right(), new PlaceLeft());
                    break;
                default:
                    break;
            }
        }
    }

    private void changeRoom(Position nextPosition, PlacementCommand placementCommand) {
        placementCommand.execute(roomController, isaac);

        if(positionToRoom.containsKey(nextPosition)) {
            roomController.setRoomModel(positionToRoom.get(nextPosition));
            roomController.reset();
        }
        else {
            Compass newRoomDoors = determineRoomAccess(nextPosition);
            RoomModel newRoom = roomFactory.createRoom(
                    isaac,
                    newRoomDoors,
                    grid.getRoom(
                            nextPosition.getX(),
                            nextPosition.getY()
                    )
            );

            positionToRoom.put(nextPosition, newRoom);
            roomController.setRoomModel(newRoom);
            roomController.reset();
            notifyObservers();
        }

        currentGridPosition = nextPosition;
    }

    private Compass determineRoomAccess(Position nextPosition) {
        int x = nextPosition.getX();
        int y = nextPosition.getY();

        boolean north = false, south = false, east = false, west = false;

        if(y > 0 && grid.getRoom(x, y-1) != RoomType.EMPTY)  north = true;
        if(y < grid.getHeight()-1 && grid.getRoom(x, y+1) != RoomType.EMPTY)  south = true;
        if(x < grid.getWidth()-1 && grid.getRoom(x+1, y) != RoomType.EMPTY)  east = true;
        if(x > 0 && grid.getRoom(x-1, y) != RoomType.EMPTY) west = true;

        return new Compass(north, south, east, west);
    }

    public RoomModel getCurrentRoom() {
        return this.roomController.getRoomModel();
    }

    public HealthController getHealthController() {
        return roomController.getHealthController();
    }

    public TimeController getTimeController() {
        return timeController;
    }

    public ScoreController getScoreController() {
        return roomController.getScoreController();
    }

    @Override
    public void processCommand(COMMAND commandType) {
        Isaac isaac = roomController.getRoomModel().getIsaac();
        switch(commandType) {
            case UP:
                roomController.getRoomModel().setCurrentDirection(commandType);
                new MoveUpCommand(isaac, roomController,roomController.getCollisionHandler()).execute();
                break;
            case DOWN:
                roomController.getRoomModel().setCurrentDirection(commandType);
                new MoveDownCommand(isaac, roomController,roomController.getCollisionHandler()).execute();
                break;
            case LEFT:
                roomController.getRoomModel().setCurrentDirection(commandType);
                new MoveLeftCommand(isaac, roomController,roomController.getCollisionHandler()).execute();
                break;
            case RIGHT:
                roomController.getRoomModel().setCurrentDirection(commandType);
                new MoveRightCommand(isaac, roomController,roomController.getCollisionHandler()).execute();
                break;
            case QUIT:
                isFinished = true;
                break;
            case SHOOT:
                new ShootCommand(isaac, roomController.getRoomModel().getCurrentDirection()).execute();
                break;
            default:
                break;
        }
    }

    public boolean hasWon() {
        return won;
    }

    @Override
    public boolean hasEnded() {
        return isFinished;
    }

    @Override
    public void addObserver(VisitedRoomsObserver observer) {
        visitedRoomsObservers.add(observer);
    }

    @Override
    public void removeObserver(VisitedRoomsObserver observer) {
        visitedRoomsObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(VisitedRoomsObserver observer : visitedRoomsObservers)
            observer.updateVisitedRooms();
    }

}

