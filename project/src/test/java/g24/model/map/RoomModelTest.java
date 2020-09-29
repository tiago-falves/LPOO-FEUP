package g24.model.map;

import g24.controller.commands.user.COMMAND;
import g24.model.utils.Health;
import g24.model.element.Isaac;
import g24.model.element.objects.Wall;
import g24.model.element.monsters.Bugs;
import g24.model.element.monsters.Zombie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RoomModelTest {

    RoomModel roomModel;

    /*@Before
    public void setup() {
        Isaac isaac = mock(Isaac.class);

        roomModel = new RoomModel(isaac,50, 25);
    }

    @Test
    public void testSize() {
        assertEquals(50, roomModel.getWidth());
        assertEquals(25, roomModel.getHeight());
    }

    @Test
    public void testCurrentCommand() {
        /*assertEquals(COMMAND.UP, roomModel.getCurrentCommand());

        roomModel.setCurrentCommand(COMMAND.DOWN);
        assertEquals(COMMAND.DOWN, roomModel.getCurrentCommand());
    }

    @Test
    public void testFrameCounter() {
        assertEquals(0, roomModel.getFrameCounter());

        roomModel.incrementFrameCounter();
        roomModel.incrementFrameCounter();
        roomModel.incrementFrameCounter();
        assertEquals(3, roomModel.getFrameCounter());

        roomModel.setFrameCounter(10);
        assertEquals(10, roomModel.getFrameCounter());
    }

    @Test
    public void testIsaac() {
        assertNotNull(roomModel.getIsaac());

        Isaac newIsaac = mock(Isaac.class);

        Health health = mock(Health.class);
        when(newIsaac.getHealth()).thenReturn(health);

        roomModel.setIsaac(newIsaac);

        assertEquals(health, roomModel.getIsaac().getHealth());
    }

    @Test
    public void testIsFinished() {
        //assertNotNull(roomModel.isFinished());
        assertEquals(roomModel.isFinished(),false);
        roomModel.setFinished(true);
        assertEquals(roomModel.isFinished(),true);

    }

    @Test
    public void testMonsters() {
        Bugs bug1 = mock(Bugs.class);
        Bugs bug2 = mock(Bugs.class);
        Bugs bug3 = mock(Bugs.class);
        Zombie zombie1 = mock(Zombie.class);
        Zombie zombie2 = mock(Zombie.class);
        Zombie zombie3 = mock(Zombie.class);

        roomModel.addMonster(bug1);
        roomModel.addMonster(bug2);
        roomModel.addMonster(bug3);
        roomModel.addMonster(zombie1);
        roomModel.addMonster(zombie2);
        roomModel.addMonster(zombie3);

        assertEquals(6, roomModel.getMonsters().size());
    }

    @Test
    public void testWalls() {
        Wall wall1 = mock(Wall.class);
        Wall wall2 = mock(Wall.class);

        roomModel.addWall(wall1);
        roomModel.addWall(wall2);

        assertEquals(2, roomModel.getWalls().size());
    }

    @Test
    public void testElements() {
        Bugs bug1 = mock(Bugs.class);
        Bugs bug2 = mock(Bugs.class);
        Bugs bug3 = mock(Bugs.class);
        Zombie zombie1 = mock(Zombie.class);
        Zombie zombie2 = mock(Zombie.class);
        Zombie zombie3 = mock(Zombie.class);
        roomModel.addMonster(bug1);
        roomModel.addMonster(bug2);
        roomModel.addMonster(bug3);
        roomModel.addMonster(zombie1);
        roomModel.addMonster(zombie2);
        roomModel.addMonster(zombie3);

        Wall wall1 = mock(Wall.class);
        Wall wall2 = mock(Wall.class);
        roomModel.addWall(wall1);
        roomModel.addWall(wall2);

        assertEquals(9, roomModel.getAllElements().size());
    }*/
}


