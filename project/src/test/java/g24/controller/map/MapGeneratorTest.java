package g24.controller.map;

import g24.model.map.MapTemplate;
import g24.model.map.RoomType;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapGeneratorTest {
    @Test
    public void generate() {
        MapGenerator mapGenerator = new MapGenerator();
        MapTemplate grid = mapGenerator.generateGrid();

        int numEnemy = 0;
        int numTrap = 0;
        int numBoss = 0;
        int numStart = 0;

        int width = grid.getWidth();
        int height = grid.getHeight();
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(grid.getRoom(i, j) == RoomType.ENEMY_EASY || grid.getRoom(i, j) == RoomType.ENEMY_MEDIUM || grid.getRoom(i, j) == RoomType.ENEMY_HARD)
                    numEnemy++;
                else if(grid.getRoom(i, j) == RoomType.TRAP)
                    numTrap++;
                else if(grid.getRoom(i, j) == RoomType.BOSS)
                    numBoss++;
                else if(grid.getRoom(i, j) == RoomType.START)
                    numStart++;
            }
        }

        assertEquals(1, numBoss);
        assertEquals(1, numStart);
        assertTrue(numEnemy >= 1);
        assertTrue(numTrap >= 1);
        assertTrue(numStart+numEnemy+numTrap+numBoss == 6);
    }
}
