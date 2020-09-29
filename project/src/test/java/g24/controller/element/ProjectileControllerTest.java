package g24.controller.element;

import g24.controller.element.movementstrategy.DIRECTION;
import g24.model.element.projectile.Projectile;
import g24.model.utils.Position;
import g24.model.utils.Positions;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ProjectileControllerTest {
    private Projectile projectile;
    private Positions positions;
    @Before
    public void setup(){
        projectile = mock(Projectile.class);
        Position position = mock(Position.class);
        positions = mock(Positions.class);
        doNothing().when(positions).shiftTo(position);
        doNothing().when(projectile).setJustCreated(false);
        when(projectile.getPositions()).thenReturn(positions);
        when(projectile.exists()).thenReturn( true);


    }
    @Test
    public void shoot1(){
        when(projectile.getDirection()).thenReturn(DIRECTION.UP);
        when(projectile.justCreated()).thenReturn(true);
        doNothing().when(projectile).shootUp();

        ProjectileController projectileController = new ProjectileController(projectile);
        projectileController.shoot(positions);

        verify(projectile,times(1)).shootUp();
        verify(projectile,times(1)).setJustCreated(false);
    }

    @Test
    public void shoot2(){
        when(projectile.getDirection()).thenReturn(DIRECTION.LEFT);
        when(projectile.justCreated()).thenReturn(true);
        doNothing().when(projectile).shootUp();

        ProjectileController projectileController = new ProjectileController(projectile);
        projectileController.shoot(positions);

        verify(projectile,times(1)).shootLeft();
        verify(projectile,times(1)).setJustCreated(false);
    }
    @Test
    public void shoot3(){
        when(projectile.getDirection()).thenReturn(DIRECTION.RIGHT);
        when(projectile.justCreated()).thenReturn(true);
        doNothing().when(projectile).shootUp();

        ProjectileController projectileController = new ProjectileController(projectile);
        projectileController.shoot(positions);

        verify(projectile,times(1)).shootRight();
        verify(projectile,times(1)).setJustCreated(false);
    }

    @Test
    public void shoot4(){
        when(projectile.getDirection()).thenReturn(DIRECTION.DOWN);
        when(projectile.justCreated()).thenReturn(true);
        doNothing().when(projectile).shootUp();

        ProjectileController projectileController = new ProjectileController(projectile);
        projectileController.shoot(positions);

        verify(projectile,times(1)).shootDown();
        verify(projectile,times(1)).setJustCreated(false);
    }

    @Test
    public void increaseDamage(){
        int value = 0;
        doNothing().when(projectile).setDamage(value);
        ProjectileController projectileController = new ProjectileController(projectile);
        projectileController.increaseDamage(value);
        verify(projectile,times(1)).setDamage(value);
    }
}
