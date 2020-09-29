package g24.model.element;

import g24.model.utils.Health;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class HealthTest {

    @Test
    public void getHealth() {
        Health health = new Health(5);
        assertEquals(health.getHealth(),5);

        Health negative = new Health(-5);
        assertEquals(negative.getHealth(),0);
    }

    @Test
    public void setHealth() {
        Health health = new Health(5);
        health.setHealth(4);
        assertEquals(health.getHealth(),4);
    }

    @Test
    public void increaseHealth() {
        Health health = new Health(5);
        health.increase(4);
        assertEquals(health.getHealth(),9);
    }

    @Test
    public void decreaseHealth() {
        Health health = new Health(5);
        health.decrease(4);
        assertEquals(health.getHealth(),1);

        health.decrease(5);
        assertEquals(health.getHealth(),0);

    }

    @Test
    public void isZero() {
        Health health = new Health(0);
        assertTrue(health.isZero());

        Health newHealth = new Health(5);
        assertFalse(newHealth.isZero());

        Health negative = new Health(-5);
        assertTrue(negative.isZero());

    }


}
