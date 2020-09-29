package g24.controller.commands.interaction;


import g24.controller.map.RoomController;
import g24.model.element.objects.PowerUp;
import g24.model.utils.Health;

public class DecreaseHealthCommand extends Interaction {
    private int value;

    public DecreaseHealthCommand(int value){
        this.value = value;
    }

    @Override
    public void interact(PowerUp powerUp, RoomController room) {
        Health health = room.getIsaac().getHealth();
        health.decrease(value);
        if(health.isZero()) room.finishRoom(false);
    }
}
