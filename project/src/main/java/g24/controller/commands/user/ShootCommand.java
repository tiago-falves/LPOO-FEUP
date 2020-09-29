package g24.controller.commands.user;

import g24.controller.commands.action.Shoot;
import g24.controller.element.movementstrategy.DIRECTION;
import g24.model.element.Isaac;

public class ShootCommand extends UserCommand {
    private Isaac isaac;
    private DIRECTION direction;

    public ShootCommand(Isaac isaac, DIRECTION direction){
        this.isaac = isaac;
        this.direction = direction;
    }

    public void execute(){
        Shoot shoot = new Shoot(isaac.getGun(),isaac.getPositions(),true);
        shoot.executeAction(direction);
    }
}
