package g24.model.element;

import g24.model.element.projectile.Projectile;
import g24.model.utils.Health;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IsaacTest {
    List<Isaac> isaacs = new ArrayList<>();
    private Health health;
    private List<Projectile> projectiles;

    /*@Before
    public void setup() {
        health = mock(Health.class);
        Projectile p1 = mock(Projectile.class);
        Projectile p2 = mock(Projectile.class);
        projectiles = new ArrayList<>();
        projectiles.add(p1);
        projectiles.add(p2);

        //isaacs.add(new Isaac(5, 5, health,projectiles));
    }
    @Test
    public void getHealth() {
        assertEquals(isaacs.get(0).getHealth(), health);
    }


    @Test
    public void isDead() {
        when(health.isZero()).thenReturn(true);
        assertTrue(isaacs.get(0).isDead());
        when(health.isZero()).thenReturn(false);
        assertFalse(isaacs.get(0).isDead());
    }*/

}
