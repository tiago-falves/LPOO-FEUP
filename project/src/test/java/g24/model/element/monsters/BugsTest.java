package g24.model.element.monsters;

import g24.controller.element.movementstrategy.MoveQuickRandomStrategy;
import g24.model.utils.Health;
import org.junit.Test;
import static org.junit.Assert.*;

public class BugsTest {

    @Test
    public void test() {
        Bugs bug = new Bugs(1, 1);
        assertNotNull(bug.getMoveStrategy());
        if(!(bug.getMoveStrategy() instanceof MoveQuickRandomStrategy))
            fail();
    }

    @Test
    public void getPower() {
        Bugs bugs = new Bugs(1, 1);
        assertNotNull(bugs.getPower());
        assertEquals (bugs.getPower(), 1);
    }

    @Test
    public void getHealth() {
        Health health = new Health(1);

        Bugs bugs = new Bugs(1, 1);
        assertNotNull(bugs.getHealth());
        assertEquals(bugs.getHealth().getHealth(), health.getHealth());
    }
}
