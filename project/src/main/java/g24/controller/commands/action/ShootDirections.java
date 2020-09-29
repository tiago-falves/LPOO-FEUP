package g24.controller.commands.action;

import g24.GameConfig;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.model.element.projectile.Gun;
import g24.model.element.projectile.Projectile;
import g24.model.utils.Positions;

import java.util.List;

public class ShootDirections extends Action {
    private Shoot shootUp;
    private Shoot shootDown;
    private Shoot shootLeft;
    private Shoot shootRight;


    public ShootDirections(Gun gun, Positions positions, boolean shootAutomatic) {
        shootUp = new Shoot(gun,positions, shootAutomatic);
        shootDown = new Shoot(gun,positions, shootAutomatic);
        shootLeft = new Shoot(gun,positions, shootAutomatic);
        shootRight = new Shoot(gun,positions, shootAutomatic);
    }

    public void executeAction(DIRECTION direction) {
        shootUp.executeAction(DIRECTION.UP);
        shootDown.executeAction(DIRECTION.DOWN);
        shootLeft.executeAction(DIRECTION.LEFT);
        shootRight.executeAction(DIRECTION.RIGHT);
    }
}

