package g24.model.element.monsters;

import g24.controller.element.movementstrategy.MoveRandomStrategy;
import org.junit.Test;
import static org.junit.Assert.*;

public class ZombieTest {

    @Test
    public void test() {
        Zombie zombie = new Zombie(1, 1);
        assertNotNull(zombie.getMoveStrategy());
        if(!(zombie.getMoveStrategy() instanceof MoveRandomStrategy))
            fail();
    }
    @Test
    public void getPower() {
        Zombie zombie = new Zombie(1, 1);
        assertNotNull(zombie.getPower());
        assertEquals (zombie.getPower(), 1);
    }
}