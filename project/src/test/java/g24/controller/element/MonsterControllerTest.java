package g24.controller.element;

import g24.controller.commands.action.Action;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.controller.element.movementstrategy.MoveRandomStrategy;
import g24.controller.map.RoomController;
import g24.model.element.Isaac;
import g24.model.element.objects.PowerUp;
import g24.model.utils.Position;
import g24.model.utils.Positions;
import g24.model.element.monsters.Monster;
import g24.model.map.RoomModel;
import org.junit.Before;
import org.junit.Test;

import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MonsterControllerTest {
    private MonsterController monsterController;
    private Monster monster;
    private MoveRandomStrategy randomStrategy;
    private Positions positionsMock;
    private Positions nextPositions;

    @Before
    public void setUp(){
        monster = mock(Monster.class);
        randomStrategy = mock(MoveRandomStrategy.class);

        positionsMock = getPositions();
        nextPositions = getNextPositions();

        when(positionsMock.collide(any(Positions.class))).thenReturn(true);
        when(monster.getPositions()).thenReturn(positionsMock);
        when(randomStrategy.move(monster.getPositions())).thenReturn(nextPositions);
        when(randomStrategy.getDirection()).thenReturn(DIRECTION.UP);
        when(monster.getMoveStrategy()).thenReturn(randomStrategy);
        doNothing().when(monster).setPositions(any(Positions.class));

        monsterController = new MonsterController(monster);
    }
    @Test
    public void handle(){
        RoomController roomController = mock(RoomController.class);
        when(roomController.isInsideBoundaries(any(Positions.class))).thenReturn(true);
        RoomModel roomModel = mock(RoomModel.class);
        when(roomController.getRoomModel()).thenReturn(roomModel);

        positionsMock = getPositions();
        Isaac isaac = mock(Isaac.class);
        when(isaac.getPositions()).thenReturn(positionsMock);
        when(roomController.getIsaac()).thenReturn(isaac);

        List<PowerUp> powerUps = getPowerUps();
        CollisionHandler collisionHandler = mock(CollisionHandler.class);
        when(collisionHandler.isCollidingElement(nextPositions,powerUps)).thenReturn(false);

        Action action = mock(Action.class);
        doNothing().when(action).executeAction(DIRECTION.UP);

        when(monster.getAction()).thenReturn(action);

        monsterController.handle(roomController,collisionHandler);

        verify(monster,times(1)).setPositions(any(Positions.class));
        verify(action,times(1)).executeAction(DIRECTION.UP);
    }


    private Positions getPositions(){
        Positions positions1 = mock(Positions.class);
        List<Position> positions = new ArrayList<>();

        Position pos1 = mock(Position.class);
        when(pos1.getX()).thenReturn(1);
        when(pos1.getY()).thenReturn(1);

        Position pos2 = mock(Position.class);
        when(pos2.getX()).thenReturn(2);
        when(pos2.getY()).thenReturn(2);

        positions.add(pos1);
        positions.add(pos2);

        when(positions1.getPositionList()).thenReturn(positions);

        return positions1;
    }

    private Positions getNextPositions(){
        Positions positions1;
        positions1 = mock(Positions.class);
        List<Position> positions = new ArrayList<>();

        Position pos1 = mock(Position.class);
        when(pos1.getX()).thenReturn(3);
        when(pos1.getY()).thenReturn(3);

        Position pos2 = mock(Position.class);
        when(pos2.getX()).thenReturn(4);
        when(pos2.getY()).thenReturn(4);

        positions.add(pos1);
        positions.add(pos2);

        when(positions1.getPositionList()).thenReturn(positions);

        return positions1;
    }

    private List<PowerUp> getPowerUps(){
        List<PowerUp> powerUps = new ArrayList<>();
        PowerUp pos1 = mock(PowerUp.class);
        PowerUp pos2 = mock(PowerUp.class);
        powerUps.add(pos1);
        powerUps.add(pos2);
        return powerUps;
    }




}
